package com.stint.race_data_server.infrastructure.adapter.out.persistence.filesystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Mapea y representa la estructura JSON de los datos de Layout de circuito en Filesystem.
 * En caso de modificar json, modificar DTO y mapper.
 */
@Data
public class LayoutDto {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("variant")
    private String variant;
    
    @JsonProperty("length")
    private int length;
    
    @JsonProperty("width")
    private int width;
    
    @JsonProperty("pitboxes")
    private int pitboxes;
    
    @JsonProperty("mapImagePath")
    private String mapImagePath;
    
    @JsonProperty("renderParameters")
    private RenderParametersDto renderParameters;

    // existe solo en contexto de layout -> diseño
    @Data
    public static class RenderParametersDto {
        @JsonProperty("width")
        private float width;
        
        @JsonProperty("height")
        private float height;
        
        @JsonProperty("margin")
        private float margin;
        
        @JsonProperty("scaleFactor")
        private float scaleFactor;
        
        @JsonProperty("xOffset")
        private float xOffset;
        
        @JsonProperty("zOffset")
        private float zOffset;
        
        @JsonProperty("drawingSize")
        private int drawingSize;
    }
}
