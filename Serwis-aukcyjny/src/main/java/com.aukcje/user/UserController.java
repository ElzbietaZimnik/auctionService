package com.aukcje.user;

import com.aukcje.address.Address;

import com.aukcje.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

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
        model.addAttribute("dto", new DtoUserRegistration());
        return "registerForm";
    }

    @ModelAttribute("dtoUserRegistration")
    public DtoUserRegistration dtoUserRegistration() {
        return new DtoUserRegistration();
    }

    @PostMapping("/register")
    public String registryNewUser(@ModelAttribute @Valid DtoUserRegistration dto,
                                  BindingResult bindResult) {
        log.info("Dodanie nowego użytkownika {}", dto);

        User existing = userService.findByUserAccountName(dto.getUserAccountName());
        if (existing != null) {
            bindResult.rejectValue("userAccountName", null, "Konto o podanej nazwie użytkownika już istnieje");
        }
        if (bindResult.hasErrors()) {
            //return "registerForm";
            return "test";
        } else {
            User user = new User();
            user.setUserAccountName(dto.getUserAccountName());
            user.setCity(dto.getCity());
            user.setLoginByEmail(dto.getLoginByEmail());
            user.setRegion(dto.getRegion());
            user.setPassword(dto.getPassword());
            user.setAccountCreationDate(LocalDateTime.now());
            Address address = new Address();
            address.setStreet(dto.getStreet());
            address.setNumber(dto.getNumber());
            address.setCityCode(dto.getCityCode());
            user.setAddress(address);
            userService.addWithDefaultRole(user);
            return "registerSuccsess";
        }
    }

    @PostMapping("/users/delete/{id}")
    public String remove(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }


    @GetMapping("/usersList")
    public String findAll(Model model) {
        log.info("Zwracanie listy użytkowników");
        List<User> all = userService.findAll();
        if (all != null) {
            List<DtoUserRegistration> collect = all.stream()
                    .map(user -> {
                        DtoUserRegistration d = new DtoUserRegistration();
                        d.setId(user.getId());
                        d.setUserAccountName(user.getUserAccountName());
                        d.setLoginByEmail(user.getLoginByEmail());
                        d.setCity(user.getCity());
                        d.setRegion(user.getRegion());
                        d.setAccountCreationDate(user.getAccountCreationDate());
                        return d;
                    })
                    .collect(Collectors.toList());
            model.addAttribute("user", collect);
        } else {
            System.out.println("Nie znaleziono żadnego użytkownika");
        }
        return "users";
    }

//    @GetMapping(value = "/login")
//    public String loginPage(Model model, DtoUserRegistration dto) {
//        log.info("Logowanie użytkownika {}", dto);
//        return "loginPage";
//    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage(Model model, DtoUserRegistration dto) {
        log.info("Wylogowanie użytkownika {}", dto);
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

//    @GetMapping("/admin")
//    public String adminPage(Model model, Principal principal) {
//        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//        return "adminPage";
//    }

//    @GetMapping("/403")
//    public String accessDenied(Model model, Principal principal) {
//        if (principal != null) {
//            org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//            String userInfo = WebUtils.toString(loginedUser);
//            model.addAttribute("userInfo", userInfo);
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//        }
//        return "403Page";
//    }

//    @GetMapping(value = "/userInfo")
//    public String userInfo(Model model, Principal principal) {
//        log.info("Po poprawnym zalogowaniu");
//        String userName = principal.getName();
//        System.out.println("User Name: " + userName);
//        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);
//        return "userInfoPage";
//    }
}

