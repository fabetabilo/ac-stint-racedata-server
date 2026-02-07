package com.stint.race_data_server.application.service;

import com.stint.race_data_server.application.port.in.QueryTrack;
import com.stint.race_data_server.domain.track.Layout;
import com.stint.race_data_server.domain.track.Track;
import com.stint.race_data_server.application.port.out.LoadTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que implementa los casos de uso de Track
 * 
 * Implementa puerto de entrada (QueryTrack) y orquesta logica utilizando 
 * puerto de salida LoadTrack.
 */
@Service
public class TrackService implements QueryTrack {
    
    private static final Logger log = LoggerFactory.getLogger(TrackService.class);
    
    private final LoadTrack loadTrack;
    
    public TrackService(LoadTrack loadTrack) {
        this.loadTrack = loadTrack;
        log.info("TrackService initialized: tracks will be loaded on demand");
    }
    
    @Override
    public Optional<Track> getTrack(String trackId) {
        log.debug("Requesting track: {}", trackId); //  solo cuando se solicita.
        Optional<Track> track = loadTrack.findById(trackId);
        
        if (track.isPresent()) {
            log.info("Track '{}' loaded successfully with {} layouts", 
                trackId, track.get().getLayouts().size());
        } else {
            log.warn("Track '{}' not found", trackId);
        }
        
        return track;
    }
    
    @Override
    public Optional<Layout> getTrackLayout(String trackId, String layoutId) {
        log.debug("Requesting layout: {}/{}", trackId, layoutId);
        Optional<Layout> layout = loadTrack.findLayoutById(trackId, layoutId);
        
        if (layout.isPresent()) {
            log.info("Layout '{}/{}' loaded successfully", trackId, layoutId);
        } else {
            log.warn("Layout '{}/{}' not found", trackId, layoutId);
        }
        
        return layout;
    }
    
    @Override
    public List<String> getAvailableTrackIds() {
        log.debug("Requesting available track IDs");
        List<String> trackIds = loadTrack.findAllTrackIds();
        log.info("Found {} available tracks", trackIds.size());
        return trackIds;
    }
}
