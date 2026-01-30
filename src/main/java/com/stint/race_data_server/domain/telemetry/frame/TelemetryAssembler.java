package com.stint.race_data_server.domain.telemetry.frame;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import com.stint.race_data_server.domain.telemetry.data.Aerodynamic;
import com.stint.race_data_server.domain.telemetry.data.GpsRadar;
import com.stint.race_data_server.domain.telemetry.data.Imu;
import com.stint.race_data_server.domain.telemetry.data.Info;
import com.stint.race_data_server.domain.telemetry.data.Input;
import com.stint.race_data_server.domain.telemetry.data.LiveTiming;
import com.stint.race_data_server.domain.telemetry.data.Suspension;
import com.stint.race_data_server.domain.telemetry.data.Tyre;
import com.stint.race_data_server.domain.telemetry.sample.AerodynamicSample;
import com.stint.race_data_server.domain.telemetry.sample.GpsRadarSample;
import com.stint.race_data_server.domain.telemetry.sample.ImuSample;
import com.stint.race_data_server.domain.telemetry.sample.InfoSample;
import com.stint.race_data_server.domain.telemetry.sample.InputSample;
import com.stint.race_data_server.domain.telemetry.sample.LiveTimingSample;
import com.stint.race_data_server.domain.telemetry.sample.SuspensionSample;
import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;
import com.stint.race_data_server.domain.telemetry.sample.TyreSample;

public class TelemetryAssembler {

    private final ConcurrentHashMap<Integer, Telemetry> frames = new ConcurrentHashMap<>();

    public Telemetry getOrCreate(int driverIdx) {
        return frames.computeIfAbsent(
            driverIdx,
            k -> new Telemetry(k, Instant.now()) // lambda truco c:
        ); 
    }
    public Telemetry apply(TelemetrySample sample) {

        Telemetry frame = getOrCreate(sample.getDriverIdx());

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
        else if (sample instanceof GpsRadarSample s) {
            frame.setGpsRadar(GpsRadar.from(s));
        }

        return frame;
    }
}
