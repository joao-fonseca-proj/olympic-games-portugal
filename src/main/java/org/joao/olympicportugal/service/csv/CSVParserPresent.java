package org.joao.olympicportugal.service.csv;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVReaderBuilder;
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

public class CSVParserPresent implements org.joao.olympicportugal.service.csv.CSVParser {

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static CSVParser PRESENT_PARSER = new CSVParserBuilder()
            .withSeparator(';')
            .build();

    private static Integer FILE_FORMAT_LENGTH = 4;

    private static Boolean IGNORE_HEADER = true;

    @Override
    public EventSchedule createEventsFromFile(File f) throws InvalidParsingException {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(f)).withCSVParser(PRESENT_PARSER).build();
            List<String[]> lines = reader.readAll();
            if(areCsvFieldsEmpty(lines)) {
                throw new IllegalArgumentException("Not found");
            }
            if(IGNORE_HEADER) {
                lines.remove(0);
            }
            LocalDate date = LocalDate.parse(f.getName().substring(0, f.getName().length() - FILE_FORMAT_LENGTH), DATE_FORMATTER); // headers sometime come corrupted lol
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

    private Event createEventFromLine(String[] line) {
        LocalTime portugalTime = parseTimeFromString(line[1]);
        SportsRoundTuple sports = parseSportFromString(line[2], line[4]);
        return new Event(portugalTime, sports.getSport(), line[3], sports.getRound(), sports.getDirectOponent(),
                "RTP2 / Eurosport2", Result.STANDBY);
    }
}
