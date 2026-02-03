package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.AerodynamicSample;

public class Aerodynamic {

    private final float drag;
    private final float downforce;
    private final float clFront;
    private final float clRear;
    private final float cd;
    private final float rideHeightFront;
    private final float rideHeightRear;

    public Aerodynamic(float drag, float downforce, float clFront, float clRear, float cd, 
            float rideHeightFront, float rideHeightRear) {
        this.drag = drag;
        this.downforce = downforce;
        this.clFront = clFront;
        this.clRear = clRear;
        this.cd = cd;
        this.rideHeightFront = rideHeightFront;
        this.rideHeightRear = rideHeightRear;
    }

    public static Aerodynamic from(AerodynamicSample s) {
        return new Aerodynamic(
            s.getDrag(),
            s.getDownforce(),
            s.getClFront(),
            s.getClRear(),
            s.getCd(),
            s.getRideHeightFront(),
            s.getRideHeightRear()
        );
    }

    public float getDownforce() {
        return downforce;
    }

    public float getDrag() {
        return drag;
    }

    public float getClFront() {
        return clFront;
    }

    public float getClRear() {
        return clRear;
    }

    public float getCd() {
        return cd;
    }

    public float getRideHeightFront() {
        return rideHeightFront;
    }

    public float getRideHeightRear() {
        return rideHeightRear;
    }
}
