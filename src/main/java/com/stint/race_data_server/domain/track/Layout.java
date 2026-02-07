package com.stint.race_data_server.domain.track;

/**
 * Representa layouts (opciones) de un mismo circuito {@link Track}.
 */
public class Layout {
    
    private final String id;
    private final String name;
    private final String variant;
    private final int length;
    private final int width;
    private final int pitboxes;
    private final String mapImagePath;
    private final RenderParameters renderParameters;

    public Layout(String id, String name, String variant, int length, int width, int pitboxes, 
            String mapImagePath, RenderParameters renderParameters) {
        this.id = id;
        this.name = name;
        this.variant = variant;
        this.length = length;
        this.width = width;
        this.pitboxes = pitboxes;
        this.mapImagePath = mapImagePath;
        this.renderParameters = renderParameters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVariant() {
        return variant;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getPitboxes() {
        return pitboxes;
    }

    public String getMapImagePath() {
        return mapImagePath;
    }

    public RenderParameters getRenderParameters() {
        return renderParameters;
    }
}
