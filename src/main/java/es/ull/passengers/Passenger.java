package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;

import es.ull.flights.Flight;

/**
 * Represents a passenger with a unique identifier, name, and country code.
 */
public class Passenger {

    /**
     * Unique identifier for the passenger.
     */
    private String identifier;

    /**
     * Name of the passenger.
     */
    private String name;

    /**
     * Country code of the passenger.
     */
    private String countryCode;

    /**
     * The flight that the passenger is currently on.
     */
    private Flight flight;

    /**
     * Constructs a Passenger object with the specified identifier, name, and country code.
     *
     * @param newIdentifier   The unique identifier for the passenger.
     * @param newName         The name of the passenger.
     * @param newCountryCode  The country code of the passenger.
     * @throws RuntimeException If the provided country code is invalid.
     */
    public Passenger(final String newIdentifier, final String newName, final String newCountryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(newCountryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = newIdentifier;
        this.name = newName;
        this.countryCode = newCountryCode;
    }

    /**
     * Gets the identifier of the passenger.
     *
     * @return The passenger identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the name of the passenger.
     *
     * @return The passenger name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the country code of the passenger.
     *
     * @return The country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Gets the flight that the passenger is currently on.
     *
     * @return The current flight.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Joins a new flight, leaving the previous one if applicable.
     *
     * @param newFlight The new flight to join.
     * @throws RuntimeException If the passenger cannot be removed from the previous flight or added to the new one.
     */
    public void joinFlight(final Flight newFlight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(newFlight);
        if (null != newFlight) {
            if (!newFlight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * Sets the current flight of the passenger.
     *
     * @param newFlight The new flight.
     */
    public void setFlight(final Flight newFlight) {
        this.flight = newFlight;
    }

    /**
     * Returns a string representation of the passenger.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
