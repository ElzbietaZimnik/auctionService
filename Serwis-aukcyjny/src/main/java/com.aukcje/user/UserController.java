package com.aukcje.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homepage() {
        log.info("Wyświetlamy stronę główną");
        return "index";
    }

    @GetMapping("/categories")
    public String kategorie() {
        log.info("Wyświetlamy kategorie");
        return "categories";
    }

    @GetMapping("/register")
    public String register(Model model) {
        log.info("Rejestracja");
        model.addAttribute("user", new User());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user,
                          BindingResult bindResult) {
        log.info("Dodanie nowego użytkownika {}", user);
        if(bindResult.hasErrors())
          //  return "registerForm";
            return "test";
        else {
            userService.add(user);
            return "registerSuccess";
        }
    }
    //Model and Viev
    @GetMapping("/usersList")  //tutaj podpinamy plik users.html
    public ModelAndView getUsersView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");  //"users" od users.html
        modelAndView.addObject("users", userService);
        return modelAndView;
    }



    @GetMapping("/login")
   public String login() {
        log.info("Logujemy nowego uzytkownika");
        return "login";
    }


}
