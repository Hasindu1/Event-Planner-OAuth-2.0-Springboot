package com.development.secure.software.eventplanner.controller;

import com.development.secure.software.eventplanner.dto.EventRequestDTO;
import com.development.secure.software.eventplanner.dto.EventResponseDTO;
import com.development.secure.software.eventplanner.service.CalendarService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 11:06 PM
 * @Version 1.0
 * Rest Controller for events management
 */
@RestController
@RequestMapping("/api")
public class EventRestController {
    @Autowired
    private CalendarService calendarService;

    /**
     * Rest endpoint for add a new event
     *
     * @param eventRequestDTO
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<EventResponseDTO> addNewEvent(@NotNull @RequestBody EventRequestDTO eventRequestDTO, BindingResult errors) throws IOException {

        EventResponseDTO savedEventResponseDTO = calendarService.addEvent(eventRequestDTO);
        return new ResponseEntity<>(savedEventResponseDTO, HttpStatus.CREATED);
    }

    /**
     * Rest endpoint for get all events
     *
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() throws IOException {
        List<EventResponseDTO> eventList = calendarService.getAllEvents();

        if (eventList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }
}
