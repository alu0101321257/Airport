/**
 * @file Doxyfile
 * @brief Configuración para la generación de documentación Doxygen.
 *
 * @mainpage Airport Project Documentation
 *
 * @section intro Introduction
 *
 * Airport Project comprises the development of unit tests for the 'flights' and 'passengers' classes, along with detailed documentation using the Doxygen tool. This documentation serves as a clear guide on the purpose, usage, and functionality of the classes, as well as their associated unit tests. Doxygen comments are seamlessly integrated into the source code, enhancing system comprehension and maintenance.
 *
 * @section coverage SonarCloud Code Coverage Evaluation
 *
 * A comprehensive analysis of unit test coverage has been conducted using SonarCloud. This process identifies specific areas of the code that may not have been addressed by the tests, providing valuable quality metrics. Detailed coverage information is available in the SonarCloud-generated report, offering a transparent view of the effectiveness of the implemented tests.
 *
 * @section maven Maven POM File Plugin Integration
 *
 * The development cycle management has been optimized through the integration of specific plugins in the Maven POM file. These plugins efficiently handle test execution and coverage analysis during the build process. Maven configuration has been enhanced to ensure code consistency and quality, simplifying project maintenance and collaboration.
 *
 * @section doxygen Doxygen Documentation
 *
 * Doxygen-generated documentation has been included to enhance code understanding and facilitate maintenance. Doxygen comments, embedded directly in the source code, provide detailed descriptions of classes, methods, and variables, as well as their relationships. This documentation enriches project quality by offering clear and accessible information for future developers and collaborators.
 *
 * @section circleci Continuous Integration with CircleCI
 *
 * Continuous integration has been configured using CircleCI. Each commit and pull request automatically triggers unit test execution and coverage analysis. This ensures early detection of potential issues and constant validation of code quality. CircleCI configuration is located in the .circleci/config.yml file, allowing easy comprehension and modification as needed.
 *
 * @section github Project Management on GitHub
 *
 * The project is hosted on GitHub, facilitating review and collaboration among team members. Transparent project progress management is achieved through effective use of branches, commits with descriptive messages, and pull request integration. This approach supports effective collaboration and clear tracking of software development.
 */


/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

/**
 * @file Flight.java
 * @brief This file contains the implementation of the Flight class.
 */
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;
/**
 * @class Flight
 * @brief Represents a flight with a unique flight number and a certain number of seats.
 */
public class Flight {

    private String flightNumber; ///< Unique identifier for the flight.
    private int seats; ///< Number of available seats on the flight.
    private Set<Passenger> passengers = new HashSet<>(); ///< Set of passengers currently booked on the flight.

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; ///< Regular expression for valid flight numbers.
    private static Pattern pattern = Pattern.compile(flightNumberRegex); ///< Pattern object for flight number validation.
    /**
     * @brief Constructs a Flight object with the specified flight number and number of seats.
     * @param flightNumber The unique identifier for the flight.
     * @param seats The number of available seats on the flight.
     * @throws RuntimeException If the provided flight number is invalid.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }
    /**
     * @brief Gets the flight number of the flight.
     * @return The flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }
    /**
     * @brief Gets the number of passengers currently booked on the flight.
     * @return The number of passengers.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }
    /**
     * @brief Adds a passenger to the flight.
     * @param passenger The passenger to be added.
     * @return True if the passenger was successfully added, false otherwise.
     * @throws RuntimeException If there are not enough seats for the flight.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }
    /**
     * @brief Removes a passenger from the flight.
     * @param passenger The passenger to be removed.
     * @return True if the passenger was successfully removed, false otherwise.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
