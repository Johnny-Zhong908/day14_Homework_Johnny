package org.afs.pakinglot.domain.model;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, new AvailableRateStrategy());
    }
}