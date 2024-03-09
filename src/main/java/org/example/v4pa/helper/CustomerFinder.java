package org.example.v4pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Customer;

public abstract class CustomerFinder {
    public static ObservableList<Appointment> findAssociatedAppointments(int customerID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> associatedList = FXCollections.observableArrayList();
        for(Appointment testAppointment : appointmentList) {
            if (testAppointment.getApptCustomerID() == customerID) {
                associatedList.add(testAppointment);
            }
        }
        return associatedList;
    }

    public static Customer findCustomerID(String customerName) {
        ObservableList<Customer> customerList = CustomerQuery.getAllCustomers();
        for(Customer testCustomer : customerList) {
            if (testCustomer.getCustomerName().contains(customerName)) {
                return testCustomer;
            }
        }
        return null;
    }

    public static Customer findCustomerName(int customerID) {
        ObservableList<Customer> customerList = CustomerQuery.getAllCustomers();
        for(Customer testCustomer : customerList) {
            if (testCustomer.getCustomerID() == (customerID)) {
                return testCustomer;
            }
        }
        return null;
    }
}
