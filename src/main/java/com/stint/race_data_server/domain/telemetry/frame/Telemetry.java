package com.stint.race_data_server.domain.telemetry.frame;

import java.time.Instant;

import com.stint.race_data_server.domain.telemetry.data.Aerodynamic;
import com.stint.race_data_server.domain.telemetry.data.Gps;
import com.stint.race_data_server.domain.telemetry.data.Imu;
import com.stint.race_data_server.domain.telemetry.data.Info;
import com.stint.race_data_server.domain.telemetry.data.Input;
import com.stint.race_data_server.domain.telemetry.data.LiveTiming;
import com.stint.race_data_server.domain.telemetry.data.Suspension;
import com.stint.race_data_server.domain.telemetry.data.Tyre;

/**
 * Telemetry frame, 
 * representacion interna y temporal de un “instante” de telemetria
 */
public class Telemetry {
    
    private final int deviceId;
    private final Instant timestamp;

    private Info info;
    private Input input;
    private Imu imu;
    private Suspension susp;
    private LiveTiming timing;
    private Tyre tyre;
    private Aerodynamic aero;
    private Gps gps;

    public Telemetry(int deviceId, Instant timestamp) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
    }
    
    public int getDeviceId() {
        return deviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
    
    // setters controlados (package-private o explicitos)
    void setInfo(Info info) {
        this.info = info;
    }

    void setInput(Input input) {
        this.input = input;
    }

    void setImu(Imu imu) {
        this.imu = imu;
    }

    void setSusp(Suspension susp) {
        this.susp = susp;
    }

    void setLiveTiming(LiveTiming timing) {
        this.timing = timing;
    }

    void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }
    
    void setAero(Aerodynamic aero) {
        this.aero = aero;
    }

    void setGpsRadar(Gps gps) {
        this.gps = gps;
    }


    // getters, null-safe mas adelante
    public Info getInfo() {
        return info;
    }

    public Input getInput() {
        return input;
    }

    public Imu getImu() {
        return imu;
    }

    public Suspension getSusp() {
        return susp;
    }

    public LiveTiming getTiming() {
        return timing;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public Aerodynamic getAero() {
        return aero;
    }

    public Gps getGps() {
        return gps;
    }

}
