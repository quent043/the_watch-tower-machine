package com.watchtower_machine.controller;

import com.watchtower_machine.entity.Admin;
import com.watchtower_machine.entity.BasicUser;
import com.watchtower_machine.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/the_watch_tower/user")
public class UserRestController implements IController<String, User> {

    //TODO: For now, in memory database. Need to config the full controller, service & DAO later.
    private static final List<User> USERS = Arrays.asList(
            new BasicUser("Bhodi","","Bhodi","swayze"),
            new Admin("Quentin","Quentin","Quentin","swayze", true)
    );


    @Override
    public void createObject(User entity) {

    }

    @Override
    public String updateObject(String id, User newData) {
        return null;
    }

    @Override
    public String deleteObject(String id) {
        return null;
    }

    @Override
    @GetMapping("{name}")
    public User readObjectById(@PathVariable("userName") String userName) {
        User result = USERS.stream()
                .filter(user -> userName.equals(user.getUserName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user with this User Name"));
        return result;
    }

    @Override
    public List<User> readAll() {
        return null;
    }
}
