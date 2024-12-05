// src/test/java/org/afs/pakinglot/domain/StandardParkingBoyTest.java
package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardParkingBoyTest {

    @Test
    void should_park_in_first_available_lot() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car = new Car(CarPlateGenerator.generatePlate());
        // When
        Ticket ticket = standardParkingBoy.park(car);
        // Then
        assertTrue(parkingLot1.contains(ticket));
    }
}