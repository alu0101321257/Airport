package es.ull.flights;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        // Initialize a Flight object before each test
        flight = new Flight("AB123", 50);
    }

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
        Flight smallFlight = new Flight("XY456", 1);https://chat.openai.com/c/494ca3db-a8f7-4918-9529-add049ae2caa
        Passenger passenger1 = new Passenger("ID001", "Alice", "CA");
        Passenger passenger2 = new Passenger("ID002", "Bob", "US");
        smallFlight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> smallFlight.addPassenger(passenger2));
    }
    @Test
    void testAddPassengerWhenFull() {
        // Añadir pasajeros hasta que se llene el vuelo
        for (int i = 1; i <= 50; i++) {
            Passenger passenger = new Passenger("ID" + i, "Passenger" + i, "US");
            assertTrue(flight.addPassenger(passenger));
        }

        // Intentar añadir un pasajero más (debería lanzar una excepción)
        Passenger extraPassenger = new Passenger("ExtraID", "ExtraPassenger", "US");
        assertThrows(RuntimeException.class, () -> flight.addPassenger(extraPassenger));
    }

    @Test
    void testRemovePassengerNotInFlight() {
        // Crear un pasajero que no está en el vuelo
        Passenger passenger = new Passenger("ID123", "John Doe", "US");

        // Intentar eliminar al pasajero que no está en el vuelo
        assertFalse(flight.removePassenger(passenger));
    }
}
