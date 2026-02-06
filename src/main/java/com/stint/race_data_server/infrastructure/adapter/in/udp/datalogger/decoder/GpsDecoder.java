package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Gps;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class GpsDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(GpsDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
        
        try {
            if (buffer.remaining() < 12) {
                log.warn("GPS packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            float heading = buffer.getFloat();
            float x = buffer.getFloat();
            float z = buffer.getFloat();
            
            return new Gps(heading, x, z);
            
        } catch (Exception e) {
            log.error("Error decoding GPS: {}", e.getMessage());
            return null;
        }
    }
    
}
