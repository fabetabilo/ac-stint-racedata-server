package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de estado de neumaticos
 */
public class TyreSample extends TelemetrySample {

    private final String compound;

    private final float[] coreTemps; // FL, FR, RL, RR
    private final float[] pressures;
    private final float[] dirtLevels;
    private final float[] wearLevels;
    private final float[] slipRatios;

    public TyreSample(int deviceId, Instant ts, String compound, float[] coreTemps,
            float[] pressures, float[] dirtLevels, float[] wearLevels, float[] slipRatios) {
        super(deviceId, ts);
        this.compound = compound;
        this.coreTemps = coreTemps;
        this.pressures = pressures;
        this.dirtLevels = dirtLevels;
        this.wearLevels = wearLevels;
        this.slipRatios = slipRatios;
    }
    // getters
    public String getCompound() {
        return compound;
    }

    public float[] getCoreTemps() {
        return coreTemps;
    }

    public float[] getPressures() {
        return pressures;
    }

    public float[] getDirtLevels() {
        return dirtLevels;
    }

    public float[] getWearLevels() {
        return wearLevels;
    }

    public float[] getSlipRatios() {
        return slipRatios;
    }
}

