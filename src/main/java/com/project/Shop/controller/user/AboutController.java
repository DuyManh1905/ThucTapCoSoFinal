package com.project.Shop.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//done

@Controller
public class AboutController {


    @GetMapping("getabout")
    public String getAbout(Model model) {
        return "user/about";
    }
}
