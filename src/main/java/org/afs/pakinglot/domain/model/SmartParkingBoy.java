package org.afs.pakinglot.domain.model;

import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, new MaxAvailableStrategy());
    }
}