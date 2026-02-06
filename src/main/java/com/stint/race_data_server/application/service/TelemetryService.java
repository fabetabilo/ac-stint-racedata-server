package com.stint.race_data_server.application.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;
import com.stint.race_data_server.application.port.out.LogTelemetry;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.domain.telemetry.frame.Telemetry;
import com.stint.race_data_server.domain.telemetry.frame.TelemetryAssembler;

/**
 * Servicio de Aplicación - Gestión de Telemetría
 * 
 * Implementa puerto {@link ReceiveTelemetry} y orquesta el procesamiento de componentes 
 * de telemetria, ensamblando en frames completos usando {@link TelemetryAssembler}
 */
@Service
public class TelemetryService implements ReceiveTelemetry{
    
    private final TelemetryAssembler assembler;
    private final LogTelemetry logTelemetry;
    
    public TelemetryService(TelemetryAssembler assembler, LogTelemetry logTelemetry) {
        this.assembler = assembler;
        this.logTelemetry = logTelemetry;
    }

    @Override
    public Telemetry handle(int deviceId, Instant timestamp, TelemetryComponent data) {

        Telemetry frame = assembler.apply(deviceId, timestamp, data);
        logTelemetry.debug(frame);

        // aqui puede ir la logica de procesamiento de telemetria, guardar en bd, publicar eventos, etc

        return frame;

    }

}

