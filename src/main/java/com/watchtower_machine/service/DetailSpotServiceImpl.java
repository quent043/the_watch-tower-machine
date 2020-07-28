package com.watchtower_machine.service;

import com.watchtower_machine.dao.IDetailSpotDao;
import com.watchtower_machine.model.DetailSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("spotServiceMk1")
public class DetailSpotServiceImpl implements IDetailSpotService{

    private final IDetailSpotDao dao;
    private List<DetailSpot> allSpots;
    private Optional<DetailSpot> selectedSpot;

    @Autowired
    public DetailSpotServiceImpl(@Qualifier("mysql") IDetailSpotDao spotDao) {
        this.dao = spotDao;
    }

    @Override
    //TODO On retourne un int en attendant de set JPA Hibernate, où on retournera un DetailSpot directement.
    public int create(DetailSpot spot) {
        System.out.println("DetailSpotService Mk1: Spot Creation");
        dao.createSpot(spot);
        return 1;
    }

    @Override
    public int update(int id, DetailSpot newSpotData) {
        System.out.println("DetailSpotService Mk1: updateSpot() running...");
        selectedSpot = dao.selectSpotById(id);
        //On vérifie que le spot à update existe
        if (selectedSpot.isEmpty()) {
            System.out.println("DetailSpotService Mk1: - updateSpot(): surf spot with id: " + id + " did not return anything");
            return 0;
        } else {
            dao.updateSpotById(id, newSpotData);
            System.out.println("DetailSpotService Mk1: - updateSpot(): surf spot with id: " + id + " successfully updated");
            return 1;
        }
    }

    @Override
    public int delete(int id) {
        System.out.println("DetailSpotService Mk1: deleteSpot() running...");
        selectedSpot = dao.selectSpotById(id);
        if (selectedSpot.isEmpty()) {
            System.out.println("DetailSpotService Mk1: - deleteSpot(): surf spot with id: " + id + " did not return anything");
            return 0;
        } else {
            dao.deleteSpot(id);
            System.out.println("DetailSpotService Mk1: - deleteSpot(): surf spot with id: " + id + " successfully deleted");
         return 1;
        }
    }

    @Override
    public Optional<DetailSpot> readById(int id) {
//        if (dao.selectSpotById(id) != null) {
            System.out.println("DetailSpotService Mk1: selectedSpot() running...");
            selectedSpot = dao.selectSpotById(id);
            System.out.println("DetailSpotService Mk1: selectedSpot:" + selectedSpot.get().getNom());
//            return selectedSpot;
//        } else {
//
//            return Optional.empty();
//        }

        return selectedSpot;
    }

    @Override
    public List<DetailSpot> readAll() {
        System.out.println("DetailSpotService Mk1 - readAll() running...");
        allSpots = dao.selectAllSpots();
        return allSpots;
    }
}
