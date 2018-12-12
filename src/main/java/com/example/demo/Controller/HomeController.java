package com.example.demo.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {


    @Autowired
    userService userService;


    @RequestMapping("/")
    public String Home() {

        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String Index() {

        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (userService.checkUserExists(user.getUsername(), user.getEmail())) {
            if (userService.checkEmailExists(user.getEmail())) {

                model.addAttribute("emailExists", true);
            }
            if (userService.checkUsernameExists(user.getUsername())) {

                model.addAttribute("usernameExists", true);

            }

            return "signup";

        } else {


            userService.createUser(user, null);
            return "redirect:/";


        }


    }


}
