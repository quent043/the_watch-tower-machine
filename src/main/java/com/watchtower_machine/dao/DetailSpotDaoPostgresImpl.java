package com.watchtower_machine.dao;

import com.watchtower_machine.entity.DetailSpot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository("postgres")
public class DetailSpotDaoPostgresImpl implements IDetailSpotDao {
    //TODO allSpots simule la DB en attendant que je la crée
    private List<DetailSpot> allSpots = new ArrayList<>();
    private Optional<DetailSpot> selectedSpot;

    @Override
    public int createSpot(int id, DetailSpot spot) {
        System.out.println("Dao Postgres: Spot creation.");
        this.allSpots.add(spot);
        return 0;
    }

    @Override
    public void updateSpotById(int id, DetailSpot newSpotData) {
        selectSpotById(id)
                .map(p -> {
                    int indexOfSpotToUpdate = allSpots.indexOf(p);
                    if (indexOfSpotToUpdate >= 0) {
                        allSpots.set(indexOfSpotToUpdate, newSpotData);
                    } else {
                        System.out.println("Dao Postgres: No Spot to update");
                    }
                    return 0;
                });
    }




    @Override
    public List<DetailSpot> selectAllSpots() {
        System.out.println("Postgres Dao - selectAllSpots():");
        return allSpots;
    }

    @Override
    public Optional<DetailSpot> selectSpotById(int id) {
        selectedSpot = allSpots.stream()
                .filter(spot -> spot.getId() == id)
                .findFirst();
        if (selectedSpot.isEmpty()){
            System.out.println("Postgres Dao: selectSpotById (" + id + ") did not retrieve any value");
            return Optional.empty();
        } else {
            System.out.println("Postgres Dao: selectSpotById (" + id + ") retrieved the spot : " + selectedSpot.get().getNom());
            return selectedSpot;
        }
    }

    @Override
    public int deleteSpot(int id) {
        System.out.println("Postgres Dao - deleteSpot():");
        Optional<DetailSpot> potentialDetailSpot = selectSpotById(id);
        if(potentialDetailSpot.isEmpty()){
            System.out.println("Postgres Dao: The surf spot with the id: "+ id + " does not exist in the database");
            return 0;
        }else {
            allSpots.remove(potentialDetailSpot.get());
            System.out.println("Postgres Dao: Surf spot: "+ potentialDetailSpot.get().getNom() + " deleted from the database.");
            return 1;
        }
    }
}

