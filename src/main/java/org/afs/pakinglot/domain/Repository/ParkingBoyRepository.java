// src/main/java/org/afs/pakinglot/repository/ParkingBoyRepository.java
package org.afs.pakinglot.domain.Repository;

import org.afs.pakinglot.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingBoyRepository {

    @Autowired
    private StandardParkingBoy standardParkingBoy;

    @Autowired
    private SmartParkingBoy smartParkingBoy;

    @Autowired
    private SuperSmartParkingBoy superSmartParkingBoy;

    public Ticket park(Car car, String strategy, String parkingBoyType) {
        ParkingBoy parkingBoy = getParkingBoyByType(parkingBoyType);
        return parkingBoy.park(car);
    }

    public Car fetch(Ticket ticket, String parkingBoyType) {
        ParkingBoy parkingBoy = getParkingBoyByType(parkingBoyType);
        return parkingBoy.fetch(ticket);
    }

    public Ticket findTicketByPlateNumber(String plateNumber, String parkingBoyType) {
        ParkingBoy parkingBoy = getParkingBoyByType(parkingBoyType);
        return parkingBoy.getTickets().stream()
                .filter(ticket -> ticket.plateNumber().equals(plateNumber))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ticket not found for the given plate number"));
    }

    private ParkingBoy getParkingBoyByType(String parkingBoyType) {
        switch (parkingBoyType.toLowerCase()) {
            case "smart":
                return smartParkingBoy;
            case "super-smart":
                return superSmartParkingBoy;
            case "standard":
            default:
                return standardParkingBoy;
        }
    }
}