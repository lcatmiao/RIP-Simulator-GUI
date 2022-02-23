package GUI;

import component.NetworkGraph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Main extends Application {

    static NetworkGraph networkGraph;
    static ByteArrayOutputStream bos;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("RIP Simulator");
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // 建立网络拓扑图
        networkGraph = new NetworkGraph();

        // 输出重定向
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        launch(args);
    }
}
