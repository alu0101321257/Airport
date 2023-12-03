package es.ull.passengers;
import es.ull.flights.Flight;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        // Initialize a Passenger object before each test
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    @Test
    void testJoinFlight() {
        // Create a new flight
        Flight flight = new Flight("AB123", 50);

        // Perform joinFlight operation
        passenger.joinFlight(flight);

        // Assertions
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
        assertTrue(flight.getPassengers().contains(passenger));
    }

    @Test
    void testJoinFlightWithPreviousFlight() {
        // Create two flights
        Flight previousFlight = new Flight("XY456", 50);
        Flight newFlight = new Flight("AB123", 50);

        // Join the passenger to the previous flight
        passenger.joinFlight(previousFlight);

        // Perform joinFlight operation with a new flight
        passenger.joinFlight(newFlight);

        // Assertions
        assertEquals(newFlight, passenger.getFlight());
        assertEquals(1, newFlight.getNumberOfPassengers());
        assertTrue(newFlight.getPassengers().contains(passenger));
        assertEquals(0, previousFlight.getNumberOfPassengers());
        assertFalse(previousFlight.getPassengers().contains(passenger));
    }

    @Test
    void testJoinFlightWithNullFlight() {
        // Perform joinFlight operation with a null flight
        passenger.joinFlight(null);

        // Assertions
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlightRemovePassengerFailure() {
        // Create a flight
        Flight flight = new Flight("AB123", 50);

        // Intentionally make removePassenger fail
        flight.setRemovePassengerShouldFail(true);

        // Assertions
        assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
        assertNull(passenger.getFlight());
    }
}
