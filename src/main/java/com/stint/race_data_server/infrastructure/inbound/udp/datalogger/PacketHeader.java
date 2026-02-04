package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.nio.ByteBuffer;

/**
 * Telemetry Packet Header.
 * Estructura de header de packet, identificado por {@link PacketType} asociado al dispositivo data logger de un vehiculo, por Device ID
 */
public class PacketHeader {
    
    private final PacketType packetType;
    private final int deviceId;   // Device ID alude al coche que se esta evaluando

    private PacketHeader(PacketType packetType, int deviceId) {
        this.packetType = packetType;
        this.deviceId = deviceId;
    }

    public static PacketHeader from(ByteBuffer buffer) {
        // importante: esto avanza el buffer
        int typeId = buffer.get() & 0xFF;  // leer 1 byte y convertir a int sin signo
        int deviceId = buffer.get() & 0xFF;

        return new PacketHeader(PacketType.fromId(typeId), deviceId);
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public int getDeviceId() {
        return deviceId;
    }
    
}
