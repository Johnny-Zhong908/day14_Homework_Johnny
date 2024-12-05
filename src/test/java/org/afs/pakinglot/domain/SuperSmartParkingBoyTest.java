// src/test/java/org/afs/pakinglot/domain/SuperSmartParkingBoyTest.java
package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.model.Car;
import org.afs.pakinglot.domain.model.ParkingLot;
import org.afs.pakinglot.domain.model.SuperSmartParkingBoy;
import org.afs.pakinglot.domain.model.Ticket;
import org.afs.pakinglot.domain.model.CarPlateGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest {
    @Test
    void should_park_in_lot_with_highest_available_position_rate() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(1); // Capacity 1
        ParkingLot parkingLot2 = new ParkingLot(2); // Capacity 2
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car = new Car(CarPlateGenerator.generatePlate());
        // When
        Ticket ticket = superSmartParkingBoy.park(car);
        // Then
        assertTrue(parkingLot2.contains(ticket));
    }


}