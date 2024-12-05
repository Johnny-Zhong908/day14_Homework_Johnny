package org.afs.pakinglot.config;

import org.afs.pakinglot.domain.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ParkingBoyConfig {

    @Bean
    public StandardParkingBoy standardParkingBoy(ParkingLot plazaPark) {
        return new StandardParkingBoy(List.of(plazaPark));
    }

    @Bean
    public SmartParkingBoy smartParkingBoy(ParkingLot cityMallGarage) {
        return new SmartParkingBoy(List.of(cityMallGarage));
    }

    @Bean
    public SuperSmartParkingBoy superSmartParkingBoy(ParkingLot officeTowerParking) {
        return new SuperSmartParkingBoy(List.of(officeTowerParking));
    }
}