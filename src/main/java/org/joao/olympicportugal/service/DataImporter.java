package org.joao.olympicportugal.service;

import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;

import java.util.List;

public interface DataImporter {

    List<EventSchedule> retrieveEvents();
}
