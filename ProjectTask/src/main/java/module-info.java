module com.example.projecttask {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projecttask to javafx.fxml;
    exports com.example.projecttask;
}