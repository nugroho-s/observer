package net.scarycode.observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Observer implements Initializable {
    private static String imageSource = "http://114.110.17.6:8895/image.jpg";

    @FXML
    private  ImageView imageView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateImage();
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateImage();
                        }
                    });
                    i++;
                    Thread.sleep(500);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void updateImage(){
        Image image = new Image(imageSource);
        imageView.setImage(image);
    }
}
