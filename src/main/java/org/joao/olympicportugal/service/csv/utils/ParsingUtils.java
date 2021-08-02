package org.joao.olympicportugal.service.csv.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ParsingUtils {

    private static final String NOT_DEFINED = "a definir";
    private static Integer TIME_LENGTH = 8;
    private static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("[H:mm:ss][HH:mm:ss]");

    public static Boolean hasNotDefinedField(String[] lines) {
        return Arrays.stream(lines).anyMatch(info -> info.isBlank() || info.equals(NOT_DEFINED));
    }

    public static Boolean areCsvFieldsEmpty(List<String[]> lines) {
        return lines.isEmpty();
    }

    public static LocalTime parseTimeFromString(String s) {
        if(s.length() > TIME_LENGTH) {
            String date = s.substring(0, TIME_LENGTH);
            return LocalTime.parse(date, TIME_FORMATTER);
        }
        return LocalTime.parse(s, TIME_FORMATTER);
    }

    public static SportsRoundTuple parseSportFromString(String inputSport, String inputRound) {
        return new SportsRoundTuple(inputSport, inputRound);
    }
}
