package fx.brd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Sender {
    DatagramSocket socket;

    public Sender(InetAddress ia) throws SocketException {
        socket = new DatagramSocket(18818, ia);
    }

    public void send(DatagramPacket packet) throws IOException {
        socket.send(packet);
        System.out.println("Packet from " + socket.getLocalSocketAddress() + " was sent to: " + packet.getSocketAddress());
    }

    public void close() {
        socket.close();
    }
}
