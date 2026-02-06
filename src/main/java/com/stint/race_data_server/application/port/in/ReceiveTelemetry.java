package com.stint.race_data_server.application.port.in;

import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.domain.telemetry.frame.Telemetry;

/**
 * Puerto de entrada que define useCase: Recibir Telemetria
 */
public interface ReceiveTelemetry {
    
    Telemetry handle(int deviceId, Instant timestamp, TelemetryComponent data);

}
