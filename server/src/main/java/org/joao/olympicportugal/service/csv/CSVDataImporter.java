package org.joao.olympicportugal.service.csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.joao.olympicportugal.domain.Event;
import org.joao.olympicportugal.domain.EventSchedule;
import org.joao.olympicportugal.domain.Round;
import org.joao.olympicportugal.service.DataImporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVDataImporter implements DataImporter {

    @Value("${data.location.folder}")
    private String dataPath;

    private static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("[H:mm:ss][HH:mm:ss]");

    private static String FILE_SPLIT = "\\.";

    private static String LEFT_PARENTHESIS_SPLIT = "\\(";

    private static String DEFAULT_STRING_VALUE = "-";

    private static Boolean IGNORE_HEADER = true;

    private static Integer TIME_LENGTH = 8;

    private static String ATLETISMO = "Atletismo";

    @Override
    public List<EventSchedule> retrieveEvents() {
        File directory = null;
        try {
            directory = new ClassPathResource(dataPath).getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File[] csvFiles = directory.listFiles();
        List<EventSchedule> eventList = new ArrayList<>();
        for (File f: csvFiles) {
            if(isCSVFile(f)) {
                try {
                    EventSchedule scheduleFromFile = CSVParserContext.getParser(f.getName()).createEventsFromFile(f); // gets parser depending on file name
                    eventList.add(scheduleFromFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return eventList;
    }

    private static Boolean isCSVFile(File f) {
        String[] split = f.getName().split(FILE_SPLIT);
        if(split.length == 2) {
            return split[1].equals("csv");
        }
        return false;
    }

}
