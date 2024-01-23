package ru.Itransition.task3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.Itransition.task3.model.User;

@Controller
public class GisController {

    @GetMapping("/gis")
    public String showUsers(Model model) {
//        Iterable<User> user = userRepository.findAll();
//        model.addAttribute("user", user);
        return "gis";
    }

}
