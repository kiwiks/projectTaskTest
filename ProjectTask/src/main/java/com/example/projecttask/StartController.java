package com.example.projecttask;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button answerBtn;

    @FXML
    private ToggleGroup answers;

    @FXML
    private Button buttonTaskCSharp;

    @FXML
    private Button buttonUserProfile;

    @FXML
    private Button buttonTaskJava;

    @FXML
    private Button buttonTaskJavaScript;

    @FXML
    private Button closeProgramBtn;

    @FXML
    private Button buttonToStartWindow;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Text questionText;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private RadioButton radioBtn3;

    @FXML
    private RadioButton radioBtn4;

    @FXML
    private Text textChangeLanguage;

    private Questions[] questionsCSharp = new Questions[]{
            new Questions("Какое из следующих ключевых слов используется для включения пространств имен в программу на C#?",
                    new String[]{"Ничего из вышеперечисленного", "imports", "exports", "using"}),
            new Questions("Что из следующего является правильным в отношении соглашений об именах переменных в C#?",
                    new String[]{"Первый символ идентификатора не может быть цифрой", "Имя должно начинаться с буквы, за которой может следовать последовательность букв, цифр (0–9) или символа подчеркивания", "Ничего из вышеперечисленного", "Все вышеперечисленное"}),
            new Questions("Число цифр, до которого допустимое значение точности типа данных с плавающей запятой?",
                    new String[]{"До 9 цифр", "До 8 цифр", "До 6 цифр", "До 7 цифр"}),
            new Questions("Правильный способ присвоения значений переменной c, когда int a=12, float b=3.5,int c;",
                    new String[]{"c = int(a + b);", "c = a + int(float(b));", "c = a + b;", "c = a + convert.ToInt32(b);"}),
            new Questions("Правильное объявление значений переменных a и b?",
                    new String[]{"int a = b = 42;", "int a = 42; b = 40;", "int a = 32, b = 40.6;", "int a = 32; int b = 40;"}),
    };

    private Questions[] questionsJavaScript = new Questions[]{
            new Questions("Какая арифметическая операция приводит к ошибке в javascript?",
                    new String[]{"Деление на ноль", "Умножение числа на строку", "Корень из отрицательного числа", "Никакая из вышеперечисленных"}),
            new Questions("Что выведет этот код?\n" + "f.call(f);\n" +
                    "\n" +
                    "function f() {\n" +
                    "  alert( this );\n" +
                    "}",
                    new String[]{"ошибка: слишком глубокая рекурсия", "ошибка: переменная f не определена", "другое", "код функции f"}),
            new Questions("Какой результат будет у выражения ниже? \n" + "null + {0:1}[0] + [,[1],][1][0]",
                    new String[]{"0", "1", "undefined", "2"}),
            new Questions("Что выведет этот код? \n" + "function User() { }\n" +
                    "User.prototype = { admin: false };\n" +
                    "\n" +
                    "let user = new User();\n" +
                    "alert(user.admin);",
                    new String[]{"NaN", "undefined", "true", "false"}),
            new Questions("Что такое ECMAScript?",
                    new String[]{"Новый язык программирования", "Переработанная реализация Javascript", "Объект класса", "Спецификация языка Javascript"}),
    };

    private Questions[] questionsJava = new Questions[]{
            new Questions("В каком из вариантов представлен корректный формат вывода информации на экран?",
                    new String[]{"Console.Write()", "console.log()", "print()", "System.out.println()"}),
            new Questions("Какой тип данных отвечает за целые числа?",
                    new String[]{"String", "Float", "Boolean", "Integer"}),
            new Questions("Где правильно присвоено новое значение к многомерному массиву?",
                    new String[]{"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
            new Questions("Какой метод позволяет запустить программу на Java?",
                    new String[]{"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
            new Questions("Каждый файл должен называться...",
                    new String[]{"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
    };

    private int nowQuestion = 0, correctAnswers, numberOfAnswers;

    private String nowCorrectAnswer;

    @FXML
    void initialize() {

        buttonTaskCSharp.setOnAction(e -> {


            nowQuestion = 0;
            correctAnswers = 0;
            numberOfAnswers = 0;

            answerBtn.setVisible(true);
            buttonToStartWindow.setVisible(false);
            questionText.setVisible(true);
            radioBtn1.setVisible(true);
            radioBtn2.setVisible(true);
            radioBtn3.setVisible(true);
            radioBtn4.setVisible(true);
            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            radioBtn3.setSelected(false);
            radioBtn4.setSelected(false);

            textChangeLanguage.setText("Задачки по C#");

            nowCorrectAnswer = questionsCSharp[nowQuestion].correctAnswer();

            questionText.setText(questionsCSharp[nowQuestion].getQuestion());

            String[] answersFirst = questionsCSharp[nowQuestion].getAnswers();

            List<String> intListFirst = Arrays.asList(answersFirst);

            //Collections.shuffle(intListFirst);

            List<Integer> randomNumFirst = new ArrayList<Integer>();

            randomNumFirst.add(0);
            randomNumFirst.add(1);
            randomNumFirst.add(2);
            randomNumFirst.add(3);

            System.out.println(randomNumFirst.get(0) + " " + randomNumFirst.get(1) + " " + randomNumFirst.get(2) + " " + randomNumFirst.get(3));


            Collections.shuffle(randomNumFirst);

            radioBtn1.setText(intListFirst.get(randomNumFirst.get(0)));
            radioBtn2.setText(intListFirst.get(randomNumFirst.get(1)));
            radioBtn3.setText(intListFirst.get(randomNumFirst.get(2)));
            radioBtn4.setText(intListFirst.get(randomNumFirst.get(3)));

            System.out.println(nowCorrectAnswer);

            answerBtn.setOnAction(event -> {

                RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();

                if (selectedRadioButton != null) {

                    String toogleGroupValue = selectedRadioButton.getText();


                    if (toogleGroupValue.equals(nowCorrectAnswer)) {

                        System.out.println("Верный ответ");
                        correctAnswers++;
                    } else {
                        System.out.println("Не верный ответ");
                    }
                    numberOfAnswers++;

                    if (nowQuestion + 1 == questionsCSharp.length) {
                        radioBtn1.setVisible(false);
                        radioBtn2.setVisible(false);
                        radioBtn3.setVisible(false);
                        radioBtn4.setVisible(false);
                        answerBtn.setVisible(false);
                        buttonToStartWindow.setVisible(true);

                        questionText.setText("Вы ответили корректно на " + correctAnswers + " из " + questionsCSharp.length + " вопросов!");
                        updateStatistics(numberOfAnswers, correctAnswers, HelloController.authLogin.trim());
                    } else {

                        nowQuestion++;

                        nowCorrectAnswer = questionsCSharp[nowQuestion].correctAnswer();


                        questionText.setText(questionsCSharp[nowQuestion].getQuestion());

                        String[] answers = questionsCSharp[nowQuestion].getAnswers();


                        List<String> intList = Arrays.asList(answers);

                        List<Integer> randomNum = new ArrayList<Integer>();

                        randomNum.add(0);
                        randomNum.add(1);
                        randomNum.add(2);
                        randomNum.add(3);

                        Collections.shuffle(randomNum);

                        //Collections.shuffle(intList);

                        radioBtn1.setText(intList.get(randomNum.get(0)));
                        radioBtn2.setText(intList.get(randomNum.get(1)));
                        radioBtn3.setText(intList.get(randomNum.get(2)));
                        radioBtn4.setText(intList.get(randomNum.get(3)));


                        selectedRadioButton.setSelected(false);
                    }

                }
            });


        });

        buttonTaskJavaScript.setOnAction(e -> {
            nowQuestion = 0;
            correctAnswers = 0;
            numberOfAnswers = 0;

            answerBtn.setVisible(true);
            buttonToStartWindow.setVisible(false);
            questionText.setVisible(true);
            radioBtn1.setVisible(true);
            radioBtn2.setVisible(true);
            radioBtn3.setVisible(true);
            radioBtn4.setVisible(true);
            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            radioBtn3.setSelected(false);
            radioBtn4.setSelected(false);

            textChangeLanguage.setText("Задачки по JavaScript");

            nowCorrectAnswer = questionsJavaScript[nowQuestion].correctAnswer();

            questionText.setText(questionsJavaScript[nowQuestion].getQuestion());

            String[] answersFirst = questionsJavaScript[nowQuestion].getAnswers();

            List<String> intListFirst = Arrays.asList(answersFirst);

            //Collections.shuffle(intListFirst);

            List<Integer> randomNumFirst = new ArrayList<Integer>();

            randomNumFirst.add(0);
            randomNumFirst.add(1);
            randomNumFirst.add(2);
            randomNumFirst.add(3);

            System.out.println(randomNumFirst.get(0) + " " + randomNumFirst.get(1) + " " + randomNumFirst.get(2) + " " + randomNumFirst.get(3));


            Collections.shuffle(randomNumFirst);

            radioBtn1.setText(intListFirst.get(randomNumFirst.get(0)));
            radioBtn2.setText(intListFirst.get(randomNumFirst.get(1)));
            radioBtn3.setText(intListFirst.get(randomNumFirst.get(2)));
            radioBtn4.setText(intListFirst.get(randomNumFirst.get(3)));

            System.out.println(nowCorrectAnswer);

            answerBtn.setOnAction(event -> {

                RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();

                if (selectedRadioButton != null) {

                    String toogleGroupValue = selectedRadioButton.getText();


                    if (toogleGroupValue.equals(nowCorrectAnswer)) {

                        System.out.println("Верный ответ");
                        correctAnswers++;
                    } else
                        System.out.println("Не верный ответ");
                    numberOfAnswers++;

                    if (nowQuestion + 1 == questionsJavaScript.length) {
                        radioBtn1.setVisible(false);
                        radioBtn2.setVisible(false);
                        radioBtn3.setVisible(false);
                        radioBtn4.setVisible(false);
                        answerBtn.setVisible(false);
                        buttonToStartWindow.setVisible(true);

                        questionText.setText("Вы ответили корректно на " + correctAnswers + " из " + questionsJavaScript.length + " вопросов!");
                        updateStatistics(numberOfAnswers, correctAnswers, HelloController.authLogin.trim());
                    } else {

                        nowQuestion++;

                        nowCorrectAnswer = questionsJavaScript[nowQuestion].correctAnswer();


                        questionText.setText(questionsJavaScript[nowQuestion].getQuestion());

                        String[] answers = questionsJavaScript[nowQuestion].getAnswers();


                        List<String> intList = Arrays.asList(answers);

                        List<Integer> randomNum = new ArrayList<Integer>();

                        randomNum.add(0);
                        randomNum.add(1);
                        randomNum.add(2);
                        randomNum.add(3);

                        Collections.shuffle(randomNum);

                        //Collections.shuffle(intList);

                        radioBtn1.setText(intList.get(randomNum.get(0)));
                        radioBtn2.setText(intList.get(randomNum.get(1)));
                        radioBtn3.setText(intList.get(randomNum.get(2)));
                        radioBtn4.setText(intList.get(randomNum.get(3)));


                        selectedRadioButton.setSelected(false);
                    }

                }
            });
        });

        buttonTaskJava.setOnAction(e -> {
            nowQuestion = 0;
            correctAnswers = 0;
            numberOfAnswers = 0;

            answerBtn.setVisible(true);
            buttonToStartWindow.setVisible(false);
            questionText.setVisible(true);
            radioBtn1.setVisible(true);
            radioBtn2.setVisible(true);
            radioBtn3.setVisible(true);
            radioBtn4.setVisible(true);
            radioBtn1.setSelected(false);
            radioBtn2.setSelected(false);
            radioBtn3.setSelected(false);
            radioBtn4.setSelected(false);

            textChangeLanguage.setText("Задачки по Java");

            nowCorrectAnswer = questionsJava[nowQuestion].correctAnswer();

            questionText.setText(questionsJava[nowQuestion].getQuestion());

            String[] answersFirst = questionsJava[nowQuestion].getAnswers();

            List<String> intListFirst = Arrays.asList(answersFirst);

            //Collections.shuffle(intListFirst);

            List<Integer> randomNumFirst = new ArrayList<Integer>();

            randomNumFirst.add(0);
            randomNumFirst.add(1);
            randomNumFirst.add(2);
            randomNumFirst.add(3);

            System.out.println(randomNumFirst.get(0) + " " + randomNumFirst.get(1) + " " + randomNumFirst.get(2) + " " + randomNumFirst.get(3));


            Collections.shuffle(randomNumFirst);

            radioBtn1.setText(intListFirst.get(randomNumFirst.get(0)));
            radioBtn2.setText(intListFirst.get(randomNumFirst.get(1)));
            radioBtn3.setText(intListFirst.get(randomNumFirst.get(2)));
            radioBtn4.setText(intListFirst.get(randomNumFirst.get(3)));

            System.out.println(nowCorrectAnswer);

            answerBtn.setOnAction(event -> {

                RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();

                if (selectedRadioButton != null) {

                    String toogleGroupValue = selectedRadioButton.getText();


                    if (toogleGroupValue.equals(nowCorrectAnswer)) {

                        System.out.println("Верный ответ");
                        correctAnswers++;
                    } else
                        System.out.println("Не верный ответ");
                    numberOfAnswers++;

                    if (nowQuestion + 1 == questionsJava.length) {
                        radioBtn1.setVisible(false);
                        radioBtn2.setVisible(false);
                        radioBtn3.setVisible(false);
                        radioBtn4.setVisible(false);
                        answerBtn.setVisible(false);
                        buttonToStartWindow.setVisible(true);

                        questionText.setText("Вы ответили корректно на " + correctAnswers + " из " + questionsJava.length + " вопросов!");
                        updateStatistics(numberOfAnswers, correctAnswers, HelloController.authLogin.trim());
                    } else {

                        nowQuestion++;

                        nowCorrectAnswer = questionsJava[nowQuestion].correctAnswer();


                        questionText.setText(questionsJava[nowQuestion].getQuestion());

                        String[] answers = questionsJava[nowQuestion].getAnswers();


                        List<String> intList = Arrays.asList(answers);

                        List<Integer> randomNum = new ArrayList<Integer>();

                        randomNum.add(0);
                        randomNum.add(1);
                        randomNum.add(2);
                        randomNum.add(3);

                        Collections.shuffle(randomNum);

                        //Collections.shuffle(intList);

                        radioBtn1.setText(intList.get(randomNum.get(0)));
                        radioBtn2.setText(intList.get(randomNum.get(1)));
                        radioBtn3.setText(intList.get(randomNum.get(2)));
                        radioBtn4.setText(intList.get(randomNum.get(3)));


                        selectedRadioButton.setSelected(false);
                    }

                }
            });
        });

        buttonToStartWindow.setOnAction(e -> {
            questionText.setVisible(false);
            radioBtn1.setVisible(false);
            radioBtn2.setVisible(false);
            radioBtn3.setVisible(false);
            radioBtn4.setVisible(false);
            buttonToStartWindow.setVisible(false);
        });


        closeProgramBtn.setOnAction(e -> {
            System.exit(0);
        });

        buttonUserProfile.setOnAction(e -> {
            buttonUserProfile.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userProfile-view.fxml"));

            try {
                loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Профиль");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });

    }

    public void updateStatistics(int numberOfAnswersFunction, int correctAnswersFunction, String name) {
        if (numberOfAnswersFunction != 0) {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            User user = new User();
            user.setUserName(name);


            ResultSet resultSet = databaseHandler.getUserStatistics(user);

            int resultStatisticsCorrectAnswers = 0;
            int resultStatisticsAnswers = 0;

            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (resultSet.getString(2).equals(name)) {
                        resultStatisticsCorrectAnswers = resultSet.getInt(5);
                        resultStatisticsAnswers = resultSet.getInt(6);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            resultStatisticsCorrectAnswers += correctAnswersFunction;
            resultStatisticsAnswers += numberOfAnswersFunction;

            System.out.println(resultStatisticsCorrectAnswers + " : " + correctAnswersFunction);
            System.out.println(resultStatisticsAnswers + " : " + numberOfAnswersFunction);

            user.setCorrectAnswers(resultStatisticsCorrectAnswers);
            user.setAnswers(resultStatisticsAnswers);

            System.out.println(user.getCorrectAnswers() + " 123");
            System.out.println(user.getAnswers() + " 321");

            //DatabaseHandler dbHandler = new DatabaseHandler();

            int result = databaseHandler.setNumUser(user);
        }
    }
}
