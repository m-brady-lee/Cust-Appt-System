package org.example.v4pa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.v4pa.dao.FruitsQuery;
import org.example.v4pa.dao.JDBC;
import org.example.v4pa.model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/view/log-in-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello world!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch();

//        try {
//            ResourceBundle rb = ResourceBundle.getBundle("/src/Nat", Locale.getDefault());
//
//            if(Locale.getDefault().getLanguage().equals("de") || Locale.getDefault().getLanguage().equals("es") || Locale.getDefault().getLanguage().equals("fr")) {
//                System.out.println(rb.getString("hello") + " " + rb.getString("world"));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        int rowsAffected = FruitsQuery.delete(4);
        if (rowsAffected > 0) {
            System.out.println("Delete successful!");
        } else {
            System.out.println("Delete failure");
        }
        FruitsQuery.update(6, "Goulash");
        FruitsQuery.select(2);

        JDBC.closeConnection();
    }
}