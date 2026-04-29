/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author Admin
 */



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuBar;

public class MenuBarController {

    @FXML private MenuBar menubar;

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleBold(ActionEvent event) {
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        String style = menubar.getScene().getRoot().getStyle();
        style = style.replaceAll("-fx-font-weight:[^;]+;", "");
        if (item.isSelected())
            menubar.getScene().getRoot().setStyle(style + " -fx-font-weight:bold;");
        else
            menubar.getScene().getRoot().setStyle(style + " -fx-font-weight:normal;");
    }

    @FXML
    private void handleItalic(ActionEvent event) {
        CheckMenuItem item = (CheckMenuItem) event.getSource();
        String style = menubar.getScene().getRoot().getStyle();
        style = style.replaceAll("-fx-font-style:[^;]+;", "");
        if (item.isSelected())
            menubar.getScene().getRoot().setStyle(style + " -fx-font-style:italic;");
        else
            menubar.getScene().getRoot().setStyle(style + " -fx-font-style:normal;");
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Task Management System");
        alert.setContentText(
                "Version: 1.0\n" +
                "Course: Programming III Lab - CSCI 2108\n" +
                "Institution: The Islamic University of Gaza\n" +
                "Instructor: Aya N. Alharazin"
        );
        alert.showAndWait();
    }
}