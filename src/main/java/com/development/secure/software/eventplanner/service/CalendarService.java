package com.development.secure.software.eventplanner.service;

import com.development.secure.software.eventplanner.dto.EventRequestDTO;
import com.development.secure.software.eventplanner.dto.EventResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 9:21 PM
 * @Version 1.0
 * Service Layer for Calendar Service
 */
@Service
public interface CalendarService {
     EventResponseDTO addEvent(EventRequestDTO eventRequestDTO) throws IOException;
     List<EventResponseDTO> getAllEvents() throws IOException;
}
