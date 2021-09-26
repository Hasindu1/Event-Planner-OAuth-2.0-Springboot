package com.development.secure.software.eventplanner.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/26/2021 3:41 PM
 * @Version 1.0
 * Error Handler
 */
@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/403")
    public String forbidden(Model model) {
        return "error/403";
    }

    @GetMapping("/404")
    public String notFound(Model model) {
        return "error/404";
    }

    @GetMapping("/500")
    public String internal(Model model) {
        return "error/500";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "error/access-denied";
    }

}
