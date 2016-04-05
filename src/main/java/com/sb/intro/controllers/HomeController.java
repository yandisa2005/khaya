package com.sb.intro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(method= RequestMethod.GET, value="/home")
    public ModelAndView homePage() {
        return new ModelAndView("index.html");
    }

    @RequestMapping("/docs")
    public ModelAndView docPage() {
        return new ModelAndView("docIndex.html");
    }
}
