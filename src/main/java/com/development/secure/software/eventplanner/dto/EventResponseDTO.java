package com.development.secure.software.eventplanner.dto;

import lombok.Data;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 10:27 PM
 * @Version 1.0
 * EventResponse DTO class
 */
@Data
public class EventResponseDTO {
    private String id;
    private String startDateTime;
    private String endDateTime;
    private String summary;
    private String description;
}
