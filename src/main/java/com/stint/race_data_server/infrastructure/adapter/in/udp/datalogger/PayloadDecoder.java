package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;

/**
 * Interface para sub-decoders de payload.
 * Cada implementacion extrae datos binarios del buffer y produce
 * directamente un objeto de dominio {@link TelemetryComponent}.
 */
public interface PayloadDecoder {
    
    /**
     * Decodifica el payload binario en un componente de telemetria,
     * no recibe header: los metadatos deviceId y timestamp se gestionan en capas superiores
     */
    TelemetryComponent decode(ByteBuffer buffer);
    
}
