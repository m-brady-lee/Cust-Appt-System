package org.example.v4pa.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.v4pa.dao.AppointmentQuery;
import org.example.v4pa.dao.CustomerQuery;
import org.example.v4pa.model.Appointment;
import org.example.v4pa.model.Customer;

/** This class creates the CustomerFinder class that finds Customer-related information based on different parameters. */
public abstract class CustomerFinder {

    /** This method finds all appointments associated with a specific customerID.
     * LAMBDA - The lambda allows the user to determine if there are any appointments associated with the customer ID.
     * @param customerID the ID used to determine if a customer has associated appointments.
     */
    public static ObservableList<Appointment> findAssociatedAppointments(int customerID) {
        ObservableList<Appointment> appointmentList = AppointmentQuery.getAllAppointments();
        ObservableList<Appointment> associatedList = FXCollections.observableArrayList();
        appointmentList.forEach(a-> {
            if (a.getApptCustomerID() == customerID) {
                associatedList.add(a);
            }
        });
        return associatedList;
    }

    /** This method finds a customer associated with a specific customerName.
     * @param customerName the name used to find the customer object.
     */
    public static Customer findCustomerID(String customerName) {
        ObservableList<Customer> customerList = CustomerQuery.getAllCustomers();
        for(Customer testCustomer : customerList) {
            if (testCustomer.getCustomerName().contains(customerName)) {
                return testCustomer;
            }
        }
        return null;
    }

    /** This method finds a customer associated with a specific customerID.
     * @param customerID the ID used to find the customer object.
     */
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
