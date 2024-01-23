package ru.Itransition.task3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import ru.Itransition.task3.model.Status;
import ru.Itransition.task3.model.User;
import ru.Itransition.task3.repository.RoleRepository;
import ru.Itransition.task3.repository.UserRepository;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void register(UserRegistration userRegistration){
        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setFirstName(userRegistration.getFirstName());
        user.setLastName(userRegistration.getLastName());
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    public Model showProfile(Model model, User user) {
        model.addAttribute("user", user);
        return model;
    }

    public String userUpdate(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "edit-profile";
        userRepository.save(user);
        return "redirect:/allAdmin";
    }

    public String updatePassword(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "edit-profile";

        userRepository.save(user);
        return "redirect:/allAdmin";
    }
}
