package com.stint.race_data_server.application.service;

import org.springframework.stereotype.Service;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;
import com.stint.race_data_server.application.port.out.LogTelemetry;
import com.stint.race_data_server.domain.telemetry.frame.Telemetry;
import com.stint.race_data_server.domain.telemetry.frame.TelemetryAssembler;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Servicio de Aplicación - Gestión de Telemetría
 * 
 * Implementa puerto {@link ReceiveTelemetry} y orquesta el procesamiento de samples de telemetria,
 * ensamblando samples en frames completos usando {@link TelemetryAssembler}
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
    public Telemetry handle(TelemetrySample sample) {

        Telemetry frame = assembler.apply(sample);
        logTelemetry.debug(frame);

        // aqui puede ir la logica de procesamiento de telemetria, guardar en bd, publicar eventos, etc

        return frame;

    }

}

