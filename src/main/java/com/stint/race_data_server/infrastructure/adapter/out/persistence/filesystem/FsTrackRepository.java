package com.stint.race_data_server.infrastructure.adapter.out.persistence.filesystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stint.race_data_server.domain.track.Layout;
import com.stint.race_data_server.domain.track.RenderParameters;
import com.stint.race_data_server.domain.track.Track;
import com.stint.race_data_server.application.port.out.LoadTrack;
import com.stint.race_data_server.infrastructure.adapter.out.persistence.filesystem.dto.LayoutDto;
import com.stint.race_data_server.infrastructure.adapter.out.persistence.filesystem.dto.TrackDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Adaptador de salida que define implementacion de Filesystem (Fs)
 * 
 * Implementa puerto LoadTrack leyendo archivos JSON del filesystem.
 * Utiliza cache en memoria para mejorar el rendimiento.
 * - los datos se deben cargar solo cuando se solicitan.
 */
@Repository
public class FsTrackRepository implements LoadTrack {
    
    private static final Logger log = LoggerFactory.getLogger(FsTrackRepository.class);
    private static final Path BASE_PATH = Path.of("data/tracks");
    
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, Track> trackCache = new ConcurrentHashMap<>();
    
    @Override
    public Optional<Track> findById(String trackId) {

        if (trackCache.containsKey(trackId)) {
            return Optional.of(trackCache.get(trackId));
        }
        
        try {
            Path trackPath = BASE_PATH.resolve(trackId);
            Path trackJsonPath = trackPath.resolve("track.json");
            
            if (!Files.exists(trackJsonPath)) {
                return Optional.empty();
            }
        
            TrackDto trackDto = mapper.readValue(trackJsonPath.toFile(), TrackDto.class);
            List<Layout> layouts = loadLayoutsForTrack(trackId, trackDto);
            Track track = mapToTrack(trackDto, layouts);
            trackCache.put(trackId, track);
            
            return Optional.of(track);
            
        } catch (IOException e) {
            log.error("Failed to load track: {}", trackId, e);
            return Optional.empty();
        }
    }
    
    @Override
    public List<String> findAllTrackIds() {
        
        if (!Files.exists(BASE_PATH)) {
            return List.of();
        }
        
        try (Stream<Path> paths = Files.list(BASE_PATH)) {
            return paths
                .filter(Files::isDirectory)
                .map(path -> path.getFileName().toString())
                .sorted()
                .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Failed to list track IDs", e);
            return List.of();
        }
    }
    
    @Override
    public Optional<Layout> findLayoutById(String trackId, String layoutId) {
        // optional permite manejar la ausencia de valores sin NullPointerException
        return findById(trackId)
            .flatMap(track -> track.getLayouts().stream()
                .filter(layout -> layout.getId().equals(layoutId))
                .findFirst());
    }
    
    private List<Layout> loadLayoutsForTrack(String trackId, TrackDto trackDto) {
        
        return trackDto.getLayouts().stream()
            .map(layoutRef -> loadLayout(trackId, layoutRef.getPath()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    }
    
    private Optional<Layout> loadLayout(String trackId, String layoutPath) {

        Path layoutJsonPath = BASE_PATH.resolve(trackId).resolve(layoutPath).resolve("layout.json");
        
        if (!Files.exists(layoutJsonPath)) {
            return Optional.empty();
        }
        
        try {
            LayoutDto dto = mapper.readValue(layoutJsonPath.toFile(), LayoutDto.class);
            return Optional.of(mapToLayout(dto));
            
        } catch (IOException e) {
            log.error("Failed to load layout: {}/{}", trackId, layoutPath, e);
            return Optional.empty();
        }
    }
    
    private Track mapToTrack(TrackDto dto, List<Layout> layouts) {
        return new Track(
            dto.getId(),
            dto.getName(),
            dto.getCountry(),
            dto.getCity(),
            layouts
        );
    }
    
    private Layout mapToLayout(LayoutDto dto) {

        RenderParameters renderParams = new RenderParameters(
            dto.getRenderParameters().getWidth(),
            dto.getRenderParameters().getHeight(),
            dto.getRenderParameters().getMargin(),
            dto.getRenderParameters().getScaleFactor(),
            dto.getRenderParameters().getXOffset(),
            dto.getRenderParameters().getZOffset(),
            dto.getRenderParameters().getDrawingSize()
        );
        
        return new Layout(
            dto.getId(),
            dto.getName(),
            dto.getVariant(),
            dto.getLength(),
            dto.getWidth(),
            dto.getPitboxes(),
            dto.getMapImagePath(),
            renderParams
        );
    }
}
