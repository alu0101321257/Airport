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
 * @file Passenger.java
 * @brief This file contains the implementation of the Passenger class.
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Represents a passenger with a unique identifier, name, and country code.
 */
public class Passenger {

    private String identifier; ///< Unique identifier for the passenger.
    private String name; ///< Name of the passenger.
    private String countryCode; ///< Country code of the passenger.
    private Flight flight; ///< The flight that the passenger is currently on.

    /**
     * @brief Constructs a Passenger object with the specified identifier, name, and country code.
     * @param identifier The unique identifier for the passenger.
     * @param name The name of the passenger.
     * @param countryCode The country code of the passenger.
     * @throws RuntimeException If the provided country code is invalid.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Gets the identifier of the passenger.
     * @return The passenger identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Gets the name of the passenger.
     * @return The passenger name.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Gets the country code of the passenger.
     * @return The country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Gets the flight that the passenger is currently on.
     * @return The current flight.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Joins a new flight, leaving the previous one if applicable.
     * @param flight The new flight to join.
     * @throws RuntimeException If the passenger cannot be removed from the previous flight or added to the new one.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets the current flight of the passenger.
     * @param flight The new flight.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Returns a string representation of the passenger.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
