package org.example.v4pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.model.Appointment;

import java.time.LocalDateTime;

/** This class creates the AppointmentFinder class that finds Appointment-related information based on different parameters. */
public abstract class AppointmentFinder {
    /** This method finds all appointments associated with a specific contactID.
     * LAMBDA - The lambda allows the user to determine if there are any appointments associated with the contact ID.
     * @param contactID the ID used to determine if a contact has associated appointments.
     */
    public static ObservableList<Appointment> findAppointmentsByContact(int contactID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();
        appointmentList.forEach(a -> {
            if (a.getApptContactID() == contactID) {
                appointmentsByContact.add(a);
            }
        });
        return appointmentsByContact;
    }

    /** This method finds an appointment associated with a specific userID.
     * @param userID the ID used to find the appointment object.
     */
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

    /** This method finds an appointment associated with a specific customerID.
     * @param customerID the ID used to find the appointment object.
     */
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

    /** This method finds an appointment associated with a specific userID that will start within 15 minutes of the user's current time.
     * @param userID the ID used to find the appointment object.
     * @param currentDateTime the current date and time to compare against the appointment objects.
     */
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

    /** This method finds any appointments that will start within 7 days of the user's current date and time.
     * @param currentDateTime the current date and time to compare against the appointment objects.
     */
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

    /** This method finds any appointments that will start within 1 month of the user's current date and time.
     * @param currentDateTime the current date and time to compare against the appointment objects.
     */
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
