package com.stint.race_data_server.infrastructure.adapter.out.persistence.filesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Mapea y representa la estructura JSON de los datos de Circuito en Filesystem (sistema de archivos).
 * En caso de modificar json, modificar DTO y mapper.
 */
@Data
public class TrackDto {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("layouts")
    private List<LayoutRefDto> layouts;

    @Data
    public static class LayoutRefDto {
        @JsonProperty("id")
        private int id;
        
        @JsonProperty("variant")
        private String variant;
        
        @JsonProperty("path")
        private String path;
    }
}
