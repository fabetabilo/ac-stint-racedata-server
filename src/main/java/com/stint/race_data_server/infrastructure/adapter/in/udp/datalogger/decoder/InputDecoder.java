package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Input;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class InputDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(InputDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {

        try {
            if (buffer.remaining() < 44) {
                log.warn("INPUT packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
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
            
            return new Input(rpm, turbo, speedKmh, gear, throttle, brake, 
                clutch, steer, fuel, kersCharge, kersInput);
            
        } catch (Exception e) {
            log.error("Error decoding INPUT: {}", e.getMessage());
            return null;
        }
    }
}
   
