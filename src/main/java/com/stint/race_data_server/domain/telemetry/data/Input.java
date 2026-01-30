package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.InputSample;

public class Input {
    
    private final long rpm;
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

    public Input(long rpm, float turbo, float speedKmh, int gear, float throttle, float brake, float clutch, 
            float steer, float fuel, float kersCharge, float kersInput) {
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

    public static Input from(InputSample s) {
        return new Input(
            s.getRpm(),
            s.getTurbo(),
            s.getSpeedKmh(),
            s.getGear(),
            s.getThrottle(),
            s.getBrake(),
            s.getClutch(),
            s.getSteer(),
            s.getFuel(),
            s.getKersCharge(),
            s.getKersInput()
        );
    }
}
