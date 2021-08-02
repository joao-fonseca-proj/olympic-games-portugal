package org.joao.olympicportugal.service.csv;

import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.service.csv.exception.InvalidParsingException;

import java.io.File;

public interface CSVParser {

    EventSchedule createEventsFromFile(File f) throws InvalidParsingException;

}
