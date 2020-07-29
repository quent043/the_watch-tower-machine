package com.watchtower_machine.dao;


import com.watchtower_machine.model.DetailSpot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDetailSpotDaoJPARepository extends JpaRepository<DetailSpot, Long> {
}
