package fx.brd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InterfaceAddress;

public class Listener extends Thread {
    InterfaceAddress ia;
    public Listener(InterfaceAddress ia) {
        this.ia = ia;
        setDaemon(true);
    }

    @Override
    public void run() {
        try (DatagramSocket receiver = new DatagramSocket(18918, ia.getAddress())) {
            System.out.println("receiver is ready on: " + receiver.getLocalSocketAddress());
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[0], 0);
                receiver.receive(packet);
                if (packet.getAddress().equals(ia.getAddress())) continue;
                System.out.println("receiver " + receiver.getLocalSocketAddress() + " packet received from: " + packet.getSocketAddress());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
