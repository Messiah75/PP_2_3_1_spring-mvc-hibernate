package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "users";
    }

    @GetMapping("/signup")
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "user-info";
    }

    @PostMapping("/addUser")
    public String save(@ModelAttribute ("user") User user) {

        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("updateuser", userService.findById(id));
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute ("user") User user) {
        userService.updateUser(user);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/";
    }
}
