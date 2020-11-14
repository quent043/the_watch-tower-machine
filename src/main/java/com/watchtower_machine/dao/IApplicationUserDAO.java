package com.watchtower_machine.dao;

import com.watchtower_machine.auth.ApplicationUser;

import java.util.Optional;

public interface IApplicationUserDAO {

    Optional<ApplicationUser> selectApplicationUserByUsername(String Username);
}
