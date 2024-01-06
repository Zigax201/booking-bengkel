# Booking System Project With Login

This is a simple login booking system project written in Java. It allows users to login before accessing other feature create reservations, get detail current login user data, adding local currency, and perform various other functionalities.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Project Structure](#project-structure)
- [How to Run](#how-to-run)
- [Usage](#usage)
- [Contributing](#contributing)

## Introduction

The Booking System Project is designed to manage reservations, local currency, and reservation history in a bengkel or service-based business. It uses Java programming language and follows a simple console-based user interface.

## Features

- **Get current user data**: Users can get their private data that can only be access after login.
- **Create Reservation**: Users can create reservations by selecting a customer, an available employee, and services.
- **Edit Currency**: Users can adding the currency of a it's own account.
- **Show Data**: Various functionalities to display recent reservations, vehicle, service and reservation history.
- **Interactive Menu System**: The project features an interactive console-based menu system.

## Project Structure

The project is structured into several packages:

- `com.bengkel.booking.interfaces`: Contains the Payment classes for Customer.
- `com.bengkel.booking.models`: Contains the model classes for Customer, Booking/Reservation, Membership, Vehicle, Reservation, and Service.
- `com.bengkel.booking.repositories`: Provides mock repositories for Customer and Service.
- `com.bengkel.booking.service`: Includes service classes for managing reservations, printing data, and validating input.

## How to Run

To run the project, follow these steps:

1. Clone the repository:
   ```bash
   git clone <repository_url>

   cd booking-system-project
2. Compile the Java files:
    ```bash
    javac com/bengkel/booking/App.java
3. Run the main class:
    ```bash
    java com.bengkel.booking.Main
## Usage
Follow the on-screen instructions to navigate through the menu and perform various actions. Use the menu options to create reservations, display data, and manage the system.

## Contributing
If you would like to contribute to the project, feel free to fork the repository and submit pull requests with your changes. Issues and feature requests are also welcome.