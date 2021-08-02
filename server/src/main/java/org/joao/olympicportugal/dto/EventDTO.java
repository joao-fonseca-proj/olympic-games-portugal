package org.joao.olympicportugal.dto;

public class EventDTO {

    public Long id;

    public String portugalTime;

    public String sport;

    public String athlete;

    public String round;

    public String directOponent;

    public String trasmission;

    public String result;

    public EventDTO(Long id, String portugalTime, String sport, String athlete, String round, String directOponent, String trasmission,
                    String result) {
        this.id = id;
        this.portugalTime = portugalTime;
        this.sport = sport;
        this.athlete = athlete;
        this.round = round;
        this.directOponent = directOponent;
        this.trasmission = trasmission;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortugalTime() {
        return portugalTime;
    }

    public void setPortugalTime(String portugalTime) {
        this.portugalTime = portugalTime;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getAthlete() {
        return athlete;
    }

    public void setAthlete(String athlete) {
        this.athlete = athlete;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getDirectOponent() {
        return directOponent;
    }

    public void setDirectOponent(String directOponent) {
        this.directOponent = directOponent;
    }

    public String getTrasmission() {
        return trasmission;
    }

    public void setTrasmission(String trasmission) {
        this.trasmission = trasmission;
    }
}
