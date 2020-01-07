package ru.mail.romanov1234567890987.repository;

import ru.mail.romanov1234567890987.repository.model.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CarRepository {
    void add(Connection connection) throws SQLException;

    void deleteCars(Connection connection) throws SQLException;

    List<Car> findCars(Connection connection) throws SQLException;

    int countCars(Connection connection, int randomCapacity) throws SQLException;

    List<Car> changeTitles(Connection connection, int randomCapacity) throws SQLException;
}
