package com.watchtower_machine.dao;

import com.google.common.collect.Lists;
import com.watchtower_machine.auth.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.watchtower_machine.security.ApplicationUserRole.*;

@Repository("UserDaoMySql")
public class ApplicationUserDaoMySqlImpl implements IApplicationUserDAO {

    //On a défini le "BCryptPasswordEncoder" comme bean dans la classe config "PasswordConfig". Donc pas besoin de @Autowire
    private final PasswordEncoder passwordEncoder;


    public ApplicationUserDaoMySqlImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String Username) {

        return getApplicationUsers().stream()
                .filter(applicationUser -> Username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "Quentin",
                        passwordEncoder.encode("swayze"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        "Bhodi",
                        passwordEncoder.encode("swayze"),
                        BASIC_USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        "reader",
                        passwordEncoder.encode("swayze"),
                        READER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser(
                        "writer",
                        passwordEncoder.encode("swayze"),
                        WRITER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
        return applicationUsers;
    }


}


//    public int createSpot(int id, DetailSpot spot) {
//
//        try {
//            System.out.println("Dao MySql: Spot creation.");
//            session = Connmanagement.getSession();
//            session.beginTransaction();
//            session.save(spot);
//            session.getTransaction().commit();
//            System.out.println("MySql Dao - createSpot(): Surf spot: " + spot.getNom() + "successfully created");
//        } catch (Exception e) {
//            System.out.println("MySql Dao - createSpot(): - Problem while creating spot with id: " + spot.getId());
//            session.getTransaction().rollback();
//            e.printStackTrace();
//        }
//
//        return 0;
//    }