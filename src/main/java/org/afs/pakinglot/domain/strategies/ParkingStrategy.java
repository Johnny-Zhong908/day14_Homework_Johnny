package org.afs.pakinglot.domain.strategies;


import org.afs.pakinglot.domain.model.ParkingLot;

import java.util.List;

public interface ParkingStrategy {
    ParkingLot findParkingLot(List<ParkingLot> parkingLots);
}
