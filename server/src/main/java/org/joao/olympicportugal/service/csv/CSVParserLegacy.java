package org.joao.olympicportugal.service.csv;

import com.opencsv.CSVReader;
import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.domain.Result;
import org.joao.olympicportugal.service.csv.exception.InvalidParsingException;
import org.joao.olympicportugal.service.csv.utils.SportsRoundTuple;

import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.joao.olympicportugal.service.csv.utils.ParsingUtils.*;

public class CSVParserLegacy implements CSVParser {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static Boolean IGNORE_HEADER = true;

    @Override
    public EventSchedule createEventsFromFile(File f) throws InvalidParsingException {
        try {
            CSVReader reader = new CSVReader(new FileReader(f));
            List<String[]> lines = reader.readAll();
            if(areCsvFieldsEmpty(lines)) {
                throw new IllegalArgumentException("Not found");
            }
            if(IGNORE_HEADER) {
                lines.remove(0);
            }
            LocalDate date = LocalDate.parse(lines.get(1)[0], DATE_FORMATTER); // headers sometime come corrupted lol
            EventSchedule daySchedule = new EventSchedule(date);
            for (String[] line: lines) {
                if(!hasNotDefinedField(line)) {
                    daySchedule.addEvent(createEventFromLine(line));
                }
            }
            return daySchedule;
        } catch (Exception e) {
            throw new InvalidParsingException("Invalid parsing " + e.getMessage());
        }
    }

    private static Event createEventFromLine(String[] line) {
        LocalTime portugalTime = parseTimeFromString(line[2]);
        SportsRoundTuple sports = parseSportFromString(line[3], line[5]);
        return new Event(portugalTime, sports.getSport(), line[4], sports.getRound(), sports.getDirectOponent(),
                "RTP2 / Eurosport2", Result.STANDBY);
    }

}
