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
        // Inicializa un objeto Passenger antes de cada prueba
        passenger = new Passenger("ID123", "John Doe", "US");
    }

    @Test
    void testJoinFlight() {
        // Crea un vuelo
        Flight flight = new Flight("AB123", 50);

        // Une al pasajero al vuelo
        passenger.joinFlight(flight);

        // Verifica que el pasajero esté en el vuelo
        assertEquals(flight, passenger.getFlight());
        assertTrue(flight.getPassengers().contains(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    void testJoinFlightWithPreviousFlight() {
        // Crea dos vuelos
        Flight previousFlight = new Flight("XY456", 50);
        Flight newFlight = new Flight("AB123", 50);

        // Une al pasajero al vuelo anterior
        passenger.joinFlight(previousFlight);

        // Verifica que el pasajero no esté en el nuevo vuelo
        assertNull(passenger.getFlight());
        assertFalse(newFlight.getPassengers().contains(passenger));

        // Une al pasajero al nuevo vuelo
        passenger.joinFlight(newFlight);

        // Verifica que el pasajero esté en el nuevo vuelo y no en el anterior
        assertEquals(newFlight, passenger.getFlight());
        assertTrue(newFlight.getPassengers().contains(passenger));
        assertEquals(1, newFlight.getNumberOfPassengers());
        assertEquals(0, previousFlight.getNumberOfPassengers());
    }

    @Test
    void testJoinFlightWithNullFlight() {
        // Une al pasajero a un vuelo nulo
        passenger.joinFlight(null);

        // Verifica que el pasajero no esté en ningún vuelo
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinFlightRemovePassengerFailure() {
        // Crea un vuelo
        Flight flight = new Flight("AB123", 50);

        // Hace que removePassenger falle intencionalmente
        flight.setRemovePassengerShouldFail(true);

        // Verifica que se lance una excepción al intentar unir al pasajero al vuelo
        assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
        assertNull(passenger.getFlight());
    }

    @Test
    void testGetCountryCode() {
        // Verifica que el método getCountryCode devuelva el código de país correcto
        assertEquals("US", passenger.getCountryCode());
    }
}
