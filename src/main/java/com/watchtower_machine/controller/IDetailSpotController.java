package com.watchtower_machine.controller;

import java.util.List;
import com.watchtower_machine.model.DetailSpot;

public interface IDetailSpotController {

    public String createObject (DetailSpot object);

    public String updateObject (int id, DetailSpot newSpotData);

    public String deleteObject (int id);

    public DetailSpot readObjectById (int id);

    public List<DetailSpot> readAll ();

}
