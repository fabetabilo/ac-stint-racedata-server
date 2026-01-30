package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de informacion general sobre driver y coche
 */
public class InfoSample extends TelemetrySample {

    private final String carNumber;
    private final String driverName;
    private final String teamId;
    private final boolean inPit;
    private final float dist;
    private final float[] carDamage;
    private final boolean tcOn;
    private final boolean absOn;

    public InfoSample(int driverIdx, Instant timestamp, String carNumber, String driverName, 
            String teamId, boolean inPit, float dist, float[] carDamage, boolean tcOn, boolean absOn) {
        super(driverIdx, timestamp);
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.teamId = teamId;
        this.inPit = inPit;
        this.dist = dist;
        this.carDamage = carDamage;
        this.tcOn = tcOn;
        this.absOn = absOn;
    }
    // getters
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

