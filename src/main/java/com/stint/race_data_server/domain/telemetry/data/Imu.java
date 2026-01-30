package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.ImuSample;

public class Imu {
    
    private final float accX;
    private final float accY;
    private final float accZ;
    private final float roll;
    private final float pitch;

    public Imu(float accX, float accY, float accZ, float roll, float pitch) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
        this.roll = roll;
        this.pitch = pitch;
    }

    public static Imu from(ImuSample sample) {
        return new Imu(
            sample.getAccX(),
            sample.getAccY(),
            sample.getAccZ(),
            sample.getRoll(),
            sample.getPitch()
        );
    }
}
