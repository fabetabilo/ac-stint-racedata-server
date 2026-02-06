package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.LiveTiming;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class LiveTimingDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(LiveTimingDecoder.class);
    
    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
        
        try {
            if (buffer.remaining() < 24) {
                log.warn("LIVETIMING packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            int position = buffer.get() & 0xFF; // 1 byte
            int currentLapMs = buffer.getInt();
            float delta = buffer.getFloat();
            int sectorIdx = buffer.get() & 0xFF;
            int sectorTimeMs = buffer.getInt();
            int lastLapMs = buffer.getInt();
            int bestLapMs = buffer.getInt();
            int lapNum = buffer.getShort() & 0xFFFF; // 2 bytes
            boolean inPitLane = buffer.get() != 0;
            int flag = buffer.get() & 0xFF;
            
            return new LiveTiming(position, currentLapMs, delta, sectorIdx, 
                sectorTimeMs, lastLapMs, bestLapMs, lapNum, inPitLane, flag);
            
        } catch (Exception e) {
            log.error("Error decoding LIVETIMING: {}", e.getMessage());
            return null;
        }
    }

}
