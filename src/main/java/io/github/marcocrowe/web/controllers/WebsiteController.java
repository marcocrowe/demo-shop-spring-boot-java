package io.github.marcocrowe.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebsiteController {


    @GetMapping("/")
    public String index() {
        return TemplatePaths.INDEX;
    }
}
