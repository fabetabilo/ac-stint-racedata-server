package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Muestra de tiempos en tiempo real y datos extra
 */
public class LiveTimingSample extends TelemetrySample {

    private final int position;
    private final int currentLapMs;
    private final float delta;
    private final int sectorIdx;
    private final int sectorTimeMs;
    private final int lastLapMs;
    private final int bestLapMs;
    private final int lapCount;
    private final boolean inPitLane;
    private final int flag;

    public LiveTimingSample(int deviceId, Instant ts, int position, int currentLapMs, float delta, int sectorIdx,
            int sectorTimeMs, int lastLapMs, int bestLapMs, int lapCount, boolean inPitLane, int flag) {
        super(deviceId, ts);
        this.position = position;
        this.currentLapMs = currentLapMs;
        this.delta = delta;
        this.sectorIdx = sectorIdx;
        this.sectorTimeMs = sectorTimeMs;
        this.lastLapMs = lastLapMs;
        this.bestLapMs = bestLapMs;
        this.lapCount = lapCount;
        this.inPitLane = inPitLane;
        this.flag = flag;
    }
    // getters
    public int getPosition() {
        return position;
    }

    public int getCurrentLapMs() {
        return currentLapMs;
    }

    public float getDelta() {
        return delta;
    }

    public int getSectorIdx() {
        return sectorIdx;
    }

    public int getSectorTimeMs() {
        return sectorTimeMs;
    }

    public int getLastLapMs() {
        return lastLapMs;
    }

    public int getBestLapMs() {
        return bestLapMs;
    }

    public int getLapCount() {
        return lapCount;
    }

    public boolean isInPitLane() {
        return inPitLane;
    }

    public int getFlag() {
        return flag;
    }
}
