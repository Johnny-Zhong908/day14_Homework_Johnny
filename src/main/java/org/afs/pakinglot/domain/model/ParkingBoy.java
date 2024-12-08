// src/main/java/org/afs/pakinglot/domain/model/ParkingBoy.java
package org.afs.pakinglot.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.afs.pakinglot.domain.exception.UnrecognizedTicketException;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingStrategy parkingStrategy = new SequentiallyStrategy();
    private static final Pattern LICENSE_PLATE_PATTERN = Pattern.compile("^[A-Z]{2}-\\d{4}$");

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingStrategy parkingStrategy) {
        this.parkingLots = parkingLots;
        this.parkingStrategy = parkingStrategy;
    }

    public Ticket park(Car car) {
        validateLicensePlate(car.plateNumber());
        if (isCarAlreadyParked(car)) {
            throw new IllegalArgumentException("This car already has a parking space");
        }
        return parkingStrategy.findParkingLot(parkingLots).park(car);
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkingLotOfTheTicket = parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
        return parkingLotOfTheTicket.fetch(ticket);
    }

    public boolean contains(Ticket ticket) {
        return parkingLots.stream().anyMatch(parkingLot -> parkingLot.contains(ticket));
    }

    public double calculateParkingFee(Ticket ticket) {
        ParkingLot parkingLotOfTheTicket = parkingLots.stream()
                .filter(parkingLot -> parkingLot.contains(ticket))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
        return parkingLotOfTheTicket.calculateParkingFees(ticket);
    }

    public List<Ticket> getTickets() {
        return parkingLots.stream()
                .flatMap(parkingLot -> parkingLot.getTickets().stream())
                .collect(Collectors.toList());
    }

    private void validateLicensePlate(String plateNumber) {
        if (plateNumber == null || plateNumber.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be empty.");
        }
        if (!LICENSE_PLATE_PATTERN.matcher(plateNumber).matches()) {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
    }

    private boolean isCarAlreadyParked(Car car) {
        return parkingLots.stream()
                .anyMatch(parkingLot -> parkingLot.getTickets().stream()
                        .anyMatch(ticket -> ticket.plateNumber().equals(car.plateNumber())));
    }
}