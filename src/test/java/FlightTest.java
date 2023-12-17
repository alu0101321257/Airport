/**
 * @file FlightTest.java
 * @brief Test cases for the Flight class.
 */
package es.ull.flights;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @brief Test class for the Flight class.
 */
class FlightTest {

    private Flight flight;
    /**
     * @brief Setup method to initialize a Flight object before each test.
     */
    @BeforeEach
    void setUp() {
        // Initialize a Flight object before each test
        flight = new Flight("AB123", 50);
    }
    /**
     * @brief Test case for the getFlightNumber method.
     */
    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }
    /**
     * @brief Test case for the getNumberOfPassengers method.
     */
    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }
    /**
     * @brief Test case for the addPassenger method.
     */
    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }
    /**
     * @brief Test case for the removePassenger method.
     */
    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }
    /**
     * @brief Test case for creating a Flight with an invalid flight number.
     */
    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidNumber", 100));
    }
    /**
     * @brief Test case for adding a passenger to a flight with not enough seats.
     */
    @Test
    void testNotEnoughSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        smallFlight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }
}

