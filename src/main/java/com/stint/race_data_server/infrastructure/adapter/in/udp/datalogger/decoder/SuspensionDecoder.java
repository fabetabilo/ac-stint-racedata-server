package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.SuspensionSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PacketHeader;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;

public class SuspensionDecoder implements PayloadDecoder {

    @Override
    public TelemetrySample decode(ByteBuffer buffer, PacketHeader header) {
    
        try {
            if (buffer.remaining() < 64) {
                System.err.println("SUSPENSION packet too short: " + buffer.remaining() + " bytes");
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
            
            return new SuspensionSample(
                header.getDeviceId(),
                header.getTimestampAsInstant(),
                suspensionTravel,
                camberRAD,
                wheelLoad,
                wheelAngularSpeed
            );

        } catch (Exception e) {
            System.err.println("Error decoding SUSPENSION: " + e.getMessage());
            return null;
        }
    }
}
