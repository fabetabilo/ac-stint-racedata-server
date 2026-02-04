package com.stint.race_data_server.application.service.debug;

import java.time.Instant;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stint.race_data_server.domain.telemetry.frame.Telemetry;
import com.stint.race_data_server.domain.telemetry.data.*;

/**
 * TelemetryDebug = debug de unidad data logger, su objetivo es
 * verificar los paquetes y su integridad a traves de una configuracion 
 */
@Service
public class TelemetryDebug {

    private static final Logger log = LoggerFactory.getLogger(TelemetryDebug.class);

    private final boolean enabled;
    private final long minIntervalMs;
    private final Set<DebugField> activeFields;

    private final ConcurrentMap<Integer, Instant> lastPrint = new ConcurrentHashMap<>();

    public enum DebugField {
        INFO,
        INPUT,
        IMU,
        SUSP,
        TIMING,
        AERO,
        TYRE,
        GPS
    }

    public TelemetryDebug(
            @Value("${datalogger.debug.enabled:false}") boolean enabled,
            @Value("${datalogger.debug.rate-hz:0.5}") int rateHz,
            @Value("${datalogger.debug.fields:}") String fields
        ) {
            this.enabled = enabled;
            this.minIntervalMs = 1000L / Math.max(rateHz, 1);
            this.activeFields = parseFields(fields);
    }

