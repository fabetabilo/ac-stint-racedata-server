package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.sample.InputSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class InputDecoder implements PayloadDecoder{

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {

        try {
            long rpm = Integer.toUnsignedLong(buffer.getInt());
            float turbo = buffer.getFloat();
            float speedKmh = buffer.getFloat();
            int gear = (int) buffer.getFloat();
            float throttle = buffer.getFloat();
            float brake = buffer.getFloat();
            float clutch = buffer.getFloat();
            float steer = buffer.getFloat();
            float fuel = buffer.getFloat();
            float kersCharge = buffer.getFloat();
            float kersInput = buffer.getFloat();
            
            return new InputSample(
                header.getDriverIdx(),
                Instant.now(),
                rpm,
                turbo,
                speedKmh,
                gear,
                throttle,
                brake,
                clutch,
                steer,
                fuel,
                kersCharge,
                kersInput
            );
        } catch (Exception e) {
            System.err.println("Error decoding INPUT: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
   
