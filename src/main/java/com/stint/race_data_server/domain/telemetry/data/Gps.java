package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.GpsSample;

public class Gps {
    
    private final float heading;
    private final float x;
    private final float z;

    public Gps(float heading, float x, float z) {
        this.heading = heading;
        this.x = x;
        this.z = z;
    }

    public static Gps from(GpsSample s) {
        return new Gps(
            s.getHeading(),
            s.getX(),
            s.getZ()
        );
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
