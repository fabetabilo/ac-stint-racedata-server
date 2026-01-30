package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de informacion en tiempo real de carga aerodinamica sobre el coche
 */
public class AerodynamicSample extends TelemetrySample {

    private final float drag;
    private final float downforce;
    private final float clFront;
    private final float clRear;
    private final float cd;
    private final float rideHeightFront;
    private final float rideHeightRear;

    public AerodynamicSample(int driverIdx, Instant ts, float drag, float downforce,
            float clFront, float clRear, float cd, float rideHeightFront, float rideHeightRear) {
        super(driverIdx, ts);
        this.drag = drag;
        this.downforce = downforce;
        this.clFront = clFront;
        this.clRear = clRear;
        this.cd = cd;
        this.rideHeightFront = rideHeightFront;
        this.rideHeightRear = rideHeightRear;
    }
    // getters
    public float getDrag() {
        return drag;
    }

    public float getDownforce() {
        return downforce;
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
