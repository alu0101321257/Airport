import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FlightTest {

    private Flight flight;
    private Passenger passenger;

    @Before
    public void setUp() {
        flight = new Flight("AB123", 50);
        passenger = new Passenger("John Doe");
    }

    @Test
    public void testValidFlightNumber() {
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidFlightNumber() {
        new Flight("InvalidNumber", 50);
    }

    @Test
    public void testGetNumberOfPassengers() {
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddPassenger() {
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test(expected = RuntimeException.class)
    public void testAddPassengerExceedSeats() {
        for (int i = 0; i < 55; i++) {
            flight.addPassenger(new Passenger("Passenger" + i));
        }
    }

    @Test
    public void testRemovePassenger() {
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void testPassengerFlightAssociation() {
        flight.addPassenger(passenger);
        assertEquals(flight, passenger.getFlight());
    }
}
