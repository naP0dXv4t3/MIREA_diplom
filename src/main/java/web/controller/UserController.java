package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/")
    public String allUsers(Model model) {
        List<User> allUsers = userService.listUsers();
        model.addAttribute("usersList", allUsers);
        return "usersList";
    }
    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user) {
        System.out.println("new user");
        return "user/user-create";
    }
    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "user/user-create";
        }

        userService.add(user);
        return "redirect:/users";
    }
    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }


}
