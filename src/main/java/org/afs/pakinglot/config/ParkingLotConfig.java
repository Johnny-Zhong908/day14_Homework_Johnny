// src/main/java/org/afs/pakinglot/config/ParkingLotConfig.java
package org.afs.pakinglot.config;

import org.afs.pakinglot.domain.model.ParkingLot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingLotConfig {

    @Bean
    public ParkingLot plazaPark() {
        return new ParkingLot(1, "The Plaza Park", 9);
    }

    @Bean
    public ParkingLot cityMallGarage() {
        return new ParkingLot(2, "City Mall Garage", 12);
    }

    @Bean
    public ParkingLot officeTowerParking() {
        return new ParkingLot(3, "Office Tower Parking", 9);
    }
}