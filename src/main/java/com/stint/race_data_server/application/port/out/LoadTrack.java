package com.stint.race_data_server.application.port.out;

import com.stint.race_data_server.domain.track.Layout;
import com.stint.race_data_server.domain.track.Track;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida que define operaciones de persistencia necesarias 
 * para el dominio {@link Track}.
 */
public interface LoadTrack {
    
    Optional<Track> findById(String trackId);
    
    Optional<Layout> findLayoutById(String trackId, String layoutId);

    List<String> findAllTrackIds();
    
}
