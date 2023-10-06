package fx;

import fx.brd.Listener;
import fx.brd.Sender;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.InterfaceAddress;

public class Main extends Application {
    public static void main(String[] args) {
        for (InterfaceAddress ia : Util.getNetworks()) {
            new Listener(ia).start();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FrameCont.showFrame(primaryStage);
    }
}
