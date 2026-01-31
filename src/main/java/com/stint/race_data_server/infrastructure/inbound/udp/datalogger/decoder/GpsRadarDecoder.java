package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.stint.race_data_server.domain.telemetry.sample.GpsRadarSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.domain.telemetry.sample.GpsRadarSample.RadarBlip;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class GpsRadarDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        // base GPS 13 bytes con struct '<fffB'
        float heading = buffer.getFloat();
        float x = buffer.getFloat();
        float z = buffer.getFloat();
        int carCount = buffer.get() & 0xFF; // 1 byte unsigned
        
        // radar blips dinamicos - cada uno 'Bff' (1 + 4 + 4 = 9 bytes)
        List<RadarBlip> nearbyCars = new ArrayList<>();
        for (int i = 0; i < carCount; i++) {
            byte carId = buffer.get();
            float carX = buffer.getFloat();
            float carZ = buffer.getFloat();
            nearbyCars.add(new RadarBlip(carId, carX, carZ));
        }
        
        return new GpsRadarSample(
            header.getDriverIdx(),
            Instant.now(),
            heading,
            x,
            z,
            nearbyCars
        );
    }
    
}
