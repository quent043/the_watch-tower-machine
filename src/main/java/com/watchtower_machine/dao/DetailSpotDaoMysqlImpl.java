package com.watchtower_machine.dao;

import com.watchtower_machine.conf.Connmanagement;
import com.watchtower_machine.model.DetailSpot;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("mysql")
public class DetailSpotDaoMysqlImpl implements IDetailSpotDao {
    private List<DetailSpot> allSpots = new ArrayList<>();
    private Optional<DetailSpot> selectedSpot;
    private Session session;

    public DetailSpotDaoMysqlImpl(List<DetailSpot> allSpots, Optional<DetailSpot> selectedSpot) {
        this.allSpots = allSpots;
        this.selectedSpot = selectedSpot;
    }

    @Override
    public int createSpot(int id, DetailSpot spot) {

        try {
            System.out.println("Dao MySql: Spot creation.");
            session = Connmanagement.getSession();
            session.beginTransaction();
            session.save(spot);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    //TODO: pas encore connecté à la DB
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

        session = Connmanagement.getSession();
        session.beginTransaction();
        allSpots = (List<DetailSpot>) session.createQuery("FROM DetailSpot").list();
        session.getTransaction().commit();

        return allSpots;
    }

    @Override
    public Optional<DetailSpot> selectSpotById(int id) {
        //TODO: Faire une querry spécifique à un ID, et non faire un getAll(). PB: querry pas finie, et querry ne détecte pas ke type de variable
//        session = Connmanagement.getSession();
//        session.beginTransaction();
//        selectedSpot = (Optional<DetailSpot>) session.createQuery("FROM DetailSpot WHERE ").stream()
//                .filter(spot -> spot.getId() == id)
//                .findFirst();
//        session.getTransaction().commit();

        allSpots = selectAllSpots();
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
            session = Connmanagement.getSession();
            session.beginTransaction();
            session.delete(potentialDetailSpot.get());
            session.getTransaction().commit();

            System.out.println("MySql Dao: Surf spot: "+ potentialDetailSpot.get().getNom() + " deleted from the database.");
            return 1;
        }
    }
}

