package org.joao.olympicportugal.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime portugalTime;

    private String sport;

    private String athlete;

    private String round;

    private String directOponent;

    private String trasmission;

    private Result result;

    public Event() {
    }

    public Event(LocalTime portugalTime, String sport, String athlete, String round, String directOponent, String trasmission,
                 Result result) {
        this.portugalTime = portugalTime;
        this.sport = sport;
        this.athlete = athlete;
        this.round = round;
        this.directOponent = directOponent;
        this.trasmission = trasmission;
        this.result = result;
    }

    public void finishEvent(Result result) {
        this.result = result;
    }
    public Long getId() {
        return id;
    }

    public LocalTime getPortugalTime() {
        return portugalTime;
    }

    public String getSport() {
        return sport;
    }

    public String getAthlete() {
        return athlete;
    }

    public String getRound() {
        return round;
    }

    public String getDirectOponent() {
        return directOponent;
    }

    public String getTrasmission() {
        return trasmission;
    }

    public Result getResult() {
        return result;
    }
}
