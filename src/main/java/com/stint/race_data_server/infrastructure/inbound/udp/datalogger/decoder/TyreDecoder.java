package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.domain.telemetry.sample.TyreSample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class TyreDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
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

        return new TyreSample(
            header.getDriverIdx(),
            Instant.now(),
            compound,
            coreTemps,
            pressures,
            dirtLevels,
            wearLevels,
            slipRatios
        );
    }

}
