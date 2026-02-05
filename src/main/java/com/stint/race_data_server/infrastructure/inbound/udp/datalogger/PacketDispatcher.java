package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.nio.ByteBuffer;
import java.util.Map;

import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Enrutador de packets a decoders especificos segun packetId
 */
public class PacketDispatcher {

    private final Map<PacketType, PayloadDecoder> decoders;

    public PacketDispatcher(Map<PacketType, PayloadDecoder> decoders) {
        this.decoders = decoders;
    }

    public TelemetrySample dispatch(ByteBuffer buffer, PacketHeader header) {

        PayloadDecoder payloadDecoder = decoders.get(header.getPacketType());

        if (payloadDecoder == null) {
            System.err.println("No payload decoder for packet type " + header.getPacketType());
            return null;
        }
        
        TelemetrySample sample = payloadDecoder.decode(buffer, header);
        
        if (sample == null) {
            // Decoder ya logueó el error, solo descartamos el paquete silenciosamente
            return null;
        }
        
        return sample;
    }
    
}
