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
