// src/main/java/org/afs/pakinglot/service/ParkingLotService.java
package org.afs.pakinglot.domain.service;

import org.afs.pakinglot.domain.model.*;
import org.afs.pakinglot.domain.Repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

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
        ParkingLot parkingLot = parkingLotRepository.findParkingLotByTicket(ticket);
        return parkingLot.fetch(ticket);
    }

    public Duration getParkingDuration(Ticket ticket) {
        ParkingLot parkingLot = parkingLotRepository.findParkingLotByTicket(ticket);
        return parkingLot.getParkingDuration(ticket);
    }

    public Map<Integer, String> getParkingStatus(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId);
        return parkingLot.getParkingStatus();
    }

    public String getParkingTimeDetails(Ticket ticket) {
        ParkingLot parkingLot = parkingLotRepository.findParkingLotByTicket(ticket);
        return parkingLot.getParkingTimeDetails(ticket);
    }

    public double calculateParkingFees(Ticket ticket) {
        ParkingLot parkingLot = parkingLotRepository.findParkingLotByTicket(ticket);
        return parkingLot.calculateParkingFees(ticket);
    }
}