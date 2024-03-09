package org.example.v4pa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.v4pa.dao.JDBC;
import org.example.v4pa.helper.GeneralInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.util.TimeZone;

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

        String name = "";
        int id = 0;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();

        // Zone ID MST (UTC -7), EST (UTC -5), GMT (UTC +0)

        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("America")).forEach(System.out::println);
//
        LocalDate easternDate = LocalDate.of(2024, 03, 20);
        LocalTime easternTime = LocalTime.of(01, 00);
        ZoneId easternZoneID = ZoneId.of("America/New_York");
        ZonedDateTime easternZDT = ZonedDateTime.of(easternDate, easternTime, easternZoneID);

        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        Instant easternToGMTInstant = easternZDT.toInstant();
        ZonedDateTime easternToLocalZDT = easternZDT.withZoneSameInstant(easternZoneID);
        ZonedDateTime gmtToLocalZDT = easternToGMTInstant.atZone(localZoneID);
//
//        System.out.println("Local: " + ZonedDateTime.now());
//        System.out.println("Eastern: " + easternZDT);
        System.out.println("Eastern-> GMT: " + easternToGMTInstant);
//        System.out.println("GMT-> Local: " + gmtToLocalZDT);
//        System.out.println("GMT-> Local Date: " + gmtToLocalZDT.toLocalDate());
//        System.out.println("GMT-> Local Time: " + gmtToLocalZDT.toLocalTime());
//
//        String date = String.valueOf(gmtToLocalZDT.toLocalDate());
//        String time = String.valueOf(gmtToLocalZDT.toLocalTime());
//        String dateTime = date + " " + time;
//        //For SQL input
//        System.out.println(dateTime);

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        JDBC.closeConnection();
    }
}