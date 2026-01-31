package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Se encarga de extraer el header {@link PacketHeader} e identifica el tipo de packet Little Endian
 */
public class PacketDecoder {
    
    private final PacketDispatcher dispatcher;

    public PacketDecoder(PacketDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public TelemetrySample decode(byte[] data, int length) {

        ByteBuffer buffer = ByteBuffer.wrap(data, 0, length).order(ByteOrder.LITTLE_ENDIAN);

        PacketHeader header = PacketHeader.from(buffer);

        return dispatcher.dispatch(buffer, header);

    }

}
