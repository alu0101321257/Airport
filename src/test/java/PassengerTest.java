import es.ull.passengers.Passenger;
import es.ull.flights.Flight;
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
    void testConstructor() {
        // Verifica que el constructor establezca correctamente los valores iniciales
        assertEquals("ID123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
        assertNull(passenger.getFlight());
    }

    @Test
    void testJoinAndLeaveFlight() {
        // Crea un vuelo
        Flight flight = new Flight("AB123", 50);

        // Une al pasajero al vuelo
        passenger.joinFlight(flight);

        // Verifica que el pasajero esté en el vuelo
        assertEquals(flight, passenger.getFlight());
        assertTrue(flight.getPassengers().contains(passenger));
        assertEquals(1, flight.getNumberOfPassengers());

        // Deja el vuelo
        passenger.joinFlight(null);

        // Verifica que el pasajero ya no esté en el vuelo
        assertNull(passenger.getFlight());
        assertFalse(flight.getPassengers().contains(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    void testInvalidCountryCode() {
        // Verifica que se lance una excepción con un código de país inválido
        assertThrows(RuntimeException.class, () -> new Passenger("ID456", "Jane Doe", "XX"));
    }

    @Test
    void testToString() {
        // Verifica que el método toString genere la cadena esperada
        assertEquals("Passenger John Doe with identifier: ID123 from US", passenger.toString());
    }
}

