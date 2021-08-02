package org.joao.olympicportugal.service.csv.utils;

import org.joao.olympicportugal.domain.Round;

public class SportsRoundTuple {

    private String sport;
    private String round;
    private String directOponent;

    private static String ATLETISMO = "Atletismo";
    private static String CANOAGEM = "Canoagem";
    private static String EQUESTRE = "Equestre";

    private static String LEFT_PARENTHESIS_SPLIT = "\\(";
    private static String DEFAULT_STRING_VALUE = "-";

    public SportsRoundTuple(String inputSport, String round) {
        if(inputSport.equals(ATLETISMO) || inputSport.equals(CANOAGEM) || inputSport.equals(EQUESTRE)) {
            String[] roundParenthesis = round.split(LEFT_PARENTHESIS_SPLIT);
            if(roundParenthesis.length == 2) {
                String currentRound = roundParenthesis[1].substring(0, roundParenthesis[1].length() -1);
                this.sport = inputSport + " - " + roundParenthesis[0].trim();
                this.directOponent = DEFAULT_STRING_VALUE;
                this.round = Round.parseAbbreviation(currentRound).getDescription();
            }
        } else {
            String[] parenthesis = inputSport.split(LEFT_PARENTHESIS_SPLIT);
            if(parenthesis.length == 2) {
                this.sport = parenthesis[0].trim();
                this.round = parenthesis[1].substring(0, parenthesis[1].length() -1);
                this.directOponent = round;
            } else {
                this.sport = inputSport;
                this.round = round;
                this.directOponent = DEFAULT_STRING_VALUE;
            }
        }
    }

    public String getSport() {
        return sport;
    }

    public String getRound() {
        return round;
    }

    public String getDirectOponent() {
        return directOponent;
    }
}
