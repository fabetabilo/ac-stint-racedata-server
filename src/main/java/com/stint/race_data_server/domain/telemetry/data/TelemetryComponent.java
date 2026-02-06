package com.stint.race_data_server.domain.telemetry.data;

/**
 * Sealed interface que representa un componente de telemetría decodificado.
 * 
 * Cada tipo de dato que llega por cualquier adaptador de entrada (UDP datalogger,
 * race control, etc.) se convierte directamente en uno de estos tipos de dominio.
 * El sealed permite pattern matching exhaustivo sin default/else.
 */
public sealed interface TelemetryComponent 
    permits Info, Input, Imu, Suspension, LiveTiming, Gps, Tyre, Aerodynamic {
}
