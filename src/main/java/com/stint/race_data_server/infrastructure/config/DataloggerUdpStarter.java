package com.stint.race_data_server.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.stint.race_data_server.application.service.TelemetryService;
import com.stint.race_data_server.infrastructure.inbound.udp.datalogger.UdpListener;

import jakarta.annotation.PostConstruct;

/**
 * Inicia automaticamente el listener {@link UdpListener} al levantar contexto Spring
 */
@Component
public class DataloggerUdpStarter {

    private static final Logger log = LoggerFactory.getLogger(DataloggerUdpStarter.class);

    private final TelemetryService telemetryService;

    public DataloggerUdpStarter(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostConstruct
    public void start() {

        UdpListener listener = DataloggerUdpConfig.dataloggerListener(9996, telemetryService); // !temporal: port
        
        Thread t = new Thread(listener, "datalogger-udp-listener");
        t.setDaemon(true);
        t.start();
        log.info("DATALOGGER UDP listener started on port: 9996");
    }

}
