package com.stint.race_data_server.domain.telemetry.data;

public final class Info implements TelemetryComponent {
    
    private final String carNumber;
    private final String driverName;
    private final String teamId;
    private final boolean inPit;
    private final float dist;
    private final float[] carDamage;
    private final boolean tcOn;
    private final boolean absOn;

    private static final int DAMAGE_ZONES = 5;

    public Info(String carNumber, String driverName, String teamId, boolean inPit,
            float dist, float[] carDamage, boolean tcOn, boolean absOn) {
        this.carNumber = carNumber != null ? carNumber : "";
        this.driverName = driverName != null ? driverName : "";
        this.teamId = teamId != null ? teamId : "";
        this.inPit = inPit;
        this.dist = dist;
        this.carDamage = validateDamageArray(carDamage);
        this.tcOn = tcOn;
        this.absOn = absOn;
    }

    private static float[] validateDamageArray(float[] array) {
        if (array == null) {
            throw new IllegalArgumentException("carDamage cannot be null");
        }
        if (array.length != DAMAGE_ZONES) {
            throw new IllegalArgumentException("carDamage must have " + DAMAGE_ZONES + " elements");
        }
        return array;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getTeamId() {
        return teamId;
    }

    public boolean isInPit() {
        return inPit;
    }

    public float getDist() {
        return dist;
    }

    public float[] getCarDamage() {
        return carDamage;
    }

    public boolean isTcOn() {
        return tcOn;
    }

    public boolean isAbsOn() {
        return absOn;
    }
}
