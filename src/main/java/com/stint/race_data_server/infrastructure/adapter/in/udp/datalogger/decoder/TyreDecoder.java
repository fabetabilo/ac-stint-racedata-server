package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.domain.telemetry.data.Tyre;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class TyreDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(TyreDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
        
        try {
            if (buffer.remaining() < 90) {
                log.warn("TYRE packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            // [4] = FL, FR, RL, RR
            
            byte[] compoundBytes = new byte[10]; // !!REVISAR mas adelante
            buffer.get(compoundBytes);
            String compound = new String(compoundBytes, StandardCharsets.UTF_8).trim().replace("\0", "");

            float[] coreTemps = new float[4];
            for (int i = 0; i < 4; i++) {
                coreTemps[i] = buffer.getFloat();
            }

            float[] pressures = new float[4];
            for (int i = 0; i < 4; i++) {
                pressures[i] = buffer.getFloat();
            }

            float[] dirtLevels = new float[4];
            for (int i = 0; i < 4; i++) {
                dirtLevels[i] = buffer.getFloat();
            }

            float[] wearLevels = new float[4];
            for (int i = 0; i < 4; i++) {
                wearLevels[i] = buffer.getFloat();
            }

            float[] slipRatios = new float[4];
            for (int i = 0; i < 4; i++) {
                slipRatios[i] = buffer.getFloat();
            }

            return new Tyre(compound, coreTemps, pressures, dirtLevels, wearLevels, slipRatios);

        } catch (Exception e) {
            log.error("Error decoding TYRE: {}", e.getMessage());
            return null;
        }
    }

}
