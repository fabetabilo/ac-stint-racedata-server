package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;

/**
 * Resultado de decodificar un paquete UDP completo: 
 * metadatos del header + dato de dominio.
 * record liviano que transporta la informacion del header junto al componente decodificado,
 */
public record DecodedData(int deviceId, Instant timestamp, TelemetryComponent data) {
    
}
