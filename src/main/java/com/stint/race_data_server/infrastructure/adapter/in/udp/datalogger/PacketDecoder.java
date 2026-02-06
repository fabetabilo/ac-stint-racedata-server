package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;

/**
 * Extrae el header {@link PacketHeader}, decodifica el payload via dispatcher, 
 * y empaqueta el resultado en {@link DecodedData}
 */
public class PacketDecoder {
    
    private final PacketDispatcher dispatcher;

    public PacketDecoder(PacketDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public DecodedData decode(byte[] data, int length) {

        ByteBuffer buffer = ByteBuffer.wrap(data, 0, length).order(ByteOrder.LITTLE_ENDIAN);

        PacketHeader header = PacketHeader.from(buffer);

        TelemetryComponent component = dispatcher.dispatch(buffer, header.getPacketType());

        if (component == null) {
            return null;
        }
        
        return new DecodedData(
            header.getDeviceId(), 
            header.getTimestampAsInstant(), 
            component
        );
    }
}
