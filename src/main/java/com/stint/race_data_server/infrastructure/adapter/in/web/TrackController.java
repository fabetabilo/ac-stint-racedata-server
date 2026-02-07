package com.stint.race_data_server.infrastructure.adapter.in.web;

import com.stint.race_data_server.application.port.in.QueryTrack;
import com.stint.race_data_server.domain.track.Layout;
import com.stint.race_data_server.domain.track.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Adaptador de entrada - REST API
 * 
 * Expone los casos de uso de Track mediante endpoints HTTP REST.
 * Este adaptador traduce peticiones HTTP a llamadas al puerto de entrada.
 */
@RestController
@RequestMapping("/api/track")
@CrossOrigin(origins = "*") // !!TEMPORAL
public class TrackController {

    private final QueryTrack queryTrack;
    // private final ManageTrack manageTrack; <- futuro para crear, update, y delete
    // en el futuro podria reemplazarse o complementarse con otros adaptadores: graphQL, gRPC, cli, etc

    public TrackController(QueryTrack queryTrack) {
        this.queryTrack = queryTrack;
    }    
    
    // TEMPORAL: SOLO LECTURA

    @GetMapping
    public ResponseEntity<List<String>> listAvailableTracks() {

        List<String> trackIds = queryTrack.getAvailableTrackIds();
        
        return ResponseEntity.ok(trackIds);

    }
    
    @GetMapping("/{trackId}")
    public ResponseEntity<Track> getTrack(@PathVariable String trackId) {
        
        return queryTrack.getTrack(trackId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());

    }
    
    @GetMapping("/{trackId}/{layoutId}")
    public ResponseEntity<Layout> getLayout(@PathVariable String trackId, @PathVariable String layoutId) {
        
        return queryTrack.getTrackLayout(trackId, layoutId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
