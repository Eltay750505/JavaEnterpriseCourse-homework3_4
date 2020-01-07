package ru.mail.romanov1234567890987.service;

import ru.mail.romanov1234567890987.repository.model.Car;

import java.sql.Connection;

public interface HomeworkService {
    void runFirstTask();

    void addTenCars();

    void deleteCars();

    void showCars();

    void countCars();

    void changeTitleBySpecificState();
}
