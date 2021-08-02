package org.joao.olympicportugal.service.csv;

public class CSVParserContext {

    private static String FILE_NAME_LEGACY = "data";

    public static CSVParser getParser(String fileName) {
        if(fileName.contains(FILE_NAME_LEGACY)) {
            return new CSVParserLegacy();
        }
        return new CSVParserPresent();
    }
}
