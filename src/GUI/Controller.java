package GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    private Timer timer;


    // 点击刚开始按钮
    @FXML
    private void onButtonOneClick(ActionEvent event) throws UnsupportedEncodingException {
        button1.setDisable(true);
        clearLabel();
        showEachRouterTable();
        button2.setDisable(false);
    }

    // 点击初始化按钮
    @FXML
    private void onButtonTwoClick(ActionEvent event) throws UnsupportedEncodingException {
        button2.setDisable(true);
        clearLabel();
        Main.networkGraph.initialAllRouterTable();
        showEachRouterTable();
        button3.setDisable(false);
    }

    // 点击开始交换更新按钮
    @FXML
    private void onButtonThreeClick(ActionEvent event) {
        button3.setDisable(true);
        button2.setDisable(true);

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // 让JavaFX的UI线程更新界面
                Platform.runLater(() -> {
                    clearLabel();
                    Main.networkGraph.randomSendAndUpdate();
                    try {
                        showEachRouterTable();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 500);

        button4.setDisable(false);
    }

    // 点击停止交换更新按钮
    @FXML
    private void onButtonFourClick(ActionEvent event) {
        button4.setDisable(true);
        timer.cancel();
        button3.setDisable(false);
        button2.setDisable(false);
    }

    // 在Label中显示各个路由表
    private void showEachRouterTable() throws UnsupportedEncodingException {
        Main.networkGraph.printRouterTable("R0");
        label1.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();

        Main.networkGraph.printRouterTable("R1");
        label2.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();

        Main.networkGraph.printRouterTable("R2");
        label3.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();

        Main.networkGraph.printRouterTable("R3");
        label4.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();

        Main.networkGraph.printRouterTable("R4");
        label5.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();

        Main.networkGraph.printRouterTable("R5");
        label6.setText(Main.bos.toString("UTF-8"));
        Main.bos.reset();
    }

    // 清空所有label里的文字
    private void clearLabel() {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
        label6.setText("");
    }
}
