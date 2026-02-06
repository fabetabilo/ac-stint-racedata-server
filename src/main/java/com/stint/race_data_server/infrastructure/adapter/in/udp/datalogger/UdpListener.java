package com.stint.race_data_server.infrastructure.adapter.in.udp.datalogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stint.race_data_server.application.port.in.ReceiveTelemetry;

/** Adaptador,
 * escucha, recibe paquetes UDP raw en bytes[] en un puerto especifico, los decodifica
 * y los entrega al puerto de entrada como objetos de dominio.
 */
public class UdpListener implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(UdpListener.class);

    private final int port;
    private final int bufferSize;
    private final ReceiveTelemetry receiveTelemetry;
    private final PacketDecoder decoder;

    public UdpListener(int port, int bufferSize, ReceiveTelemetry receiveTelemetry, PacketDecoder decoder) {
        this.port = port;
        this.bufferSize = bufferSize;
        this.receiveTelemetry = receiveTelemetry;
        this.decoder = decoder;
    }

    /**
     * Abre datagram socket con el puerto, implementando metodo de interface {@link Runnable}
     */
    @Override
    public void run() {
        // cierra automaticamente al salir del try-with-resources
        try (DatagramSocket socket = new DatagramSocket(port)){

            byte[] buffer = new byte[bufferSize];

            // !temporal: while true de momento, la unica forma de terminar es interrumpir la ejecucion
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                try{
                    DecodedData decoded = decoder.decode(packet.getData(), packet.getLength());

                    if (decoded != null) {
                        receiveTelemetry.handle(
                            decoded.deviceId(), 
                            decoded.timestamp(), 
                            decoded.data()
                        );
                    }
                } catch (Exception e) {
                    log.error("Error processing packet: {}", e.getMessage());
                } 
            }
        } catch (Exception e) {
            log.error("UDP listener fatal error on port {}", port, e);
        }
    }
}
