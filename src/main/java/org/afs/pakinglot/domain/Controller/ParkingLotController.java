// src/main/java/org/afs/pakinglot/controller/ParkingLotController.java
package org.afs.pakinglot.domain.Controller;

import org.afs.pakinglot.domain.model.Car;
import org.afs.pakinglot.domain.model.Ticket;
import org.afs.pakinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/park")
    public Ticket park(@RequestBody Car car, @RequestParam String strategy) {
        return parkingLotService.park(car, strategy);
    }

    @GetMapping("/fetch")
    public Car fetch(@RequestParam Ticket ticket) {
        return parkingLotService.fetch(ticket);
    }

    @GetMapping("/duration")
    public Duration getParkingDuration(@RequestParam Ticket ticket) {
        return parkingLotService.getParkingDuration(ticket);
    }

    @GetMapping("/status/{id}")
    public Map<Integer, String> getParkingStatus(@PathVariable int id) {
        return parkingLotService.getParkingStatus(id);
    }

    @GetMapping("/time-details")
    public String getParkingTimeDetails(@RequestParam Ticket ticket) {
        return parkingLotService.getParkingTimeDetails(ticket);
    }

    @GetMapping("/fees")
    public double calculateParkingFees(@RequestParam Ticket ticket) {
        return parkingLotService.calculateParkingFees(ticket);
    }
}