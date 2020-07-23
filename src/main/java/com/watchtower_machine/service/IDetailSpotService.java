package com.watchtower_machine.service;

import com.watchtower_machine.model.DetailSpot;

import java.util.List;
import java.util.Optional;

public interface IDetailSpotService {

    public int create (DetailSpot object);

    public int update (int id, DetailSpot newSpotData);

    public int delete (int id);

    public Optional<DetailSpot> readById (int id);

    public List<DetailSpot> readAll ();

}
