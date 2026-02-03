package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

/**
 * Telemetry Packet Type.
 * Representa el tipo de packet identificado por id
 */
public enum PacketType {
    
    INFO(1), // PKT_INFO [...]
    INPUT(2),
    IMU(3),
    SUSP(4),
    LIVE_TIMING(5),
    GPS(6),
    TYRE(7),
    AERO(8);

    private final int id;

    PacketType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PacketType fromId(int id) {
        for (PacketType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown PacketType (PKT) id: " + id);
    }

}
