package org.joao.olympicportugal.controller;

import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.dto.EventScheduleDTO;
import org.joao.olympicportugal.dto.ResultDTO;
import org.joao.olympicportugal.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class EventController {

    private EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/events")
    public CompletableFuture<List<EventScheduleDTO>> getAllEvents() {
        return CompletableFuture.supplyAsync(() -> service.findAllEvents());
    }

    @GetMapping("/events/{date}")
    public CompletableFuture<List<EventScheduleDTO>> getAllEventsAfterDate(@PathVariable String date) {
        return CompletableFuture.supplyAsync(() -> service.findAllEventsAfterDate(date));
    }

    @GetMapping("/events/today")
    public CompletableFuture<List<EventScheduleDTO>> getAllEventsAfterToday() {
        return CompletableFuture.supplyAsync(() -> service.findAllEventsAfterToday());
    }

    @PostMapping("/events/refresh")
    public CompletableFuture<List<EventScheduleDTO>> refreshAllEvents() {
        return CompletableFuture.supplyAsync(() -> service.refreshEvents());
    }

    @PatchMapping("/events/{id}/result")
    public CompletableFuture<EventScheduleDTO> updateResult(@PathVariable Long id, @RequestBody ResultDTO result) {
        return CompletableFuture.supplyAsync(() -> service.updateResult(id, result));
    }
}
