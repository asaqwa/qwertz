package fx;

import fx.brd.Sender;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class FrameCont {
    Stage primaryStage;
    ArrayList<Sender> senders = new ArrayList<>();

    public static void showFrame(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Frame.fxml"));
        try {
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        FrameCont cont = loader.getController();
        cont.primaryStage = primaryStage;
        try {
            cont.initSenders();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        primaryStage.show();
    }

    private void initSenders() throws SocketException {
        for (InterfaceAddress ia: Util.getNetworks()) {
            senders.add(new Sender(ia.getAddress()));
        }
    }

    @FXML
    private void handleRequest() throws IOException {
        InetAddress brd = InetAddress.getByName("255.255.255.255");
        DatagramPacket packet = new DatagramPacket(new byte[0], 0, brd, 18918);
        for (Sender sender: senders) {
            sender.send(packet);
        }
    }
}
