package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Info;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class InfoDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(InfoDecoder.class);
    
    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {

        try {
            if (buffer.remaining() < 85) {
                log.warn("INFO packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            byte[] carNumberBytes = new byte[4];
            buffer.get(carNumberBytes);
            String carNumber = new String(carNumberBytes, StandardCharsets.UTF_8).trim().replace("\0", "");
            
            byte[] driverNameBytes = new byte[32];
            buffer.get(driverNameBytes);
            String driverName = new String(driverNameBytes, StandardCharsets.UTF_8).trim().replace("\0", "");
            
            byte[] teamIdBytes = new byte[20];
            buffer.get(teamIdBytes);
            String teamId = new String(teamIdBytes, StandardCharsets.UTF_8).trim().replace("\0", "");
            
            boolean inPit = buffer.get() != 0;
            
            float dist = buffer.getFloat();
            
            float[] carDamage = new float[5]; // 5 zonas: front - left - right - rear - engine
            for (int i = 0; i < 5; i++) {
                carDamage[i] = buffer.getFloat();
            }
            
            boolean tcOn = buffer.get() != 0;
            
            boolean absOn = buffer.get() != 0;

            return new Info(carNumber, driverName, teamId, inPit, dist, carDamage, tcOn, absOn);
            
        } catch (Exception e) {
            log.error("Error decoding INFO: {}", e.getMessage());
            return null;
        }
    }

}
