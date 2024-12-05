// src/test/java/org/afs/pakinglot/domain/SmartParkingBoyTest.java
package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    @Test
    void should_park_in_lot_with_most_available_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car = new Car(CarPlateGenerator.generatePlate());
        // When
        Ticket ticket = smartParkingBoy.park(car);
        // Then
        assertTrue(parkingLot2.contains(ticket));
    }
}