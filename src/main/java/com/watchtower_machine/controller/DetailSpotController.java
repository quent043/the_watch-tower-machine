package com.watchtower_machine.controller;

import com.watchtower_machine.model.DetailSpot;
import com.watchtower_machine.service.IDetailSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/the_watch_tower")
@RestController
public class DetailSpotController implements IDetailSpotController{

    private final IDetailSpotService spotService;
    private List<DetailSpot> allSpots;
    private Optional<DetailSpot> selectedSpot;

    @Autowired
    public DetailSpotController(@Qualifier("spotServiceMk1") IDetailSpotService service) {
        this.spotService = service;
    }

    @PostMapping("/createSpot")
    @Override
    public String createObject(@RequestBody DetailSpot spot) {
        spotService.create(spot);

        if(spot != null)
            return "Spot Successfully Created";
        else
        return "Problem while creating new spot (controller)";
    }

    @PutMapping(path = "{id}")
    @Override
    public String updateObject(@PathVariable("id") int id, @RequestBody DetailSpot newSpotData) {
        selectedSpot = spotService.readById(id);
        if (selectedSpot.isEmpty()) {
            System.out.println("updateObject() did not update any value for id: " + id);
            return null;
        } else {
            spotService.update(id, newSpotData);
            return "Spot: " + selectedSpot.get().getNom() + " successfully updated (controller)";
        }
    }

    @DeleteMapping(path = "{id}")
    @Override
    public String deleteObject(@PathVariable("id") int id) {
        selectedSpot = spotService.readById(id);
        if (selectedSpot.isEmpty()) {
            System.out.println("deleteObject() did not delete any value for id: " + id);
            return null;
        } else {
            spotService.delete(id);
            return "Spot: " + selectedSpot.get().getNom() + " successfully deleted (controller)";
        }
    }

    @GetMapping(path = "{id}")
    @Override
    public DetailSpot readObjectById(@PathVariable("id") int id) {
        selectedSpot = spotService.readById(id);

//        return selectedSpot;
        if (selectedSpot.isEmpty()){
            System.out.println("readObjectById request did not retrieve any value (controller)");
        }
        return selectedSpot
                .orElse(null);
    }

    @GetMapping("/getAll")
    @Override
    public List<DetailSpot> readAll() {
        allSpots = spotService.readAll();
        if (allSpots != null && allSpots.size() != 0) {
            System.out.println("readAll request went through successfully (controller)");
        return allSpots;
        }
        else {
            System.out.println("readAll request returned nothing (controller)");
            return allSpots;
        }
    }
}
