package com.development.secure.software.eventplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 2:05 PM
 * @Version 1.0
 * Page Navigation Controller
 */
@Controller
public class NavigationController {

    @Autowired
    OAuth2AuthorizedClientService clientService;

    /**
     * Login Page navigation
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping(value = "/login.html/")
    public void showHomePage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.sendRedirect("/login.html");
    }

    /**
     * Events Page navigation
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @GetMapping(value = "/")
    public void showLoginPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.sendRedirect("/events.html");
    }

    @GetMapping(value = "api/google/grant")
    public void showEventsManagementPage(HttpServletResponse httpServletResponse) throws IOException {


        httpServletResponse.sendRedirect("/events.html");

    }


}
