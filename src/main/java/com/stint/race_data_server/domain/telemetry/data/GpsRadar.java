package com.stint.race_data_server.domain.telemetry.data;

import java.util.List;

import com.stint.race_data_server.domain.telemetry.sample.GpsRadarSample;
import com.stint.race_data_server.domain.telemetry.sample.GpsRadarSample.RadarBlip;

public class GpsRadar {
    
    private final float heading;
    private final float x;
    private final float z;
    private final List<RadarBlip> nearbyCars;

    public GpsRadar(float heading, float x, float z, List<RadarBlip> nearbyCars) {
        this.heading = heading;
        this.x = x;
        this.z = z;
        this.nearbyCars = nearbyCars;
    }

    public static GpsRadar from(GpsRadarSample s) {
        return new GpsRadar(
            s.getHeading(),
            s.getX(),
            s.getZ(),
            s.getNearbyCars()
        );
    }
}
