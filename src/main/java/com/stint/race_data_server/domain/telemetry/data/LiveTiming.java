package com.stint.race_data_server.domain.telemetry.data;

public final class LiveTiming implements TelemetryComponent {
    
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

    public LiveTiming(int position, int currentLapMs, float delta, int sectorIdx, int sectorTimeMs, int lastLapMs, 
            int bestLapMs, int lapCount, boolean inPitLane, int flag) {
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

    public int getLapCount() {
        return lapCount;
    }

    public int getSectorIdx() {
        return sectorIdx;
    }

    public int getPosition() {
        return position;
    }

    public int getCurrentLapMs() {
        return currentLapMs;
    }

    public float getDelta() {
        return delta;
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

    public boolean isInPitLane() {
        return inPitLane;
    }

    public int getFlag() {
        return flag;
    }
}
