package com.watchtower_machine.controller;

import com.watchtower_machine.entity.Admin;
import com.watchtower_machine.entity.BasicUser;
import com.watchtower_machine.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/the_watch_tower/user")
public class UserManagementRestController {

    private static final List<User> USERS = Arrays.asList(
            new BasicUser("Bhodi","","Bhodi","swayze"),
            new Admin("Quentin","Quentin","Quentin","swayze", true)
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_BASIC_USER')")
    public List<User> getAllStudents() {
        return USERS;
    }
    @PostMapping
    //TODO/ Ne veut pas prendre la classe abstraite
    public void createUser(@RequestBody Admin user) {
        System.out.println(user);
    }
    @DeleteMapping(path = "{userName}")
    public void delete (@PathVariable("userName") String userName) {
        System.out.println(userName);
    }
    @PutMapping(path = "{userName}")
    public void updateUser (@PathVariable("userName") String userName
//            , @RequestBody User newData
    ){
//        System.out.println(String.format("%s; %s", userName , newData));
        System.out.println(String.format("%s", userName));
    }
}
