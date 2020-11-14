package com.watchtower_machine.dao;

import com.watchtower_machine.entity.DetailSpot;

import java.util.List;
import java.util.Optional;

public interface IDetailSpotDao {

    //TODO: Virer cet ID
    int createSpot(int id, DetailSpot spot);

    default int createSpot(DetailSpot spot) {
        int id = (int) Math.random();
        return createSpot(id, spot);
    }

    List<DetailSpot> selectAllSpots();

    Optional<DetailSpot> selectSpotById(int id);

    void updateSpotById(int id, DetailSpot newSpotData);

    int deleteSpot (int id);
}
