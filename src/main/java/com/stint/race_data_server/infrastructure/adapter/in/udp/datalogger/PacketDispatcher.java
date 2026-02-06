package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.nio.ByteBuffer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;

/**
 * Enrutador de packets a decoders especificos segun {@link PacketType}
 */
public class PacketDispatcher {
    
    private static final Logger log = LoggerFactory.getLogger(PacketDispatcher.class);

    private final Map<PacketType, PayloadDecoder> decoders;

    public PacketDispatcher(Map<PacketType, PayloadDecoder> decoders) {
        this.decoders = decoders;
    }

    public TelemetryComponent dispatch(ByteBuffer buffer, PacketType packetType) {

        PayloadDecoder payloadDecoder = decoders.get(packetType);

        if (payloadDecoder == null) {
            log.warn("No payload decoder for packet type {}", packetType);
            return null;
        }
        
        return payloadDecoder.decode(buffer);
    }
    
}
