package ru.Itransition.task3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DB2Controller {

    @GetMapping("/db2")
    public String showUsers(Model model) {
//        Iterable<User> user = userRepository.findAll();
//        model.addAttribute("user", user);
        return "db2";
    }

}
