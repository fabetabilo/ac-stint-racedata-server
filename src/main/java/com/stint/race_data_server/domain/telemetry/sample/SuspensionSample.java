package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de cinematica de suspension
 */
public class SuspensionSample extends TelemetrySample {
    // 4 ruedas: FL, FR, RL, RR
    private final float[] suspensionTravel;
    private final float[] camberRAD;
    private final float[] wheelLoad;
    private final float[] wheelAngularSpeed;

    public SuspensionSample(int deviceId, Instant ts, float[] suspensionTravel, float[] camberRAD, 
            float[] wheelLoad, float[] wheelAngularSpeed) {
        super(deviceId, ts);
        this.suspensionTravel = suspensionTravel;
        this.camberRAD = camberRAD;
        this.wheelLoad = wheelLoad;
        this.wheelAngularSpeed = wheelAngularSpeed;
    }
    // getters
    public float[] getSuspensionTravel() {
        return suspensionTravel;
    }

    public float[] getCamberRAD() {
        return camberRAD;
    }

    public float[] getWheelLoad() {
        return wheelLoad;
    }

    public float[] getWheelAngularSpeed() {
        return wheelAngularSpeed;
    }
}
