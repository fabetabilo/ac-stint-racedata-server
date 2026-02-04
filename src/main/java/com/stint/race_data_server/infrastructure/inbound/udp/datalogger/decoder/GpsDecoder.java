package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.GpsSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class GpsDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        float heading = buffer.getFloat();
        float x = buffer.getFloat();
        float z = buffer.getFloat();
        
        return new GpsSample(
            header.getDeviceId(),
            header.getTimestampAsInstant(),
            heading,
            x,
            z
        );
    }
    
}
