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
            System.out.println("MySql Dao - createSpot(): Surf spot: " + spot.getNom() + "successfully created");
        } catch (Exception e) {
            System.out.println("MySql Dao - createSpot(): - Problem while creating spot with id: " + spot.getId());
            session.getTransaction().rollback();
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void updateSpotById(int id, DetailSpot newSpotData) {
        System.out.println("MySql Dao - updateSpotById():");
        try {
            int spotToUpdateId = selectSpotById(id).get().getId();
            if(spotToUpdateId == 0 || spotToUpdateId != id) {
                System.out.println("MySql Dao - updateById(): - Targeted spot Id is either null, '0' or different from the data to update");
            } else {
                /*Pour update l'objet, on dois soit clear l'objet ayant le même ID de la session (.clear()) avant de le (.saveOrUpdate()). Soit le (.merge()).
                 Ici j'ai choisi merge.*/
                session = Connmanagement.getSession();
    //            session.clear();
                session.beginTransaction();
                session.merge(newSpotData);
    //            session.saveOrUpdate(newSpotData);
                session.getTransaction().commit();
                System.out.println("MySql Dao - updateSpotById(): - Spot: " + newSpotData.getNom() + "updated.");
            }
        } catch (Exception e) {
            System.out.println("MySql Dao - updateSpotById(): - Problem while updating the spot with id: " + id);
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<DetailSpot> selectAllSpots() {
        try {
            System.out.println("MySql Dao - selectAllSpots():");
            session = Connmanagement.getSession();
            session.beginTransaction();
            allSpots = (List<DetailSpot>) session.createQuery("FROM DetailSpot").list();
            session.getTransaction().commit();
            System.out.println("MySql Dao - selectAllSpots(): - All Spots Selected");
        } catch (Exception e) {
            System.out.println("MySql Dao - selectAllSpots(): - Problem while selecting all spots");
            session.getTransaction().rollback();
            e.printStackTrace();
        }

        return allSpots;
    }

    @Override
    public Optional<DetailSpot> selectSpotById(int id) {
        try {
            if(id != 0) {
                session = Connmanagement.getSession();
                session.beginTransaction();
                selectedSpot = session.createQuery("FROM DetailSpot as spot WHERE spot.id =:id ")
                        .setParameter("id", id)
                        .uniqueResultOptional();
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            System.out.println("MySql Dao: Problem executing selectSpotById (" + id + ")");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
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

