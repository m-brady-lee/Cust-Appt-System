package org.example.v4pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.v4pa.dao.JDBC;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/** This class creates an app for the Customer Appointment System.
 This is the main class. */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/org/example/view/log-in-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello world!");
        stage.setScene(scene);
        stage.show();
    }

/** This is the main method. This is the first method that gets called when you run the program.
 * This method also opens and closes the database connection, allowing the user to connect to the MySQL database.
 * JAVA DOC FILE LOCATION: /V6-PA/index.html
 */
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        JDBC.openConnection();

        launch();

        JDBC.closeConnection();

        //Value returning Lambda Expression
//        GeneralInterface square = n -> n * n;
//        System.out.println(square.calculateSquare(5));

//        GeneralInterface message = s -> "Hello " + s;
//        System.out.println(message.DisplayMessage("Michael"););

        // Void Lambda Expression
//        GeneralInterface message = s -> System.out.println("Hello " + s);
//        message.DisplayMessage("Michael");

//         Multiple parameter Lambda Expression


        // Void No parameter Lambda Expression
//        GeneralInterface message = () -> System.out.println("Hello World");
//        message.displayMessage();

//         Multiple statement Lambda Expression
//        GeneralInterface square = n -> {
//            int x = n * n;
//            return x;
//        };
//        System.out.println(square.calculateSquare(7));

        //Using local variable in lambda expression
//        final int number = 50;
//        GeneralInterface square = n -> n * n;
//        System.out.println(square.calculateSquare(number));

        // Zone ID MST (UTC -7), EST (UTC -5), GMT (UTC +0)

//        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("")).forEach(System.out::println);




    }
}