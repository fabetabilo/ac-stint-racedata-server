package com.stint.race_data_server.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stint.race_data_server.domain.telemetry.frame.TelemetryAssembler;

/**
 * Configuración de Componentes de Dominio
 * 
 * Instancia objetos de dominio que necesitan ser inyectados en la aplicacion.
 */
@Configuration
public class DomainBeansConfig {
    
    /**
     * Crea instancia de {@link TelemetryAssembler} como bean gestionado por Spring
     */
    @Bean
    public TelemetryAssembler telemetryAssembler() {
        return new TelemetryAssembler();
    }
}
