package org.joao.olympicportugal.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EventSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "event_id")
    private List<Event> eventsForTheDay;

    public EventSchedule() {
    }

    public EventSchedule(LocalDate day, List<Event> eventsForTheDay) {
        this.day = day;
        this.eventsForTheDay = eventsForTheDay;
    }

    public EventSchedule(LocalDate day) {
        this.day = day;
        this.eventsForTheDay = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDay() {
        return day;
    }

    public List<Event> getEventsForTheDay() {
        return eventsForTheDay;
    }

    public boolean addEvent(Event event) {
        return this.eventsForTheDay.add(event);
    }
}
