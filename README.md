# e-Healthcare Website with Java and Spring

This repository contains the source code and documentation for an e-Healthcare website developed using Java and the Spring framework. The website provides various functionalities to manage healthcare-related activities and services.

## Features

The e-Healthcare website offers the following features:

1. **User Registration and Authentication**: Users can register for an account on the website and authenticate themselves to access different functionalities.

2. **Patient Management**: The website allows users to manage patient records, including their personal information, medical history, and appointments.

3. **Doctor Management**: Users with administrative privileges can manage doctor profiles, including their specialization, availability, and appointment schedules.

4. **Appointment Booking**: Patients can book appointments with available doctors through the website. The system ensures that appointments do not clash with existing bookings.

5. **Prescription Management**: Doctors can create and manage prescriptions for their patients, including details of medications, dosage instructions, and any additional notes.

6. **Medical History Tracking**: Patients can view and track their medical history, including past appointments, diagnoses, and prescribed medications.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Thymeleaf
- PostgreSQL
- Spring Data JPA
- Spring Mail
- Lombok

## Getting Started
## Getting Started

To run the e-Healthcare website locally, follow these steps:

1. Clone the repository to your local machine using the following command:

'''
git clone https://github.com/Dementris/Curse-work-e-Healthcare.git
'''

2. Ensure that you have Java Development Kit (JDK) and PostgreSQL installed on your machine.

3. Configure the PostGreSQL database by creating a new database and executing the SQL scripts provided in the `database` folder.

4. Update the database connection properties in the `application.properties` file to match your PostgreSQL configuration.

5. Build the project using Maven.

6. Run the application and access it in your web browser by navigating to `http://localhost:8080`.


## License

This project is licensed under the [GNU General Public License v3.0](LICENCE.md). Feel free to modify and distribute the codebase as per the terms of the license.


