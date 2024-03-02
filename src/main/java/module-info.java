module c195.v6pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens c195.v6pa to javafx.fxml;
    exports c195.v6pa;

    opens org.example.v4pa to javafx.fxml;
    exports org.example.v4pa;
    exports org.example.v4pa.controller;
    opens org.example.v4pa.controller to javafx.fxml;
    opens org.example.v4pa.model to javafx.base;
}