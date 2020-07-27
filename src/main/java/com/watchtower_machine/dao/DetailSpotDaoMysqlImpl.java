package com.watchtower_machine.dao;

import com.watchtower_machine.model.DetailSpot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql")
public class DetailSpotDaoMysqlImpl implements IDetailSpotDao {
    //TODO allSpots simule la DB en attendant que je la crée
    private List<DetailSpot> allSpots = new ArrayList<>();
    private Optional<DetailSpot> selectedSpot;

    @Override
    public int createSpot(int id, DetailSpot spot) {
        System.out.println("Dao MySql: Spot creation.");
        this.allSpots.add(spot);
        return 0;
    }

    @Override
    public int updateSpotById(int id, DetailSpot newSpotData) {
        return selectSpotById(id)
                .map(p -> {
                    int indexOfSpotToUpdate = allSpots.indexOf(p);
                    if (indexOfSpotToUpdate >= 0) {
                        allSpots.set(indexOfSpotToUpdate, newSpotData);
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }


    @Override
    public List<DetailSpot> selectAllSpots() {
        System.out.println("MySql Dao - selectAllSpots():");
        return allSpots;
    }

    @Override
    public Optional<DetailSpot> selectSpotById(int id) {
        selectedSpot = allSpots.stream()
                .filter(spot -> spot.getId() == id)
                .findFirst();
        if (selectedSpot.isEmpty()){
            System.out.println("MySql Dao: selectSpotById (" + id + ") did not retrieve any value");
            return Optional.empty();
        } else {
            System.out.println("MySql Dao: selectSpotById (" + id + ") retrieved the spot : " + selectedSpot.get().getNom());
            return selectedSpot;
        }
    }

    @Override
    public int deleteSpot(int id) {
        System.out.println("MySql Dao - deleteSpot():");
        Optional<DetailSpot> potentialDetailSpot = selectSpotById(id);
        if(potentialDetailSpot.isEmpty()){
            System.out.println("MySql Dao: The surf spot with the id: "+ id + " does not exist in the database");
            return 0;
        }else {
            allSpots.remove(potentialDetailSpot.get());
            System.out.println("MySql Dao: Surf spot: "+ potentialDetailSpot.get().getNom() + " deleted from the database.");
            return 1;
        }
    }
}

