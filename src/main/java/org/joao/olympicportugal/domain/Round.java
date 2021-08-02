package org.joao.olympicportugal.domain;

public enum Round {
    ELIMINATORIA("Eliminatória", "E"),
    QUALIFICACAO("Qualificação", "Q"),
    OITAVOS_FINAIS("Oitavos-finais", "OF"),
    QUARTOS_FINAIS("Quartos-finais", "QF"),
    MEIAS_FINAIS("Meias-finais", "MF"),
    FINAL("Final", "F");

    private String description;
    private String abbreviation;

    Round(String description, String abbreviation) {
        this.description = description;
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Round parseAbbreviation(String abbreviation) {
        switch (abbreviation) {
            case "E":
                return ELIMINATORIA;
            case "Q":
                return QUALIFICACAO;
            case "OF":
                return OITAVOS_FINAIS;
            case "QF":
                return QUARTOS_FINAIS;
            case "MF":
                return MEIAS_FINAIS;
            case "F":
                return FINAL;
            default:
                return QUALIFICACAO;
        }
    }
}
