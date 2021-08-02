package org.joao.olympicportugal.service;

import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.domain.Result;
import org.joao.olympicportugal.dto.EventScheduleDTO;
import org.joao.olympicportugal.dto.ResultDTO;
import org.joao.olympicportugal.dto.mapper.EventMapper;
import org.joao.olympicportugal.repository.EventScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventScheduleRepository repo;

    private DataImporter dataImporter;

    private static DateTimeFormatter DATE_FORMATTER_SERVICE_PARAM = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Autowired
    public EventService(EventScheduleRepository repo, DataImporter dataImporter) {
        this.repo = repo;
        this.dataImporter = dataImporter;
    }

    public List<EventScheduleDTO> findAllEvents() {
        return EventMapper.domainListToDto(this.repo.findAll());
    }

    public List<EventScheduleDTO> findAllEventsAfterDate(String date) {
        LocalDate dateValue = LocalDate.parse(date, DATE_FORMATTER_SERVICE_PARAM);
        return EventMapper.domainListToDto(this.repo.findAllByDayGreaterThanEqualOrderByDay(dateValue));
    }

    public List<EventScheduleDTO> findAllEventsAfterToday() {
        return EventMapper.domainListToDto(this.repo.findAllByDayGreaterThanEqualOrderByDay(LocalDate.now()));
    }

    public List<EventScheduleDTO> refreshEvents() {
        List<EventSchedule> schedule = dataImporter.retrieveEvents();
        if(schedule.isEmpty()) return Collections.emptyList();
        this.repo.deleteAll(); // clean only if retrieving is successful
        return EventMapper.domainListToDto(this.repo.saveAll(schedule));
    }

    public EventScheduleDTO updateResult(Long eventId, ResultDTO result) {
        LocalDate eventScheduleDate = LocalDate.parse(result.getDate(), DATE_FORMATTER_SERVICE_PARAM);
        EventSchedule schedule = this.repo.findEventScheduleByDayIs(eventScheduleDate).orElseThrow(() ->
                new IllegalArgumentException("Schedule not found for date " + result.date));
        Event eventFound = schedule.getEventsForTheDay().stream().filter(p -> p.getId().equals(eventId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Event not found for id " + eventId));
        eventFound.finishEvent(Result.valueOf(result.getResult()));
        return EventMapper.domainToDto(this.repo.save(schedule));
    }
}
