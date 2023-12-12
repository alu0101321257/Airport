import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @BeforeEach
    void setUp() {
        // Initialize a Flight and Passenger object before each test
        flight = new Flight("AB123", 50);
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    // Flight class tests

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
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testRemovePassenger() {
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

    // Passenger class tests

    @Test
    void testGetIdentifier() {
        assertEquals("ID123", passenger.getIdentifier());
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", passenger.getName());
    }

    @Test
    void testGetCountryCode() {
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    void testGetFlight() {
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlight() {
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertTrue(flight.getPassengers().contains(passenger));
    }

    @Test
    void testJoinFlightWithPreviousFlight() {
        Flight previousFlight = new Flight("CD456", 30);
        passenger.joinFlight(previousFlight);

        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertTrue(flight.getPassengers().contains(passenger));
        assertFalse(previousFlight.getPassengers().contains(passenger));
    }

    @Test
    void testJoinFlightWithFailedRemove() {
        Flight mockFlight = new Flight("EF789", 20);
        // Make the previous flight unable to remove the passenger
        passenger.setFlight(mockFlight);

        assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
    }

    @Test
    void testToString() {
        String expectedString = "Passenger John Doe with identifier: ID123 from US";
        assertEquals(expectedString, passenger.toString());
    }
}

