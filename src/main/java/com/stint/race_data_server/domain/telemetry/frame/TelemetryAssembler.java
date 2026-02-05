package com.stint.race_data_server.domain.telemetry.frame;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.stint.race_data_server.domain.telemetry.data.Aerodynamic;
import com.stint.race_data_server.domain.telemetry.data.Gps;
import com.stint.race_data_server.domain.telemetry.data.Imu;
import com.stint.race_data_server.domain.telemetry.data.Info;
import com.stint.race_data_server.domain.telemetry.data.Input;
import com.stint.race_data_server.domain.telemetry.data.LiveTiming;
import com.stint.race_data_server.domain.telemetry.data.Suspension;
import com.stint.race_data_server.domain.telemetry.data.Tyre;
import com.stint.race_data_server.domain.telemetry.sample.AerodynamicSample;
import com.stint.race_data_server.domain.telemetry.sample.GpsSample;
import com.stint.race_data_server.domain.telemetry.sample.ImuSample;
import com.stint.race_data_server.domain.telemetry.sample.InfoSample;
import com.stint.race_data_server.domain.telemetry.sample.InputSample;
import com.stint.race_data_server.domain.telemetry.sample.LiveTimingSample;
import com.stint.race_data_server.domain.telemetry.sample.SuspensionSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.domain.telemetry.sample.TyreSample;

@Component
public class TelemetryAssembler {

    private final ConcurrentHashMap<Integer, Telemetry> frames = new ConcurrentHashMap<>();

    public Telemetry getOrCreate(int deviceId, Instant timestamp) {
        return frames.computeIfAbsent(
            deviceId,
            k -> new Telemetry(k, timestamp)
        ); 
    }
    public Telemetry apply(TelemetrySample sample) {

        if (sample == null) {
            // paquete corrupto ya descartado en decoder, -> no hacer nada
            return null;
        }
        
        Telemetry frame = getOrCreate(sample.getDeviceId(), sample.getTimestamp());
        // Actualizar timestamp del frame con el del sample (cada sample trae su propio timestamp)
        frame.setTimestamp(sample.getTimestamp());

        if (sample instanceof InfoSample s) {
            frame.setInfo(Info.from(s));
        }
        else if (sample instanceof InputSample s) {
            frame.setInput(Input.from(s));
        }
        else if (sample instanceof ImuSample s) {
            frame.setImu(Imu.from(s));
        }
        else if (sample instanceof SuspensionSample s) {
            frame.setSusp(Suspension.from(s));
        }
        else if (sample instanceof LiveTimingSample s) {
            frame.setLiveTiming(LiveTiming.from(s));
        }
        else if (sample instanceof TyreSample s) {
            frame.setTyre(Tyre.from(s));
        }
        else if (sample instanceof AerodynamicSample s) {
            frame.setAero(Aerodynamic.from(s));
        }
        else if (sample instanceof GpsSample s) {
            frame.setGpsRadar(Gps.from(s));
        }

        return frame;
    }
}
