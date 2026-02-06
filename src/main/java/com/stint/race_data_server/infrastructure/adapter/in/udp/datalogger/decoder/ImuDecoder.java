package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Imu;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class ImuDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(ImuDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
        
        try {
            if (buffer.remaining() < 28) {
                log.warn("IMU packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            float accX = buffer.getFloat();
            float accY = buffer.getFloat();
            float accZ = buffer.getFloat();
            
            float roll = buffer.getFloat();
            float pitch = buffer.getFloat();
            float yawRate = buffer.getFloat();
            float sideSlip = buffer.getFloat();

            return new Imu(accX, accY, accZ, roll, pitch, yawRate, sideSlip);
            
        } catch (Exception e) {
            log.error("Error decoding IMU: {}", e.getMessage());
            return null;
        }
    }
}
