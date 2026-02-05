package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.nio.ByteBuffer;

import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Interface para orquestar "sub-decoders"
 */
public interface PayloadDecoder {
    
    /**
     * Metodo que extrae y transforma los datos raw del buffer en un objeto
     * {@link TelemetrySample} estructurado, utilizando informacion del header del packet
     * para determinar el tipo y formato de los datos
     * 
     * @param buffer buffer binario que contiene los datos de telemetria a decodificar
     * @param header {@link PacketHeader} del packet
     * @return instancia {@link TelemetrySample} con datos decodificados
     */
    TelemetrySample decode(ByteBuffer buffer, PacketHeader header);
    
}
