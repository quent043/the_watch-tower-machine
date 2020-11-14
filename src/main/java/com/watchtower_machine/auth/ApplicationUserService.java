package com.watchtower_machine.auth;

import com.watchtower_machine.dao.IApplicationUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //TODO: A déclarer dans la config de Spring comme un Bean quand le service sera utiisé
public class ApplicationUserService implements UserDetailsService {


    private final IApplicationUserDAO applicationUserDAO;

    @Autowired
    public ApplicationUserService(@Qualifier("UserDaoMySql") IApplicationUserDAO applicationUserDAO) {
        this.applicationUserDAO = applicationUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return applicationUserDAO.selectApplicationUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }
}
