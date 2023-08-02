package ru.Itransition.task3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Itransition.task3.model.Status;
import ru.Itransition.task3.model.User;
import ru.Itransition.task3.repository.UserRepository;
import ru.Itransition.task3.service.UserAuthService;
import ru.Itransition.task3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {

    private User user;
    private final UserService userService;
    private UserAuthService userAuthService;
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserAuthService userAuthService, HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse, UserRepository userRepository) {
        this.userService = userService;
        this.userAuthService = userAuthService;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.userRepository = userRepository;
    }

    @GetMapping("/allAdmin")
    public String showUsers(Model model) {
        Iterable<User> user = userRepository.findAll();
        model.addAttribute("user", user);
        return "allAdmin";
    }

    @PostMapping("/users/delete/{id}")
    private String delete(@PathVariable(value = "id") Long id) {
        user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/allAdmin";
    }

    @PostMapping("/users/block/{id}")
    private String blockUser(@PathVariable(value = "id") Long id) {
        user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.BLOCKED);
        userRepository.save(user);
        return "redirect:/allAdmin";
    }

    @PostMapping("/users/unlockUser/{id}")
    private String unlockUser(@PathVariable(value = "id") Long id) {
        user = userRepository.findById(id).orElseThrow();
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/allAdmin";
    }

    @GetMapping("/my-profile")
    public String showMyProfile(Model model) {
        userService.showProfile(model, userRepository.findByUsername(userService.getCurrentUser().getName()).orElseThrow());
        return "my-profile";
    }

    @GetMapping("/my-profile/{id}")
    public String showUserProfile(@PathVariable(name = "id") long id, Model model) {
        userService.showProfile(model, userRepository.findById(id).orElseThrow());
        return "my-profile";
    }



//    @PostMapping("/my-profile/{user}")
//    public String editMyProfile(@PathVariable User user, @Valid User newUser,
//                                BindingResult bindingResult, @RequestParam Optional<MultipartFile> newAvatar) {
//        return userService.userUpdate(user, bindingResult, newAvatar);
//    }
}