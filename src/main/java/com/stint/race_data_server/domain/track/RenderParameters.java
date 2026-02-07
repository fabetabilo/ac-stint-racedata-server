package com.stint.race_data_server.domain.track;

/**
 * Parametros especificos para cada layout de un circuito, existen dentro del
 * contexto {@link Layout}. Su objetivo es renderizar
 * posiciones en un mapa.
 */
public class RenderParameters {
    
    private final float width;
    private final float height;
    private final float margin;
    private final float scaleFactor;
    private final float xOffset;
    private final float zOffset;
    private final int drawingSize;

    public RenderParameters(float width, float height, float margin, float scaleFactor, 
            float xOffset, float zOffset, int drawingSize) {
        this.width = width;
        this.height = height;
        this.margin = margin;
        this.scaleFactor = scaleFactor;
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.drawingSize = drawingSize;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getMargin() {
        return margin;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public float getXOffset() {
        return xOffset;
    }

    public float getZOffset() {
        return zOffset;
    }

    public int getDrawingSize() {
        return drawingSize;
    }
}
