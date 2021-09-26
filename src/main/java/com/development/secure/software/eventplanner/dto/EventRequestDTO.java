package com.development.secure.software.eventplanner.dto;

import lombok.Data;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 9:57 PM
 * @Version 1.0
 * Event RequestDTO class
 */
@Data
public class EventRequestDTO {
    private String id;
    private String startDateTime;
    private String endDateTime;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String summary;
    private String description;
}
