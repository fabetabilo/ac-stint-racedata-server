package com.stint.race_data_server.domain.telemetry.data;

public final class Imu implements TelemetryComponent {
    
    private final float accX;
    private final float accY;
    private final float accZ;
    private final float roll;
    private final float pitch;
    private final float yawRate;
    private final float sideSlip;

    public Imu(float accX, float accY, float accZ, float roll, float pitch, float yawRate, float sideSlip) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
        this.roll = roll;
        this.pitch = pitch;
        this.yawRate = yawRate;
        this.sideSlip = sideSlip;
    }

    public float getAccX() {
        return accX;
    }

    public float getAccY() {
        return accY;
    }

    public float getAccZ() {
        return accZ;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYawRate() {
        return yawRate;
    }

    public float getSideSlip() {
        return sideSlip;
    }
}
