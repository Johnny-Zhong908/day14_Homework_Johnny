// src/main/java/org/afs/pakinglot/repository/ParkingLotRepository.java
package org.afs.pakinglot.domain.Repository;

import org.afs.pakinglot.domain.model.ParkingLot;
import org.afs.pakinglot.domain.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingLotRepository {

    private final List<ParkingLot> parkingLots;

    @Autowired
    public ParkingLotRepository(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingLot findAvailableParkingLot(String strategy) {
        // Implement strategy-based parking lot selection
        // For simplicity, we return the first available parking lot
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available parking lot"));
    }

    public ParkingLot findParkingLotByTicket(Ticket ticket) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Parking lot not found for the given ticket"));
    }

    public ParkingLot findById(int id) {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Parking lot not found"));
    }
}