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
        ParkingLot parkingLot1 = new ParkingLot(3); // Capacity 3
        ParkingLot parkingLot2 = new ParkingLot(5); // Capacity 5
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car1 = new Car(CarPlateGenerator.generatePlate());
        Car car2 = new Car(CarPlateGenerator.generatePlate());
        Car car3 = new Car(CarPlateGenerator.generatePlate());
        // When
        Ticket ticket1 = superSmartParkingBoy.park(car1);
        Ticket ticket2 = superSmartParkingBoy.park(car2);
        Ticket ticket3 = superSmartParkingBoy.park(car3);
        // Then
        assertTrue(parkingLot1.contains(ticket1));
        assertTrue(parkingLot2.contains(ticket2));
        assertTrue(parkingLot2.contains(ticket3));
    }


}