package com.stint.race_data_server.domain.telemetry.data;

public final class Suspension implements TelemetryComponent {
    // 4 ruedas: FL, FR, RL, RR 
    private final float[] suspensionTravel;  // metros
    private final float[] camberRAD;         // radianes
    private final float[] wheelLoad;         // Newton
    private final float[] wheelAngularSpeed; // rad/s

    private static final int WHEEL_COUNT = 4;

    public Suspension(float[] suspensionTravel, float[] camberRAD, float[] wheelLoad, float[] wheelAngularSpeed) {
        this.suspensionTravel = validateArray(suspensionTravel, "suspensionTravel");
        this.camberRAD = validateArray(camberRAD, "camberRAD");
        this.wheelLoad = validateArray(wheelLoad, "wheelLoad");
        this.wheelAngularSpeed = validateArray(wheelAngularSpeed, "wheelAngularSpeed");
    }

    private static float[] validateArray(float[] array, String fieldName) {
        if (array == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        if (array.length != WHEEL_COUNT) {
            throw new IllegalArgumentException(fieldName + " must have " + WHEEL_COUNT + " elements");
        }
        return array;
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
