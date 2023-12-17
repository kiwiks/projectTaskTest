package com.example.projecttask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class UserProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonToHomeWindow;

    @FXML
    private Button buttonUnLogin;

    @FXML
    private ImageView imageViewProfile;

    @FXML
    private Text textUserStatistics;

    @FXML
    private Text userNameProfile;


    @FXML
    void initialize() {

        //imageViewProfile.setDisable(false);
        DatabaseHandler dbHandlerStatistics = new DatabaseHandler();
        User userProfile = new User();
        String name = HelloController.authLogin.trim();
        userProfile.setUserName(name);

        ResultSet resultSet = dbHandlerStatistics.getUserStatistics(userProfile);

        int resultStatisticsCorrectAnswers = -10;
        int resultStatisticsAnswers = -10;


        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if(resultSet.getString(2).equals(name)) {
                    resultStatisticsCorrectAnswers = resultSet.getInt(5);
                    resultStatisticsAnswers = resultSet.getInt(6);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


            userNameProfile.setText("Пользователь : " + name);
            textUserStatistics.setText("Статистика : " + resultStatisticsCorrectAnswers + "/" + resultStatisticsAnswers);

            System.out.println(name + " : " + resultStatisticsCorrectAnswers + "/" + resultStatisticsAnswers);



        buttonToHomeWindow.setOnAction(e->{
            openNewScene(buttonToHomeWindow,"start-view.fxml", "Главная страница", true);
        });

        buttonUnLogin.setOnAction(e->{
            openNewScene(buttonUnLogin, "hello-view.fxml", "Страница авторизации", false);
        });

    }

    public void openNewScene(Button button, String window, String title, Boolean flag) {
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
        stage.setResizable(flag);
        stage.show();
    }

}
