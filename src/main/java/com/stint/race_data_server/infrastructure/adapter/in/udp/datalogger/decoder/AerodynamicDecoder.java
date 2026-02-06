package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Aerodynamic;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class AerodynamicDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(AerodynamicDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
        
        try {
            if (buffer.remaining() < 28) {
                log.warn("AERODYNAMIC packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            float drag = buffer.getFloat();
            float downforce = buffer.getFloat();
            float clFront = buffer.getFloat();
            float clRear = buffer.getFloat();
            float cd = buffer.getFloat();
            float rideHeightFront = buffer.getFloat();
            float rideHeightRear = buffer.getFloat();
            
            return new Aerodynamic(drag, downforce, clFront, clRear, cd, 
                rideHeightFront, rideHeightRear);
            
        } catch (Exception e) {
            log.error("Error decoding AERODYNAMIC: {}", e.getMessage());
            return null;
        }
    }
    
}
