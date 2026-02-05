package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.stint.race_data_server.domain.telemetry.sample.InfoSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class InfoDecoder implements PayloadDecoder {
    
    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {

        try {
            if (buffer.remaining() < 85) {
                System.err.println("INFO packet too short: " + buffer.remaining() + " bytes");
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

            return new InfoSample(
                header.getDeviceId(),
                header.getTimestampAsInstant(),
                carNumber,
                driverName,
                teamId,
                inPit,
                dist,
                carDamage,
                tcOn,
                absOn
            );
            
        } catch (Exception e) {
            System.err.println("Error decoding INFO: " + e.getMessage());
            return null;
        }
    }

}
