package com.stint.race_data_server.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;
import com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger.UdpListener;

import jakarta.annotation.PostConstruct;

/**
 * Inicia automaticamente el listener {@link UdpListener} al levantar contexto Spring
 */
@Component
public class DataloggerUdpStarter {

    private static final Logger log = LoggerFactory.getLogger(DataloggerUdpStarter.class);

    private final ReceiveTelemetry receiveTelemetry;
    private final int port;
    private final int bufferSize;

    public DataloggerUdpStarter(
        ReceiveTelemetry receiveTelemetry,
        @Value("${udp.datalogger.port:}") int port,
        @Value("${udp.datalogger.buffer-size:}") int bufferSize
    ) {
        this.receiveTelemetry = receiveTelemetry;
        this.port = port;
        this.bufferSize = bufferSize;
    }

    @PostConstruct
    public void start() {

        UdpListener listener = DataloggerUdpConfig.dataloggerListener(port, bufferSize, receiveTelemetry);
        
        Thread t = new Thread(listener, "datalogger-udp-listener");
        t.setDaemon(true);
        t.start();
        log.info("DATALOGGER UDP listener started on port: {}", port);
    }

}
