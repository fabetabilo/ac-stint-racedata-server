package com.stint.race_data_server.application.service;

import org.springframework.stereotype.Service;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;
import com.stint.race_data_server.domain.telemetry.frame.Telemetry;
import com.stint.race_data_server.domain.telemetry.frame.TelemetryAssembler;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Implementer de {@link ReceiveTelemetry}
 */
@Service
public class TelemetryService implements ReceiveTelemetry{
    
    private final TelemetryAssembler assembler;
    
    public TelemetryService(TelemetryAssembler assembler) {
        this.assembler = assembler;
    }

    public Telemetry handle(TelemetrySample sample) {

        Telemetry frame = assembler.apply(sample);

        // aqui puede ir la logica de procesamiento de telemetria, guardar en bd, publicar eventos, etc

        return frame;

    }

}
