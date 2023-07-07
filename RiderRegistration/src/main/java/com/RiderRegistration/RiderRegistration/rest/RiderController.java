package com.RiderRegistration.RiderRegistration.rest;

import com.RiderRegistration.RiderRegistration.service.RiderService;
import com.RiderRegistration.RiderRegistration.entity.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
public class RiderController {
    private final RiderService riderService;

    @Autowired
    public RiderController(RiderService theRiderService) {
        riderService = theRiderService;
    }

    // !======! Get All Rider Data !======! //

    @GetMapping("/get-rider")
    public List<Rider> getAllRiders() {
        return riderService.findAll();
    }

    // !======! Get Rider Data By Id !======! //

    @GetMapping("/get-riderbyid/{riderId}")
    public Rider getRiderById(@PathVariable int riderId) {
        Rider theRider = riderService.findById(riderId);
        if (theRider == null) {
            throw new RuntimeException("Rider with id: " + riderId + " not found");
        }

        return theRider;
    }

    // !======! Create Rider Data !======! //

    @PostMapping("/create-rider")
    public Rider addRider(@RequestBody Rider theRider) {
        return riderService.save(theRider);
    }

    // !======! Update rider Data !======! //

    @PutMapping("/update-rider")
    public Rider updateRider(@RequestBody Rider theRider) {
        return riderService.save(theRider);
    }

    // !======! Delete rider Data !======! //

    @DeleteMapping("/delete-rider/{riderId}")
    public String deleteRider(@PathVariable int riderId) {
        Rider tempOrder = riderService.findById(riderId);

        if (tempOrder == null) {
            throw new RuntimeException("Rider with Id: " + riderId + "not found");
        }
        riderService.delete(riderId);

        return "Rider with id: " + riderId + " deleted";
    }
}
