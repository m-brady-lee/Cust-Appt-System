package org.example.v4pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.model.Appointment;

import java.time.LocalDateTime;

public abstract class AppointmentFinder {
    public static ObservableList<Appointment> findAppointmentsByContact(int contactID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptContactID() == contactID) {
                appointmentsByContact.add(testAppointment);
            }
        }
        return appointmentsByContact;
    }

    public static ObservableList<Appointment> findAppointmentsByUser(int userID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByUser = FXCollections.observableArrayList();
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptUserID() == userID) {
                appointmentsByUser.add(testAppointment);
            }
        }
        return appointmentsByUser;
    }

    public static ObservableList<Appointment> findAppointmentsByCustomer(int customerID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByCustomer = FXCollections.observableArrayList();
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptCustomerID() == customerID) {
                appointmentsByCustomer.add(testAppointment);
            }
        }
        return appointmentsByCustomer;
    }

    public static ObservableList<Appointment> findUpcomingAppointments (LocalDateTime currentDateTime, int userID) {

        ObservableList<Appointment> appointmentsByUser = findAppointmentsByUser(userID);
        ObservableList<Appointment> appointmentsInFifteenMins = FXCollections.observableArrayList();
        LocalDateTime cutoffDateTime = currentDateTime.plusMinutes(15);

        for (Appointment testAppointment : appointmentsByUser) {
            if (testAppointment.getApptStart().isBefore(cutoffDateTime) && testAppointment.getApptStart().isAfter(currentDateTime)) {
                appointmentsInFifteenMins.add(testAppointment);
            }
        }
        return appointmentsInFifteenMins;

    }
    public static ObservableList<Appointment> findWeeklyAppointments (LocalDateTime currentDateTime) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> weeklyList = FXCollections.observableArrayList();
        LocalDateTime cutoffDateTime = currentDateTime.plusDays(7);
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptEnd().isBefore(cutoffDateTime) && testAppointment.getApptStart().isAfter(currentDateTime)) {
                weeklyList.add(testAppointment);
            }
        }
        return weeklyList;
    }

    public static ObservableList<Appointment> findMonthlyAppointments (LocalDateTime currentDateTime) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> monthlyList = FXCollections.observableArrayList();
        LocalDateTime cutoffDateTime = currentDateTime.plusMonths(1);
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptEnd().isBefore(cutoffDateTime) && testAppointment.getApptStart().isAfter(currentDateTime)) {
                monthlyList.add(testAppointment);
            }
        }
        return monthlyList;
    }
}
