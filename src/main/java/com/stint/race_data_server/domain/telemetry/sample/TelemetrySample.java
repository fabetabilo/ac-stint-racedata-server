package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Clase base para muestras de paquetes de telemetria de un punto de datos, asociado a un piloto en un momento concreto.
 * Toda subclase debe heredar de esta {@link TelemetrySample}
 */
public abstract class TelemetrySample {

    private final int driverIdx;
    private final Instant timestamp;

    protected TelemetrySample(int driverIdx, Instant timestamp) {
        this.driverIdx = driverIdx;
        this.timestamp = timestamp;
    }

    public int getDriverIdx() {
        return driverIdx;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
