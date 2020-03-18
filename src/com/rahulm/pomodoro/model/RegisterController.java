package com.rahulm.pomodoro.model;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterController {

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmpasswordField;

    @FXML
    private Button submitButton;

    @FXML
    public void register(ActionEvent event) throws SQLException{

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(passwordField.getText());
        System.out.println(confirmpasswordField.getText());
        if(fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if(passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if(!confirmpasswordField.getText().equals(passwordField.getText())) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please check if both passwords are same");
            return;
        }


        String username = fullNameField.getText();
        String password = passwordField.getText();
        String confirmpassword = confirmpasswordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(username, password, confirmpassword);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + fullNameField.getText());
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void exitregistration(ActionEvent actionEvent) throws IOException {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Font.loadFont(getClass().getResource("/fonts/VarelaRound-Regular.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 290, 380));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}