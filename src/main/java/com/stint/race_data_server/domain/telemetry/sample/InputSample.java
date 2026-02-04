package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de informacion input de driver sobre el coche e informacion relevante de este
 */
public class InputSample extends TelemetrySample {

    private final long rpm;  // unsigned int
    private final float turbo;
    private final float speedKmh;
    private final int gear;
    private final float throttle;
    private final float brake;
    private final float clutch;
    private final float steer;
    private final float fuel;
    private final float kersCharge;
    private final float kersInput;

    public InputSample(int deviceId, Instant timestamp, long rpm, float turbo, float speedKmh, int gear,
            float throttle, float brake, float clutch, float steer, float fuel, float kersCharge, float kersInput) {
        super(deviceId, timestamp);
        this.rpm = rpm;
        this.turbo = turbo;
        this.speedKmh = speedKmh;
        this.gear = gear;
        this.throttle = throttle;
        this.brake = brake;
        this.clutch = clutch;
        this.steer = steer;
        this.fuel = fuel;
        this.kersCharge = kersCharge;
        this.kersInput = kersInput;
    }

    // getters
    public long getRpm() {
        return rpm;
    }

    public float getTurbo() {
        return turbo;
    }

    public float getSpeedKmh() {
        return speedKmh;
    }

    public int getGear() {
        return gear;
    }

    public float getThrottle() {
        return throttle;
    }

    public float getBrake() {
        return brake;
    }

    public float getClutch() {
        return clutch;
    }

    public float getSteer() {
        return steer;
    }

    public float getFuel() {
        return fuel;
    }

    public float getKersCharge() {
        return kersCharge;
    }

    public float getKersInput() {
        return kersInput;
    }
}

