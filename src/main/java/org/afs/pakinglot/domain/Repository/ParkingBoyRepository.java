// src/main/java/org/afs/pakinglot/repository/ParkingBoyRepository.java
package org.afs.pakinglot.domain.Repository;

import org.afs.pakinglot.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingBoyRepository {

    @Autowired
    private StandardParkingBoy standardParkingBoy;

    @Autowired
    private SmartParkingBoy smartParkingBoy;

    @Autowired
    private SuperSmartParkingBoy superSmartParkingBoy;

    public Ticket park(Car car, String strategy) {
        ParkingBoy parkingBoy;
        switch (strategy.toLowerCase()) {
            case "smart":
                parkingBoy = smartParkingBoy;
                break;
            case "super-smart":
                parkingBoy = superSmartParkingBoy;
                break;
            case "standard":
            default:
                parkingBoy = standardParkingBoy;
                break;
        }
        return parkingBoy.park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingBoy parkingBoy = findParkingBoyByTicket(ticket);
        return parkingBoy.fetch(ticket);
    }

    private ParkingBoy findParkingBoyByTicket(Ticket ticket) {
        if (standardParkingBoy.contains(ticket)) {
            return standardParkingBoy;
        } else if (smartParkingBoy.contains(ticket)) {
            return smartParkingBoy;
        } else if (superSmartParkingBoy.contains(ticket)) {
            return superSmartParkingBoy;
        } else {
            throw new RuntimeException("Parking boy not found for the given ticket");
        }
    }
}