    private Set<DebugField> parseFields(String fieldsStr) {

        Set<DebugField> result = EnumSet.noneOf(DebugField.class);

        if (fieldsStr == null || fieldsStr.isBlank()) {
            return result;
        }

        String[] parts = fieldsStr.split(",");
        for (String part : parts) {
            try {
                result.add(DebugField.valueOf(part.trim()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown debug field: {}", part);
            }
        }
        return result;
    }

    public void debug(Telemetry frame) {

        if (!enabled || frame == null)
            return;

        int deviceId = frame.getDeviceId(); // Device ID alude al coche al que se esta evaluando telemetria
        Instant frameTimestamp = frame.getTimestamp(); // timestamp de captura desde data logger

        Instant last = lastPrint.get(deviceId);
        if (last != null && frameTimestamp.toEpochMilli() - last.toEpochMilli() < minIntervalMs) {
            log.debug("Filtered frame for device {} - timestamp: {}, last: {}, diff: {}ms", 
                deviceId, frameTimestamp.toEpochMilli(), last.toEpochMilli(), 
                frameTimestamp.toEpochMilli() - last.toEpochMilli());
            return;
        }

        lastPrint.put(deviceId, frameTimestamp);
        log.debug("Logging frame for device {} - timestamp: {}", deviceId, frameTimestamp.toEpochMilli());
        logFrame(frame);
    }

    private void logFrame(Telemetry frame) {

        StringBuilder sb = new StringBuilder();

        sb.append("[DL] DEVICE ID=").append(frame.getDeviceId())
          .append(" TS=").append(frame.getTimestamp().toEpochMilli());

        if (frame.getInfo() != null)
            appendInfo(sb, frame.getInfo());
        if (frame.getInput() != null)
            appendInput(sb, frame.getInput());
        if (frame.getImu() != null)
            appendImu(sb, frame.getImu());
        if (frame.getSusp() != null)
            appendSusp(sb, frame.getSusp());
        if (frame.getTiming() != null)
            appendLiveTiming(sb, frame.getTiming());
        if (frame.getAero() != null)
            appendAero(sb, frame.getAero());
        if (frame.getTyre() != null)
            appendTyres(sb, frame.getTyre());
        if (frame.getGps() != null)
            appendGps(sb, frame.getGps());

        log.info(sb.toString());
    }

    private void appendInfo(StringBuilder sb, Info info) {

        if (!activeFields.contains(DebugField.INFO))
            return;

        sb.append(" | INFO[");
        sb.append("Dist=").append(round(info.getDist())).append("m");
        if (info.isInPit())
            sb.append(" IN-PIT");

        sb.append(" Driver=\"").append(info.getDriverName()).append("\"")
            .append(" #").append(info.getCarNumber())
            .append(" Team=\"").append(info.getTeamId()).append("\"");

        float[] dmg = info.getCarDamage();
        sb.append(" Dmg[")
            .append(round(dmg[0])).append(",")
            .append(round(dmg[1])).append(",")
            .append(round(dmg[2])).append(",")
            .append(round(dmg[3])).append(",")
            .append(round(dmg[4])).append("]");

        if (info.isTcOn())
            sb.append(" TC-ON");
        if (info.isAbsOn())
            sb.append(" ABS-ON");

        sb.append("]");
    }

    private void appendInput(StringBuilder sb, Input i) {

        if (!activeFields.contains(DebugField.INPUT))
            return;

        sb.append(" | INPUT[");
        sb.append("Speed=").append(round(i.getSpeedKmh())).append("km/h")
                .append(" Gear=").append(i.getGear());

        sb.append(" Throttle=").append(round(i.getThrottle()))
                .append(" Brake=").append(round(i.getBrake()))
                .append(" Clutch=").append(round(i.getClutch()))
                .append(" Steer=").append(round(i.getSteer()));

        sb.append(" RPM=").append(i.getRpm())
                .append(" Fuel=").append(round(i.getFuel())).append("L")
                .append(" Turbo=").append(round(i.getTurbo()))
                .append(" KERS=").append(round(i.getKersCharge())).append("%");

        sb.append("]");
    }

    private void appendImu(StringBuilder sb, Imu imu) {

        if (!activeFields.contains(DebugField.IMU))
            return;

        sb.append(" | IMU[");
        sb.append("Acc(X:").append(round(imu.getAccX()))
            .append(" Y:").append(round(imu.getAccY()))
            .append(" Z:").append(round(imu.getAccZ())).append(")G");

        sb.append(" Roll=").append(round(imu.getRoll())).append("rad")
            .append(" Pitch=").append(round(imu.getPitch())).append("rad")
            .append(" YawRate=").append(round(imu.getYawRate())).append("rad/s")
            .append("SideSlip=").append(round(imu.getSideSlip())).append("rad");
        sb.append("]");
    }

    private void appendSusp(StringBuilder sb, Suspension s) {

        if (!activeFields.contains(DebugField.SUSP))
            return;

        sb.append(" | SUSP[");

        float[] travel = s.getSuspensionTravel();
        sb.append("Travel(FL:").append(round(travel[0] * 1000))
            .append(" FR:").append(round(travel[1] * 1000))
                .append(" RL:").append(round(travel[2] * 1000))
                .append(" RR:").append(round(travel[3] * 1000)).append(")mm");

        float[] camber = s.getCamberRAD();
        sb.append(" Camber(FL:").append(round(Math.toDegrees(camber[0])))
                .append(" FR:").append(round(Math.toDegrees(camber[1])))
                .append(" RL:").append(round(Math.toDegrees(camber[2])))
                .append(" RR:").append(round(Math.toDegrees(camber[3]))).append(")°");

        float[] load = s.getWheelLoad();
        sb.append(" Load(FL:").append(round(load[0]))
                .append(" FR:").append(round(load[1]))
                .append(" RL:").append(round(load[2]))
                .append(" RR:").append(round(load[3])).append(")N");

        float[] speed = s.getWheelAngularSpeed();
        sb.append(" AngSpeed(FL:").append(round(speed[0]))
                .append(" FR:").append(round(speed[1]))
                .append(" RL:").append(round(speed[2]))
                .append(" RR:").append(round(speed[3])).append(")");

        sb.append("]");
    }

    private void appendLiveTiming(StringBuilder sb, LiveTiming lt) {

        if (!activeFields.contains(DebugField.TIMING))
            return;

        sb.append(" | TIMING[");
        sb.append("Lap=").append(lt.getLapCount())
                .append(" Pos=P").append(lt.getPosition());

        sb.append(" Sector=").append(lt.getSectorIdx())
                .append(" SectorTime=").append(formatTime(lt.getSectorTimeMs()));

        sb.append(" Current=").append(formatTime(lt.getCurrentLapMs()))
                .append(" Last=").append(formatTime(lt.getLastLapMs()))
                .append(" Best=").append(formatTime(lt.getBestLapMs()))
                .append(" Delta=").append(round(lt.getDelta())).append("s");

        if (lt.isInPitLane())
            sb.append(" IN-PIT");
        sb.append(" Flag=").append(lt.getFlag());

        sb.append("]");
    }

    private void appendAero(StringBuilder sb, Aerodynamic a) {

        if (!activeFields.contains(DebugField.AERO))
            return;

        sb.append(" | AERO[");
        sb.append("DownForce=").append(round(a.getDownforce() / 10)).append("kg")
                .append(" Drag=").append(round(a.getDrag() / 10));

        sb.append(" CL-F=").append(round(a.getClFront()))
                .append(" CL-R=").append(round(a.getClRear()))
                .append(" CD=").append(round(a.getCd()));

        sb.append(" RideH(F:").append(round(a.getRideHeightFront() * 1000))
                .append(" R:").append(round(a.getRideHeightRear() * 1000)).append(")mm");

        sb.append("]");
    }

    private void appendTyres(StringBuilder sb, Tyre t) {

        if (!activeFields.contains(DebugField.TYRE))
            return;

        sb.append(" | TYRES[");
        sb.append("COMP=").append(t.getCompound());

        float[] temps = t.getCoreTemps();
        if (temps != null && temps.length >= 4) {
            sb.append(" Temp(FL:").append(round(temps[0]))
                    .append(" FR:").append(round(temps[1]))
                    .append(" RL:").append(round(temps[2]))
                    .append(" RR:").append(round(temps[3])).append(")");
        }

        float[] press = t.getPressures();
        if (press != null && press.length >= 4) {
            sb.append(" Press(FL:").append(round(press[0]))
                    .append(" FR:").append(round(press[1]))
                    .append(" RL:").append(round(press[2]))
                    .append(" RR:").append(round(press[3])).append(")");
        }

        float[] wear = t.getWearLevels();
        float[] dirt = t.getDirtLevels();
        if (wear != null && wear.length >= 4) {
            sb.append(" Wear(FL:").append(round(wear[0]))
                    .append(" FR:").append(round(wear[1]))
                    .append(" RL:").append(round(wear[2]))
                    .append(" RR:").append(round(wear[3])).append(")");
        }
        if (dirt != null && dirt.length >= 4) {
            sb.append(" Dirt(FL:").append(round(dirt[0]))
                    .append(" FR:").append(round(dirt[1]))
                    .append(" RL:").append(round(dirt[2]))
                    .append(" RR:").append(round(dirt[3])).append(")");
        }

        float[] slip = t.getSlipRatios();
        if (slip != null && slip.length >= 4) {
            sb.append(" Slip(FL:").append(round(slip[0]))
                    .append(" FR:").append(round(slip[1]))
                    .append(" RL:").append(round(slip[2]))
                    .append(" RR:").append(round(slip[3])).append(")");
        }

        sb.append("]");
    }

    private void appendGps(StringBuilder sb, Gps g) {

        if (!activeFields.contains(DebugField.GPS))
            return;

        sb.append(" | GPS[");
        sb.append("HeadDir=").append(round(g.getHeading()))
            .append(" POS(X:").append(round(g.getX()))
            .append(" Z:").append(round(g.getZ())).append(")");
        sb.append("]");
    }

    private String round(float v) {
        return String.format("%.2f", v);
    }

    private String round(double v) {
        return String.format("%.2f", v);
    }

    private String formatTime(int milliseconds) {
        if (milliseconds <= 0)
            return "00:00.000";
        int minutes = milliseconds / 60000;
        int seconds = (milliseconds % 60000) / 1000;
        int millis = milliseconds % 1000;
        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }
}