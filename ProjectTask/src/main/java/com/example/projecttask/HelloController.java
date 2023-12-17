package com.example.projecttask;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.projecttask.animation.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.text.Text;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonRegistration;

    @FXML
    private TextField textFieldAuthLogin;

    public static String authLogin;

    @FXML
    private PasswordField textFieldAuthPassword;

    @FXML
    private Text textStartWindow;

    @FXML
    void initialize() {
        buttonStart.setOnAction(e->{


            String loginText = textFieldAuthLogin.getText().trim();
            String loginPassword = textFieldAuthPassword.getText().trim();
            authLogin = loginText;

            if (!loginText.equals("") && !loginPassword.equals("")) {
                loginUser(loginText, loginPassword);
                textStartWindow.setVisible(false);
            } else {
                System.out.println("Поля пустые");
                textStartWindow.setVisible(true);
            }
        });

        buttonRegistration.setOnAction(e-> {
           openNewScene("registration-view.fxml", "Окно регистрации", false);
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1) {
            openNewScene("start-view.fxml", "Главная страница", true);
        } else {
            Shake userLoginAnimation = new Shake(textFieldAuthLogin);
            Shake userPasswordAnimation = new Shake(textFieldAuthPassword);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
    }
     public void openNewScene(String window, String title, Boolean flag) {
         buttonStart.getScene().getWindow().hide();

         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource(window));

         try {
             loader.load();
         } catch (IOException ex) {
             throw new RuntimeException(ex);
         }

         Parent root = loader.getRoot();
         Stage stage = new Stage();
         stage.setTitle(title);
         stage.setScene(new Scene(root));
         stage.setResizable(flag);
         stage.show();
     }
}
