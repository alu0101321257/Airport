package es.ull.passengers;
import es.ull.flights.Flight;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private Passenger passenger;
    private Flight flight;
    

    @BeforeEach
    void setUp() {
        // Initialize a Flight and Passenger object before each test
        flight = new Flight("AB123", 50);
        passenger = new Passenger("ID123", "John Doe", "US");
    }
    @Test
    void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID456", "Jane Doe", "XX"));
    }

    
   @Test
    void testJoinFlightWithPreviousFlight() {
        Flight previousFlight = new Flight("GH987", 10);
        passenger.joinFlight(previousFlight);

        // Try to join a new flight without removing from the previous flight
        Flight newFlight = new Flight("IJ321", 15);

        // Assert that no exception is thrown
        assertDoesNotThrow(() -> passenger.joinFlight(newFlight));

        // Assert that the passenger is still in the previous flight
        assertEquals(previousFlight, passenger.getFlight());
    }

    @Test
    void testJoinFlightWithRemovedPassengerFailure() {
        Flight otherFlight = new Flight("OP345", 25);
        passenger.joinFlight(otherFlight);

        // Remove passenger from the other flight
        otherFlight.removePassenger(passenger);

        // Try to join the new flight without being removed from the previous flight
        Flight newFlight = new Flight("QR678", 30);

        // Assert that no exception is thrown
        assertDoesNotThrow(() -> passenger.joinFlight(newFlight));

        // Assert that the passenger is not in the new flight
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlightWithRemovedPassengerSuccess() {
        Flight otherFlight = new Flight("OP345", 25);
        passenger.joinFlight(otherFlight);

        // Remove passenger from the other flight
        otherFlight.removePassenger(passenger);

        // Successfully join the new flight after removal from the previous flight
        Flight newFlight = new Flight("QR678", 30);
        passenger.joinFlight(newFlight);

        assertEquals(newFlight, passenger.getFlight());
    }
    //pruebas nuevas

    @Test
    void testGetFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testAddPassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testRemovePassenger() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
        assertNull(passenger.getFlight());
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("InvalidNumber", 100));
    }

    @Test
    void testNotEnoughSeats() {
        Flight smallFlight = new Flight("XY456", 1);
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        smallFlight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }
     @Test
    void testJoinFlightWithNullFlight() {
        passenger.joinFlight(null);
        assertNull(passenger.getFlight());
    }

    @Test
    void testSetFlightWithNullFlight() {
        passenger.setFlight(null);
        assertNull(passenger.getFlight());
    }

    @Test
    void testSetFlight() {
        passenger.setFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testJoinFlightWithRemovedPassenger() {
        Flight otherFlight = new Flight("CD456", 30);
        passenger.joinFlight(otherFlight);

        // Remove passenger from the other flight
        otherFlight.removePassenger(passenger);

        // Now try to join the new flight
        Flight newFlight = new Flight("EF789", 20);
        passenger.joinFlight(newFlight);

        assertEquals(newFlight, passenger.getFlight());
    }

    @Test
    void testToString() {
        String expectedString = "Passenger John Doe with identifier: ID123 from US";
        assertEquals(expectedString, passenger.toString());
    }
}

