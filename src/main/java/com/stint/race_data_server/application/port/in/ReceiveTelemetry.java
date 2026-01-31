package com.stint.race_data_server.application.port.in;

import com.stint.race_data_server.domain.telemetry.frame.Telemetry;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Puerto de entrada que define useCase: Recibir Telemetria
 */
public interface ReceiveTelemetry {
    
    Telemetry handle(TelemetrySample sample);

}
