package com.stint.race_data_server.infrastructure.inbound.udp.datalogger.decoder;

import java.nio.ByteBuffer;
import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.sample.SuspensionSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.PayloadDecoder;

public class SuspensionDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
    
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
        
        return new SuspensionSample(
            header.getDeviceId(),
            Instant.now(),
            suspensionTravel,
            camberRAD,
            wheelLoad,
            wheelAngularSpeed
        );                      
    }
}
