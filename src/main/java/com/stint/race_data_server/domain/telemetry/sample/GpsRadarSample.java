package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;
import java.util.List;

/**
 * Muestra de GPS y radar incluido.
 */
public class GpsRadarSample extends TelemetrySample {

    /**
     * Sistema radar del coche
     */
    public static record RadarBlip(byte carId, float x, float z) {
    }

    private final float heading; // direccion del cofre del coche
    private final float x;
    private final float z;
    private final List<RadarBlip> nearbyCars;

    public GpsRadarSample(int driverIdx, Instant ts, float heading, float x, float z, List<RadarBlip> nearbyCars) {
        super(driverIdx, ts);
        this.heading = heading;
        this.x = x;
        this.z = z;
        this.nearbyCars = nearbyCars;
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

    public List<RadarBlip> getNearbyCars() {
        return nearbyCars;
    }
}

