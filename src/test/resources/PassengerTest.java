import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PassengerTest {

    private Passenger passenger;
    private Flight flight;

    @Before
    public void setUp() {
        passenger = new Passenger("ABC123", "John Doe", "ES");
        flight = new Flight("AB123", 50);
    }

    @Test
    public void testValidPassengerCreation() {
        assertEquals("ABC123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("ES", passenger.getCountryCode());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidCountryCode() {
        new Passenger("XYZ456", "Jane Smith", "InvalidCode");
    }

    @Test
    public void testJoinFlight() {
        assertNull(passenger.getFlight());

        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightWithPreviousFlight() {
        Flight previousFlight = new Flight("CD456", 30);
        passenger.joinFlight(previousFlight);

        assertNotNull(passenger.getFlight());
        assertEquals(1, previousFlight.getNumberOfPassengers());

        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
        assertEquals(0, previousFlight.getNumberOfPassengers());
    }

    @Test
    public void testLeaveFlight() {
        passenger.joinFlight(flight);
        assertNotNull(passenger.getFlight());

        passenger.joinFlight(null);
        assertNull(passenger.getFlight());
        assertEquals(0, flight.getNumberOfPassengers());
    }
}
