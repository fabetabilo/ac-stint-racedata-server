package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.nio.ByteBuffer;

/**
 * Telemetry Packet Header.
 * Estructura de header de packet, identificado por {@link PacketType} asociado a un driver
 */
public class PacketHeader {
    
    private final PacketType packetType;
    private final int driverIdx;

    private PacketHeader(PacketType packetType, int driverIdx) {
        this.packetType = packetType;
        this.driverIdx = driverIdx;
    }

    public static PacketHeader from(ByteBuffer buffer) {
        // importante: esto avanza el buffer
        int typeId = buffer.get() & 0xFF;  // leer 1 byte y convertir a int sin signo
        int driverIdx = buffer.get() & 0xFF;

        return new PacketHeader(PacketType.fromId(typeId), driverIdx);
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public int getDriverIdx() {
        return driverIdx;
    }
    
}
