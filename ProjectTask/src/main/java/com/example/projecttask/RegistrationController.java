package com.example.projecttask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonBackToHomeWindow;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField textFieldEmailRegistration;

    @FXML
    private TextField textFieldLoginRegistration;

    @FXML
    private PasswordField textFieldPasswordRegistration;


    @FXML
    void initialize() {
       signUpButton.setOnAction(e->{
           if (!textFieldLoginRegistration.getText().equals("") && !textFieldPasswordRegistration.getText().equals("") && !textFieldEmailRegistration.getText().equals("")) {
               signUpNewUser();
               textFieldLoginRegistration.setText(null);
               textFieldPasswordRegistration.setText(null);
               textFieldEmailRegistration.setText(null);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setContentText("Регистрация прошла успешно");
               alert.setTitle("Регистрация");
               alert.setHeaderText("Вы зарегистрировались");
               alert.showAndWait();
               openNewScene(signUpButton, "hello-view.fxml", "Страница авторизации");
           } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setContentText("Заполните все поля");
               alert.setTitle("Внимание");
               alert.setHeaderText("Какие-то поля пустые");
               alert.show();
           }
       });

       buttonBackToHomeWindow.setOnAction(e-> {
            openNewScene(buttonBackToHomeWindow,"hello-view.fxml", "Окно авторизации");
       });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String userName = textFieldLoginRegistration.getText();
        String password = textFieldPasswordRegistration.getText();
        String email = textFieldEmailRegistration.getText();

        User user = new User(userName, password, email);

        dbHandler.signUpUser(user);
    }

    public void openNewScene(Button button, String window, String title) {
        button.getScene().getWindow().hide();

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
        stage.show();
    }

}
