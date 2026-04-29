/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

/**
 *
 * @author Admin
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TaskManagementSystem2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("views/MainView.fxml")
        );
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Task Management System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}