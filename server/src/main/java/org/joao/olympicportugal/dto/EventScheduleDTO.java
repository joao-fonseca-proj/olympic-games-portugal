package org.joao.olympicportugal.dto;

import java.util.List;

public class EventScheduleDTO {

    private Long id;

    private String day;

    private List<EventDTO> eventsForTheDay;

    public EventScheduleDTO(Long id, String day, List<EventDTO> eventsForTheDay) {
        this.id = id;
        this.day = day;
        this.eventsForTheDay = eventsForTheDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<EventDTO> getEventsForTheDay() {
        return eventsForTheDay;
    }

    public void setEventsForTheDay(List<EventDTO> eventsForTheDay) {
        this.eventsForTheDay = eventsForTheDay;
    }
}
