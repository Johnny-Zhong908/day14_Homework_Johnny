package org.afs.pakinglot.domain.model;

import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {
    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots, new SequentiallyStrategy());
    }
}