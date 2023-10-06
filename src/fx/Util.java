package fx;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Util {
    private static ArrayList<InterfaceAddress> networks;

    public static ArrayList<InterfaceAddress> getNetworks() {
        if (networks == null) networks = getLocalNetworks();
        return networks;
    }

    private static ArrayList<InterfaceAddress> getLocalNetworks() {
        ArrayList<InterfaceAddress> localNetworks = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isLoopback() || !ni.isUp() || ni.getDisplayName().toLowerCase().contains("host-only")) continue;
                for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                    String hostAddress = ia.getAddress().getHostAddress();
                    if (hostAddress.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                        localNetworks.add(ia);
                    }
                }
            }
            return localNetworks;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
