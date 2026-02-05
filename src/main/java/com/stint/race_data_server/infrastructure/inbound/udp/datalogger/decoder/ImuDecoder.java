package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.ImuSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class ImuDecoder implements PayloadDecoder{

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        try {
            if (buffer.remaining() < 28) {
                System.err.println("IMU packet too short: " + buffer.remaining() + " bytes");
                return null;
            }
            
            float accX = buffer.getFloat();
            float accY = buffer.getFloat();
            float accZ = buffer.getFloat();
            
            float roll = buffer.getFloat();
            float pitch = buffer.getFloat();
            float yawRate = buffer.getFloat();
            float sideSlip = buffer.getFloat();

            return new ImuSample(
                header.getDeviceId(),
                header.getTimestampAsInstant(),
                accX,
                accY,
                accZ,
                roll,
                pitch,
                yawRate,
                sideSlip
            );
            
        } catch (Exception e) {
            System.err.println("Error decoding IMU: " + e.getMessage());
            return null;
        }
    }
}
