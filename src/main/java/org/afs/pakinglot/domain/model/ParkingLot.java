// src/main/java/org/afs/pakinglot/domain/model/ParkingLot.java
package org.afs.pakinglot.domain.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.afs.pakinglot.domain.exception.NoAvailablePositionException;
import org.afs.pakinglot.domain.exception.UnrecognizedTicketException;

public class ParkingLot {
    private int id;
    private String name;
    private final Map<Ticket, Car> tickets = new HashMap<>();
    private final Map<Ticket, LocalDateTime> parkingTimes = new HashMap<>();

    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;

    public ParkingLot() {
        this(DEFAULT_CAPACITY);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableCapacity() {
        return capacity - tickets.size();
    }

    public Ticket park(Car car) {
        if (isFull()) {
            throw new NoAvailablePositionException();
        }

        Ticket ticket = new Ticket(car.plateNumber(), tickets.size() + 1, this.id);
        tickets.put(ticket, car);
        parkingTimes.put(ticket, LocalDateTime.now());
        return ticket;
    }

    public boolean isFull() {
        return capacity == tickets.size();
    }

    public Car fetch(Ticket ticket) {
        if (!tickets.containsKey(ticket)) {
            throw new UnrecognizedTicketException();
        }

        parkingTimes.remove(ticket);
        return tickets.remove(ticket);
    }

    public boolean contains(Ticket ticket) {
        return tickets.containsKey(ticket);
    }

    public double getAvailablePositionRate() {
        return getAvailableCapacity() / (double) capacity;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Ticket> getTickets() {
        return tickets.keySet().stream().toList();
    }

    public Duration getParkingDuration(Ticket ticket) {
        if (!parkingTimes.containsKey(ticket)) {
            throw new UnrecognizedTicketException();
        }

        LocalDateTime parkingTime = parkingTimes.get(ticket);
        return Duration.between(parkingTime, LocalDateTime.now());
    }

    public Map<Integer, String> getParkingStatus() {
        return tickets.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().position(),
                        entry -> entry.getValue().plateNumber()
                ));
    }

    public String getParkingTimeDetails(Ticket ticket) {
        if (!parkingTimes.containsKey(ticket)) {
            throw new UnrecognizedTicketException();
        }

        LocalDateTime entryTime = parkingTimes.get(ticket);
        LocalDateTime exitTime = LocalDateTime.now();
        Duration duration = Duration.between(entryTime, exitTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String entryTimeStr = entryTime.format(formatter);
        String exitTimeStr = exitTime.format(formatter);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;

        String durationStr = (days > 0 ? days + " days + " : "") + hours + " hours " + minutes + " minutes";

        return "Entry Time: " + entryTimeStr + "\nExit Time: " + exitTimeStr + "\nParking Duration: " + durationStr;
    }

    public double calculateParkingFees(Ticket ticket) {
        if (!parkingTimes.containsKey(ticket)) {
            throw new UnrecognizedTicketException();
        }

        Duration duration = getParkingDuration(ticket);
        long totalMinutes = duration.toMinutes();
        long feeUnits = (totalMinutes + 14) / 15; // Round up to the next 15 minutes
        return feeUnits * 4.0;
    }

}