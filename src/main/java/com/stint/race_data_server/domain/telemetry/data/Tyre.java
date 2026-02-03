package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.TyreSample;

public class Tyre {
    
    private final String compound;
    private final float[] coreTemps; // temporal!: separar datos por FL, FR, RL, RR en futuro
    private final float[] pressures;
    private final float[] dirtLevels;
    private final float[] wearLevels;
    private final float[] slipRatios;

    public Tyre(String compound, float[] coreTemps, float[] pressures,
            float[] dirtLevels, float[] wearLevels, float[] slipRatios) {
        this.compound = compound;
        this.coreTemps = coreTemps;
        this.pressures = pressures;
        this.dirtLevels = dirtLevels;
        this.wearLevels = wearLevels;
        this.slipRatios = slipRatios;
    }

    public static Tyre from(TyreSample s) {
        return new Tyre(
            s.getCompound(),
            s.getCoreTemps(),
            s.getPressures(),
            s.getDirtLevels(),
            s.getWearLevels(),
            s.getSlipRatios()
        );
    }
    
    public float[] getCoreTemps() {
        return coreTemps;
    }

    public String getCompound() {
        return compound;
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
