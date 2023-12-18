module com.example.examendamdesarrollo {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens images;

    opens com.example.examendamdesarrollo to javafx.fxml;
    exports com.example.examendamdesarrollo;
}