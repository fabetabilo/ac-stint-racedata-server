package com.stint.race_data_server.infrastructure.inbound.udp.datalogger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.stint.race_data_server.domain.telemetry.sample.TelemetrySample;

/**
 * Escucha, recibe paquetes UDP raw en bytes[] en un puerto especifico, y los procesa como samples de telemetria {@link TelemetrySample}
 */
public class UdpListener implements Runnable{

    private final int port;
    private final PacketDecoder decoder;

    public UdpListener(int port, PacketDecoder decoder) {
        this.port = port;
        this.decoder = decoder;
    }

    /**
     * Abre datagram socket con el puerto
     */
    public void run() {
        // cierra automaticamente al salir del try-with-resources
        try (DatagramSocket socket = new DatagramSocket(port)){

            byte[] buffer = new byte[2048]; // !temporal: revisar mas adelante puede cambiar

            // !temporal: while true de momento, la unica forma de terminar es interrumpir la ejecucion
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                try{
                    TelemetrySample sample = decoder.decode(packet.getData(), packet.getLength());

                    if (sample != null) {
                        System.out.println("monka");   
                    }
                } catch (Exception e) {
                    System.err.println("Error decoding packet: " + e.getMessage());
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
