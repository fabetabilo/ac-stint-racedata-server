package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de GPS.
 */
public class GpsSample extends TelemetrySample {

    private final float heading; // direccion del cofre del coche
    private final float x;
    private final float z;

    public GpsSample(int deviceId, Instant ts, float heading, float x, float z) {
        super(deviceId, ts);
        this.heading = heading;
        this.x = x;
        this.z = z;
    }

    // getters
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

