package com.watchtower_machine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginControllerImpl implements ILoginController {


    public String getLoginView() {
        return "login"; //<----- Doit être le même nom que la page html sans l'extension
    }

    @GetMapping("/login")
    @Override
    public void login(String userName, String password) {

    }

    @Override
    public void logout() {

    }
}
