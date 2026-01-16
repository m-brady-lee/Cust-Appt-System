# CUSTOMER APPOINTMENT SYSTEM

A JavaFX desktop application for managing customer records and appointments with secure login, time zone–aware scheduling, and built-in reporting. Designed to enforce data integrity, prevent scheduling conflicts, and support operational oversight through structured views and reports.

## OBJECTIVE

To build a secure, database-backed scheduling system that allows users to manage customers and appointments efficiently while enforcing business rules such as time zone constraints, non-overlapping appointments, and relational data integrity.

## KEY QUESTIONS ANSWERED

What appointments are scheduled by week or month?

Are there any upcoming appointments in the next 15 minutes?

How many appointments exist by appointment type?

What is the full schedule for a given contact?

How many customers are currently in the system?

## SYSTEM OVERVIEW

Authentication: Secure login required before accessing application features

Scheduling Alerts: Automatic alert on login for appointments starting within the next 15 minutes

Time Zone Handling: Appointments entered in local time are automatically converted to Eastern Time (ET) to enforce business hours (8:00 AM – 10:00 PM ET)

Conflict Prevention: Built-in validation prevents overlapping appointments

Relational Integrity: Customer deletions are restricted until all associated appointments are removed

## DATA MODEL & INTEGRITY

MySQL relational database with enforced foreign key constraints

Customer and appointment entities linked via Customer ID

Contact-based scheduling supported for reporting and filtering

Referential integrity maintained across all CRUD operations

## APPLICATION FEATURES
### Appointment Management

View appointments filtered by week, month, or all

Add, edit, or delete appointments

Appointment fields include:

Appointment ID (auto-generated)

Title, Description, Location

Contact, Type

Start/End Date & Time (military time)

Customer ID, User ID

Validation to prevent overlapping appointments

Automatic enforcement of business hours using ET conversion

### Customer Management

View all customers in a table format

Add, edit, or delete customers

Customer fields include:

Customer ID (auto-generated)

Name, Address, Country, State/Province

Postal Code, Phone Number

Deletion restricted if associated appointments exist (foreign key enforcement)

### Reporting

The application includes three built-in reports:

Appointments by Type
Displays total appointments by category:

Announcement

Brainstorm Session

Customer Engagement

Review

Contact Schedule
Displays appointment details for a selected contact:

Appointment ID, Title, Type

Description

Start/End Date & Time

Customer ID

Total Customers
Displays the current total number of customers in the database

USER FLOW

User logs in with valid credentials

Application displays an alert for any appointments starting within the next 15 minutes (or confirms none exist)

User is taken to the Appointment Details View

Navigation available between:

Appointment Details

Customer Details

Reports View

Navigation buttons are consistently located in the bottom-right of each view for usability.

## TECHNOLOGY STACK

Language: Java

UI Framework: JavaFX

Database: MySQL

JDK: 11.0.22

JavaFX SDK: 21.0.2

IDE: IntelliJ IDEA 2023.3.3 (Community Edition)

Database Driver: mysql-connector-java-8.0.28

INSTALLATION & EXECUTION

Open the project in IntelliJ IDEA

Ensure JDK 11 and JavaFX SDK are correctly configured

Configure database connection settings

Run the application

Log in with valid credentials to access system features

## SKILLS DEMONSTRATED

Object-oriented programming (Java)

Desktop UI development with JavaFX

Relational database design and enforcement

Time zone conversion and validation logic

CRUD operations with foreign key constraints

Business rule enforcement and data integrity

Reporting and operational visibility

## AUTHOR

Michael Lee
📧 m.brady.lee@gmail.com

Student Application Version: V1
Date: 03/12/2024

## FUTURE IMPROVEMENTS

Role-based access control (admin vs standard user)

Enhanced reporting filters and export options

Improved UI styling and accessibility

Automated testing for scheduling edge cases

Audit logging for appointment and customer changes

## SCREENSHOTS

![Login Screen](https://github.com/m-brady-lee/Cust-Appt-System/blob/main/login_screen.png)

