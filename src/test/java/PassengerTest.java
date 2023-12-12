@Test
    void testInvalidCountryCode() {
        // Debería lanzar una RuntimeException al intentar crear un pasajero con código de país no válido
        assertThrows(RuntimeException.class, () -> new Passenger("ID789", "Bob", "InvalidCode"));
    }

    @Test
    void testJoinFlightWithoutPreviousFlight() {
        // Debería unirse al nuevo vuelo sin lanzar excepciones
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testJoinFlightWithPreviousFlightSuccessfullyRemoved() {
        // Configura el vuelo anterior para que el método removePassenger devuelva true
        when(previousFlight.removePassenger(passenger)).thenReturn(true);

        // Une al pasajero a un nuevo vuelo
        passenger.joinFlight(flight);

        // Verifica que el método removePassenger se llamó correctamente
        verify(previousFlight, times(1)).removePassenger(passenger);
        // Verifica que el pasajero está unido al nuevo vuelo
        assertEquals(flight, passenger.getFlight());
    }

    @Test
    void testJoinFlightWithFailedRemove() {
        // Configura el vuelo anterior para que el método removePassenger devuelva false
        when(previousFlight.removePassenger(passenger)).thenReturn(false);

        // Debería lanzar una RuntimeException al intentar unirse al nuevo vuelo
        assertThrows(RuntimeException.class, () -> passenger.joinFlight(flight));
    }
