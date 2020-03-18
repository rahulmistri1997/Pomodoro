package com.rahulm.pomodoro.controllers;

import com.rahulm.pomodoro.Main;
import com.rahulm.pomodoro.Main.*;
import com.rahulm.pomodoro.controllers.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class login implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//    @FXML
//    public void handleScreen2 (ActionEvent actionEvent) throws IOException{
//
//        try {
//            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            Pane root = fxmlLoader.load(getClass().getResource("/fxml/home.fxml").openStream());
//            stage.setTitle("Home");
//            stage.setScene(new Scene(root, 500, 500));
//            stage.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
