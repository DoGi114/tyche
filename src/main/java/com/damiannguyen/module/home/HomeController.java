package com.damiannguyen.module.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/", "index.html"})
    public String getHome() {
        return "home/index";
    }

}
