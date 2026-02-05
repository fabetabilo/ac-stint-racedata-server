package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.AerodynamicSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class AerodynamicDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        try {
            if (buffer.remaining() < 28) {
                System.err.println("AERODYNAMIC packet too short: " + buffer.remaining() + " bytes");
                return null;
            }
            
            float drag = buffer.getFloat();
            float downforce = buffer.getFloat();
            float clFront = buffer.getFloat();
            float clRear = buffer.getFloat();
            float cd = buffer.getFloat();
            float rideHeightFront = buffer.getFloat();
            float rideHeightRear = buffer.getFloat();
            
            return new AerodynamicSample (
                header.getDeviceId(),
                header.getTimestampAsInstant(),
                drag,
                downforce,
                clFront,
                clRear,
                cd,
                rideHeightFront,
                rideHeightRear
            );
            
        } catch (Exception e) {
            System.err.println("Error decoding AERODYNAMIC: " + e.getMessage());
            return null;
        }
    }
    
}
