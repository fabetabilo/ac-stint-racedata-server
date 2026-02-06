package com.stint.race_data_server.domain.telemetry.frame;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import com.stint.race_data_server.domain.telemetry.data.Aerodynamic;
import com.stint.race_data_server.domain.telemetry.data.Gps;
import com.stint.race_data_server.domain.telemetry.data.Imu;
import com.stint.race_data_server.domain.telemetry.data.Info;
import com.stint.race_data_server.domain.telemetry.data.Input;
import com.stint.race_data_server.domain.telemetry.data.LiveTiming;
import com.stint.race_data_server.domain.telemetry.data.Suspension;
import com.stint.race_data_server.domain.telemetry.data.TelemetryComponent;
import com.stint.race_data_server.domain.telemetry.data.Tyre;

/**
 * Ensambla componentes de telemetria en frames por dispositivo.
 * mantiene un frame activo por deviceId, actualizandolo con cada componente recibido.
 */
public class TelemetryAssembler {

    private final ConcurrentHashMap<Integer, Telemetry> frames = new ConcurrentHashMap<>();

    public Telemetry getOrCreate(int deviceId, Instant timestamp) {
        return frames.computeIfAbsent(
            deviceId,
            k -> new Telemetry(k, timestamp)
        ); 
    }

    /**
     * Aplica componente de telemetria al frame del dispositivo correspondiente.
     */
    public Telemetry apply(int deviceId, Instant timestamp, TelemetryComponent component) {
        
        Telemetry frame = getOrCreate(deviceId, timestamp);
        frame.setTimestamp(timestamp);
        // switch sobre sealed interface, sin default necesario
        switch (component) {
            case Info info           -> frame.setInfo(info);
            case Input input         -> frame.setInput(input);
            case Imu imu             -> frame.setImu(imu);
            case Suspension susp     -> frame.setSusp(susp);
            case LiveTiming lt       -> frame.setLiveTiming(lt);
            case Tyre tyre           -> frame.setTyre(tyre);
            case Aerodynamic aero    -> frame.setAero(aero);
            case Gps gps             -> frame.setGpsRadar(gps);
        }

        return frame;
    }
}
