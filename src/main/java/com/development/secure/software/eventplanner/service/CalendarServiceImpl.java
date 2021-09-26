package com.development.secure.software.eventplanner.service;

import com.development.secure.software.eventplanner.dto.EventRequestDTO;
import com.development.secure.software.eventplanner.dto.EventResponseDTO;
import com.development.secure.software.eventplanner.interceptor.RequestInterceptor;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Hasindu Dahanayake
 * @Date 9/25/2021 9:19 PM
 * @Version 1.0
 * Service Layer Implementation for Calendar Service
 */
public class CalendarServiceImpl extends RequestInterceptor implements CalendarService {


    public CalendarServiceImpl(String accessToken) {
        super(accessToken);
    }


    @Value("${app.name}")
    private String APPLICATION_NAME;
    @Value("${app.calendar.id}")
    private String CALENDAR_ID;
    @Value("${app.timeZone}")
    private String timeZone;


    /**
     * Get All the Google calendar events
     *
     * @return
     * @throws IOException
     */
    public List<EventResponseDTO> getAllEvents() throws IOException {
        // Credential creation by providing the access token
        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

        // Access calendar services by passing the credentials
        Calendar service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();

        //Fetch all the events avialable
        Events events = service.events().list(CALENDAR_ID).setOrderBy("startTime").setSingleEvents(true)
                .execute();


        List<Event> items = events.getItems();
        List<EventResponseDTO> eventList = new ArrayList<>();

        //Map the event object back to Response object
        for (Event event : items) {

            DateTime startDateTime = event.getStart().getDateTime();
            DateTime endDateTime = event.getEnd().getDateTime();

            if (startDateTime == null) {
                startDateTime = event.getStart().getDate();
            }
            if (endDateTime == null) {
                endDateTime = event.getEnd().getDate();
            }

            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setId(event.getId());
            eventResponseDTO.setSummary(event.getSummary());
            eventResponseDTO.setDescription(event.getDescription());
            eventResponseDTO.setStartDateTime(startDateTime.toString());
            eventResponseDTO.setEndDateTime(endDateTime.toString());

            eventList.add(eventResponseDTO);
        }
        return eventList;
    }

    /**
     * Add new event to Google Calendar
     *
     * @param eventRequestDTO
     * @return
     * @throws IOException
     */
    @Override
    public EventResponseDTO addEvent(EventRequestDTO eventRequestDTO) throws IOException {
        // Credential creation by providing the access token
        GoogleCredential credential = new GoogleCredential().setAccessToken(this.accessToken);
        // Access calendar services by passing the credentials
        Calendar service = new Calendar.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME).build();

        Event event = mapToEvent(eventRequestDTO);

        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),};
        Event.Reminders reminders = new Event.Reminders().setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
        //add new event to Google Calendar
        Event savedEvent = service.events().insert(CALENDAR_ID, event).execute();
        EventResponseDTO newEventResponseDTO = new EventResponseDTO();
        newEventResponseDTO.setId(savedEvent.getId());
        System.out.println(newEventResponseDTO);
        return newEventResponseDTO;

    }

    /**
     * Map EventRequestDTO type object to Event object
     *
     * @param eventRequestDTO
     * @return
     */
    private Event mapToEvent(EventRequestDTO eventRequestDTO) {

        Event event = new Event().setSummary(eventRequestDTO.getSummary()).setDescription(eventRequestDTO.getDescription());

        String strStartDateTime = eventRequestDTO.getStartDate().concat("T").concat(eventRequestDTO.getStartTime()).concat(":00").concat(timeZone);
        DateTime startDateTime = new DateTime(strStartDateTime);
        EventDateTime start = new EventDateTime().setDateTime(startDateTime);
        event.setStart(start);
        String strEndDateTime = eventRequestDTO.getEndDate().concat("T").concat(eventRequestDTO.getEndTime()).concat(":00").concat(timeZone);
        DateTime endDateTime = new DateTime(strEndDateTime);
        EventDateTime end = new EventDateTime().setDateTime(endDateTime);
        event.setEnd(end);

        return event;

    }
}
