package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.LiveTimingSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class LiveTimingDecoder implements PayloadDecoder{
    
    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
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
        
        return new LiveTimingSample(
            header.getDeviceId(),
            header.getTimestampAsInstant(),
            position,
            currentLapMs,
            delta,
            sectorIdx,
            sectorTimeMs,
            lastLapMs,
            bestLapMs,
            lapNum,
            inPitLane,
            flag
        );
    }

}
