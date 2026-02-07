package com.stint.race_data_server.application.port.in;

import com.stint.race_data_server.domain.track.Layout;
import com.stint.race_data_server.domain.track.Track;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada que define los casos de uso del dominio {@link Track}
 * 
 * Define las operaciones que el exterior puede solicitar relacionado
 * con circuitos y sus configuraciones. Debe ser implementado en REST
 */
public interface QueryTrack {
    
    /**
     * Obtiene la configuracion completa de un circuito con todos sus layouts
     */
    Optional<Track> getTrack(String trackId);
    
    /**
     * Obtiene la configuracion de un layout específico de un circuito
     */
    Optional<Layout> getTrackLayout(String trackId, String layoutId);
    
    /**
     * Obtiene lista de los identificadores de todos los circuitos disponibles
     */
    List<String> getAvailableTrackIds();
        // util para selector de track
}
