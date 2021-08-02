package org.joao.olympicportugal.dto.mapper;

import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.dto.EventDTO;
import org.joao.olympicportugal.dto.EventScheduleDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class EventMapper {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("pt", "PT"));

    public static List<EventScheduleDTO> domainListToDto(List<EventSchedule> schedules) {
        return schedules.stream().map(EventMapper::domainToDto).collect(Collectors.toList());
    }
    public static EventScheduleDTO domainToDto(EventSchedule schedule) {
        return new EventScheduleDTO(schedule.getId(), DATE_FORMATTER.format(schedule.getDay()),
                schedule.getEventsForTheDay().stream().map(EventMapper::domainEventToDto).collect(Collectors.toList()));
    }

    private static EventDTO domainEventToDto(Event event) {
        return new EventDTO(event.getId(), event.getPortugalTime().toString(), event.getSport(), event.getAthlete(), event.getRound(), event.getDirectOponent(),
                event.getTrasmission(), event.getResult().name());
    }
}
