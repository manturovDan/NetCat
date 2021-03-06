package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Eat implements Runnable {
    private boolean running = true;
    private final byte[] buf = new byte[256];
    private final DatagramSocket socket;

    public Eat() throws IOException {
        socket = new DatagramSocket(4445);
    }

    @Override
    public void run() {
        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = (new String(packet.getData(), 0, packet.getLength())).replaceAll("\u0000.*", "");

            System.out.println("received UDP: " + received);

            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
