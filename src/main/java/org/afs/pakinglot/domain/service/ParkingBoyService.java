// src/main/java/org/afs/pakinglot/service/ParkingBoyService.java
package org.afs.pakinglot.domain.service;

import org.afs.pakinglot.domain.model.*;
import org.afs.pakinglot.domain.Repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ParkingBoyService {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    private static final Pattern LICENSE_PLATE_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{4}$");

    public Ticket park(Car car, String strategy, String parkingBoyType) {
        return parkingBoyRepository.park(car, strategy, parkingBoyType);
    }

    public Car fetch(Ticket ticket, String parkingBoyType) {
        return parkingBoyRepository.fetch(ticket, parkingBoyType);
    }

    public boolean isValidPlateNumber(String plateNumber) {
        return LICENSE_PLATE_PATTERN.matcher(plateNumber).matches();
    }

    public Ticket findTicketByPlateNumber(String plateNumber, String parkingBoyType) {
        return parkingBoyRepository.findTicketByPlateNumber(plateNumber, parkingBoyType);
    }
}