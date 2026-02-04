package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.nio.ByteBuffer;
import java.time.Instant;

/**
 * Telemetry Packet Header.
 * Estructura de header de packet de 10 bytes: BBQ (packet_id, device_id, timestamp)
 * - Byte 0: Packet ID (1-8)
 * - Byte 1: Device ID
 * - Bytes 2-9: Timestamp (microsegundos desde Unix epoch)
 */
public class PacketHeader {
    
    private final PacketType packetType;
    private final int deviceId;   // Device ID alude al coche que se esta evaluando
    private final long timestamp; // Timestamp en microsegundos desde Unix epoch (1970-01-01 00:00:00 UTC)

    private PacketHeader(PacketType packetType, int deviceId, long timestamp) {
        this.packetType = packetType;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }

    public static PacketHeader from(ByteBuffer buffer) {
        // importante: esto avanza el buffer (total 10 bytes)
        int typeId = buffer.get() & 0xFF;      // leer 1 byte y convertir a int sin signo
        int deviceId = buffer.get() & 0xFF;    // leer 1 byte y convertir a int sin signo
        long timestamp = buffer.getLong();     // leer 8 bytes (unsigned long long en Python = long en Java)

        return new PacketHeader(PacketType.fromId(typeId), deviceId, timestamp);
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Convierte el timestamp de microsegundos desde Unix epoch a Instant
     * @return Instant representando el momento de captura en el data logger
     */
    public Instant getTimestampAsInstant() {
        long seconds = timestamp / 1_000_000L;
        long nanos = (timestamp % 1_000_000L) * 1000L;
        return Instant.ofEpochSecond(seconds, nanos);
    }
    
}
