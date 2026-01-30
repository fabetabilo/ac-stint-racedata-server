package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.InfoSample;

public class Info {
    
    private final String carNumber;
    private final String driverName;
    private final String teamId;
    private final boolean inPit;
    private final float dist;
    private final float[] carDamage;
    private final boolean tcOn;
    private final boolean absOn;

    public Info(String carNumber, String driverName, String teamId, boolean inPit,
            float dist, float[] carDamage, boolean tcOn, boolean absOn) {
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.teamId = teamId;
        this.inPit = inPit;
        this.dist = dist;
        this.carDamage = carDamage;
        this.tcOn = tcOn;
        this.absOn = absOn;
    }

    public static Info from(InfoSample s) {
        return new Info(
            s.getCarNumber(),
            s.getDriverName(),
            s.getTeamId(),
            s.isInPit(),
            s.getDist(),
            s.getCarDamage(),
            s.isTcOn(),
            s.isAbsOn()
        );
    }
}
