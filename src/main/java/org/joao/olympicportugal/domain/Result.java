package org.joao.olympicportugal.domain;

public enum Result {
    GOLD("Ouro"),
    SILVER("Prata"),
    BRONZE("Bronze"),
    NO_MEDAL("Sem medalha"),
    ELIMINATED("Eliminado"),
    STANDBY("-");

    private String description;

    Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
