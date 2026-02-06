package com.stint.race_data_server.domain.telemetry.data;

public final class Gps implements TelemetryComponent {
    
    private final float heading;
    private final float x;
    private final float z;

    public Gps(float heading, float x, float z) {
        this.heading = heading;
        this.x = x;
        this.z = z;
    }

    public float getHeading() {
        return heading;
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }
}
