package com.rahulm.pomodoro.model;

import com.rahulm.pomodoro.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class loginController implements Initializable{

    public LoginModel loginModel = new LoginModel();


    @FXML
    public Label isConnected;

    @FXML
    public TextField txtUsername;

    @FXML
    public TextField txtPassword;

    @Override
    public void initialize(URL location,ResourceBundle resources){
        if (loginModel.isDbConnected()) {
            isConnected.setText("Connected");
        } else {
            isConnected.setText("Not Connected");
        }
    }

    public void Login(javafx.event.ActionEvent actionEvent){
        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())) {
                isConnected.setText("Username and Password is Correct");
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Font.loadFont(getClass().getResource("/fonts/VarelaRound-Regular.ttf").toExternalForm(), 10);
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
                primaryStage.setTitle("Pomodoro Timer");
                primaryStage.setScene(new Scene(root, 500, 500));
                primaryStage.setResizable(false);
                primaryStage.show();
            } else {
                isConnected.setText("Username and Password is Incorrect");
            }
        }catch (SQLException | IOException e){
            isConnected.setText("Username and Password is Incorrect");
            e.printStackTrace();
        }

    }

    public void registration(ActionEvent actionEvent) throws IOException {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Font.loadFont(getClass().getResource("/fonts/VarelaRound-Regular.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/registration_form.fxml"));
        primaryStage.setTitle("Registration");
        primaryStage.setScene(new Scene(root, 380, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
