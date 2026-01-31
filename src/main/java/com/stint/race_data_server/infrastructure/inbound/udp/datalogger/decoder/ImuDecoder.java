package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.sample.ImuSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class ImuDecoder implements PayloadDecoder{

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
        
        float accX = buffer.getFloat();
        float accY = buffer.getFloat();
        float accZ = buffer.getFloat();
        
        float roll = buffer.getFloat();
        float pitch = buffer.getFloat();

        return new ImuSample(
            header.getDriverIdx(),
            Instant.now(),
            accX,
            accY,
            accZ,
            roll,
            pitch
        );
    }
}
