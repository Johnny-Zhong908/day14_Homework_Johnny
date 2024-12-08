// src/main/java/org/afs/pakinglot/controller/ParkingBoyController.java
package org.afs.pakinglot.domain.Controller;

import org.afs.pakinglot.domain.model.Car;
import org.afs.pakinglot.domain.model.Ticket;
import org.afs.pakinglot.domain.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking-boys")
@CrossOrigin(origins = "http://localhost:3000")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService parkingBoyService;

    @PostMapping("/park")
    public String park(@RequestBody Car car, @RequestParam String strategy, @RequestParam String parkingBoyType) {
        if (parkingBoyService.isValidPlateNumber(car.plateNumber())) {
            try {
                Ticket ticket = parkingBoyService.park(car, strategy, parkingBoyType);
                return "Ticket: your plateNumber is: " + car.plateNumber();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        } else {
            throw new IllegalArgumentException("Invalid license plate format.");
        }
    }

    @GetMapping("/fetch")
    public Car fetch(@RequestParam String plateNumber, @RequestParam String parkingBoyType) {
        Ticket ticket = parkingBoyService.findTicketByPlateNumber(plateNumber, parkingBoyType);
        return parkingBoyService.fetch(ticket, parkingBoyType);
    }
}