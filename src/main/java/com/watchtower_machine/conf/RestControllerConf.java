package com.watchtower_machine.conf;

import com.watchtower_machine.dao.DetailSpotDaoMysqlImpl;
import com.watchtower_machine.dao.DetailSpotDaoPostgresImpl;
import com.watchtower_machine.dao.IDetailSpotDao;
import com.watchtower_machine.service.DetailSpotServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("business.properties")
//TODO: Possible to define in a property file the name of the dependency to be injected?
public class RestControllerConf {

    //**** DAO ****
    @Bean
//    @Primary <----- Pas besoin tant que le nom de la méthode matche celui de l'injection choisie
    public DetailSpotDaoMysqlImpl mySqlDao() {
        DetailSpotDaoMysqlImpl mySql = new DetailSpotDaoMysqlImpl();
        return mySql;
    }

    @Bean
    public DetailSpotDaoPostgresImpl postgreDao() {
        DetailSpotDaoPostgresImpl postgre = new DetailSpotDaoPostgresImpl();
        return postgre;
    }

    //**** Service ****
    @Bean
    public DetailSpotServiceImpl spotServiceMk1(IDetailSpotDao mySqlDao) {
        DetailSpotServiceImpl serviceImpl = new DetailSpotServiceImpl(mySqlDao);
        return serviceImpl;
    }

    @Bean(initMethod = "databaseConnectionInitializer")
    public DatabaseInit initializer(){
        return new DatabaseInit();
    }
    
}
