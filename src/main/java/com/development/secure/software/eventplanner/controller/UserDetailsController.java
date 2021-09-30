package com.development.secure.software.eventplanner.controller;

import com.development.secure.software.eventplanner.dto.UserDetailsResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/30/2021 11:50 PM
 * @Version 1.0
 * Rest Controller for user details management
 */
@RestController
@RequestMapping("/users-api")
public class UserDetailsController {

    /**
     * Rest endpoint for get logged in user details
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails() throws IOException {
        Map<String, Object> userDetails = ((DefaultOAuth2User) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal()).getAttributes();

        UserDetailsResponseDTO userDetailsResponseDTO = new UserDetailsResponseDTO();
        userDetailsResponseDTO.setName(userDetails.get("name").toString());
        userDetailsResponseDTO.setProfilePicture(userDetails.get("picture").toString());

        return new ResponseEntity<>(userDetailsResponseDTO, HttpStatus.OK);
    }


}
