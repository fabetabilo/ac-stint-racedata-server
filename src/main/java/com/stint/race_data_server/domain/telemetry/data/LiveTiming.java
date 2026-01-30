package com.stint.race_data_server.domain.telemetry.data;

import com.stint.race_data_server.domain.telemetry.sample.LiveTimingSample;

public class LiveTiming {
    
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

    public static LiveTiming from(LiveTimingSample s) {
        return new LiveTiming(
            s.getPosition(),
            s.getCurrentLapMs(),
            s.getDelta(),
            s.getSectorIdx(),
            s.getSectorTimeMs(),
            s.getLastLapMs(),
            s.getBestLapMs(),
            s.getLapCount(),
            s.isInPitLane(),
            s.getFlag()
        );
    }
}
