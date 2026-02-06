package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.Suspension;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class SuspensionDecoder implements PayloadDecoder {

    private static final Logger log = LoggerFactory.getLogger(SuspensionDecoder.class);

    @Override
    public TelemetryComponent decode(ByteBuffer buffer) {
    
        try {
            if (buffer.remaining() < 64) {
                log.warn("SUSPENSION packet too short: {} bytes", buffer.remaining());
                return null;
            }
            
            // [4] = FL, FR, RL, RR
            
            float[] suspensionTravel = new float[4];
            for (int i = 0; i < 4; i++) {
                suspensionTravel[i] = buffer.getFloat();
            }
            
            float[] camberRAD = new float[4];
            for (int i = 0; i < 4; i++) {
                camberRAD[i] = buffer.getFloat();
            }
            
            float[] wheelLoad = new float[4];
            for (int i = 0; i < 4; i++) {
                wheelLoad[i] = buffer.getFloat();
            }
            
            float[] wheelAngularSpeed = new float[4];
            for (int i = 0; i < 4; i++) {
                wheelAngularSpeed[i] = buffer.getFloat();
            }
            
            return new Suspension(suspensionTravel, camberRAD, wheelLoad, wheelAngularSpeed);

        } catch (Exception e) {
            log.error("Error decoding SUSPENSION: {}", e.getMessage());
            return null;
        }
    }
}
