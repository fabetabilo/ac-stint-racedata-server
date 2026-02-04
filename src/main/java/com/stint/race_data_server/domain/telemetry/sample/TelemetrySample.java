package com.stint.race_data_server.domain.telemetry.sample;

import java.time.Instant;

/**
 * Clase base para muestras de paquetes de telemetria de un punto de datos, asociado a un piloto en un momento concreto.
 * Toda subclase debe heredar de esta {@link TelemetrySample}
 */
public abstract class TelemetrySample {

    private final int deviceId;
    private final Instant timestamp;

    protected TelemetrySample(int deviceId, Instant timestamp) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
