package com.stint.race_data_server.infrastructure.config;

import java.util.EnumMap;
import java.util.Map;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PacketDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PacketDispatcher;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PacketType;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.PayloadDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.UdpListener;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.AerodynamicDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.GpsDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.ImuDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.InfoDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.InputDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.LiveTimingDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.SuspensionDecoder;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.decoder.TyreDecoder;

/**
 * Construye todo el pipeline del datalogger, registra:
 * que tipo de paquete -> que decoder, sin tocar dominio ni aplicacion. Se ejecuta una sola vez
 */
public class DataloggerUdpConfig {
    
    public static UdpListener dataloggerListener(int port, int bufferSize, ReceiveTelemetry receiveTelemetry) {

        PacketDecoder packetDecoder = packetDecoder();

        return new UdpListener(port, bufferSize, receiveTelemetry, packetDecoder);

    }

    private static PacketDecoder packetDecoder() {

        Map<PacketType, PayloadDecoder> decoders = new EnumMap<>(PacketType.class);

        // payload decoders registrados orden segun ids
        decoders.put(PacketType.INFO, new InfoDecoder());
        decoders.put(PacketType.INPUT, new InputDecoder());
        decoders.put(PacketType.IMU, new ImuDecoder());
        decoders.put(PacketType.SUSP, new SuspensionDecoder());
        decoders.put(PacketType.LIVE_TIMING, new LiveTimingDecoder());
        decoders.put(PacketType.GPS, new GpsDecoder());
        decoders.put(PacketType.TYRE, new TyreDecoder());
        decoders.put(PacketType.AERO, new AerodynamicDecoder());

        PacketDispatcher dispatcher = new PacketDispatcher(decoders);

        return new PacketDecoder(dispatcher);

    }

}
