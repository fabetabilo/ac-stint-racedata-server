package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.SuspensionSample;

public class Suspension {
    // 4 ruedas: FL, FR, RL, RR 
    private final float[] suspensionTravel;  // metros
    private final float[] camberRAD;         // radianes
    private final float[] wheelLoad;         // Newton
    private final float[] wheelAngularSpeed; // rad/s

    public Suspension(float[] suspensionTravel, float[] camberRAD, float[] wheelLoad, float[] wheelAngularSpeed) {
        this.suspensionTravel = suspensionTravel;
        this.camberRAD = camberRAD;
        this.wheelLoad = wheelLoad;
        this.wheelAngularSpeed = wheelAngularSpeed;
    }

    public static Suspension from(SuspensionSample sample) {
        return new Suspension(
            sample.getSuspensionTravel(),
            sample.getCamberRAD(),
            sample.getWheelLoad(),
            sample.getWheelAngularSpeed()
        );
    }

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
