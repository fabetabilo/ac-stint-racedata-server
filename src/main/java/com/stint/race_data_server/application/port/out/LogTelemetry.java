package com.stint.race_data_server.application.port.out;

import com.stint.race_data_server.domain.telemetry.frame.Telemetry;

/**
 * Define operacion de logging/debug para frames de telemetría, se
 * debe implementar por out adapter en infrastructure.
 */
public interface LogTelemetry {
    
    void debug(Telemetry frame);
    
}
