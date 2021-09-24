package com.example.javafx_rad;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;
import java.io.File;

public class LoginController implements Initializable {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView logImageView;
    @FXML
    private ImageView passwordImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField enterPasswordField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logFile = new File("image/icons8-medical-doctor-40.png");
        Image logImage = new Image(logFile.toURI().toString());
        logImageView.setImage(logImage);

        File passwordFile = new File("image/icons8-password-100.png");
        Image passwordImage = new Image(passwordFile.toURI().toString());
        passwordImageView.setImage(passwordImage);
    }

    public void loginButtonOnAction (ActionEvent event) throws IOException{

        if (usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false){
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }

    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectionDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM login WHERE email = '" + usernameTextField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectionDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("Successfully login!");

                    try {
                        Stage stage = (Stage) loginMessageLabel.getScene().getWindow();
                        stage.close();
                        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                        Scene scene = new Scene(root, 780, 490);
                        stage.setTitle("Medi-Waste Details");
                        stage.setScene(scene);
                        stage.show();

                    } catch (Exception e){
                        e.printStackTrace();

                    }


                }else{
                    loginMessageLabel.setText("Invalid login. Please try again!");

                }
            }

        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}