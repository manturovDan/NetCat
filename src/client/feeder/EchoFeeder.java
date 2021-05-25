package client.feeder;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class EchoFeeder {
    private DatagramSocket socket;
    private InetAddress address;

    public EchoFeeder(String addr) {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName(addr);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String sendToKitty(String msg) throws IOException {
        byte[] buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return new String(
                packet.getData(), 0, packet.getLength());
    }

    public void close() {
        socket.close();
    }
}
