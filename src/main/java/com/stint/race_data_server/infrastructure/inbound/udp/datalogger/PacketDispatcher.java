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
            throw new IllegalStateException("No payload decoder for packet type " + header.getPacketType());   
        }
        return payloadDecoder.decode(buffer, header);

    }
    
}
