package com.watchtower_machine.controller;

import java.util.List;
import com.watchtower_machine.entity.DetailSpot;

public interface IDetailSpotController {

    public void createObject (DetailSpot object);

    public String updateObject (int id, DetailSpot newSpotData);

    public String deleteObject (int id);

    public DetailSpot readObjectById (int id);

    public List<DetailSpot> readAll ();

}
