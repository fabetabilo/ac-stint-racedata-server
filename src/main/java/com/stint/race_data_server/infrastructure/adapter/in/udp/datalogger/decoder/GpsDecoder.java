package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.GpsSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class GpsDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        try {
            if (buffer.remaining() < 12) {
                System.err.println("GPS packet too short: " + buffer.remaining() + " bytes");
                return null;
            }
            
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
            
        } catch (Exception e) {
            System.err.println("Error decoding GPS: " + e.getMessage());
            return null;
        }
    }
    
}
