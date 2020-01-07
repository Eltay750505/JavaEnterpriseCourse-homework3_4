package ru.mail.romanov1234567890987.repository.model;

public enum CarModelsNamesEnum {
    MERCEDES("Mercedes"),
    AUDI("Audi"),
    VOLKSWAGEN("Volkswagen");

    private String name;

    public String getName() {
        return name;
    }

    CarModelsNamesEnum(String name) {
        this.name = name;
    }
}
