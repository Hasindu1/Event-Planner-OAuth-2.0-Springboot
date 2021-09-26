package com.development.secure.software.eventplanner.model;

import lombok.Data;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 9:31 PM
 * @Version 1.0
 * Model class for Event
 */
@Data
public class Event {
    private String id;
    private String startDateTime;
    private String endDateTime;
    private String summary;
    private String description;

}
