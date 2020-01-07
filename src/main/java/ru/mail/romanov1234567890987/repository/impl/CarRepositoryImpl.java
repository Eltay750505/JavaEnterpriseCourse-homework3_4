package ru.mail.romanov1234567890987.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.romanov1234567890987.repository.CarRepository;
import ru.mail.romanov1234567890987.repository.model.Car;
import ru.mail.romanov1234567890987.repository.model.CarModelsNamesEnum;
import ru.mail.romanov1234567890987.util.RandomUtil;

import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void add(Connection connection) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection
                        .prepareStatement("INSERT INTO car(name,car_model,engine_capacity)VALUES(?,?,?)",
                                Statement.RETURN_GENERATED_KEYS)
        ) {
            int carsCount = 10;
            for (int i = 0; i < carsCount; i++) {
                Car car = getRandomCar();
                preparedStatement.setString(1, car.getName());
                preparedStatement.setString(2, car.getCarModelsNamesEnum());
                preparedStatement.setInt(3, car.getEngineCapacity());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating car failed, no rows affected");
                }
            }
        }
    }

    @Override
    public void deleteCars(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            int affectedRows = statement
                    .executeUpdate("DELETE FROM car WHERE engine_capacity = " +
                            "(SELECT * FROM (SELECT MIN(engine_capacity) FROM car) AS t1);");
            if (affectedRows == 0) {
                throw new SQLException("Creating car failed, no rows affected");
            }

        }
    }

    @Override
    public List<Car> findCars(Connection connection) throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM car")
        ) {
            while (rs.next()) {
                Car car = getCar(rs);
                cars.add(car);
            }
        }
        return cars;
    }

    @Override
    public int countCars(Connection connection, int randomCapacity) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car WHERE" +
                        "engine_capacity = ?")
        ) {
            preparedStatement.setInt(1, randomCapacity);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                logger.info(resultSet.getInt("COUNT(*)"));
            }
        }
        return 0;
    }

    @Override
    public List<Car> changeTitles(Connection connection, int randomCapacity) throws SQLException {

        return null;
    }

    private Car getCar(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String carModel = rs.getString("car_model");
        CarModelsNamesEnum carModelsNamesEnum = getCarModelName(carModel);
        int engineCapacity = rs.getInt("engine_capacity");
        return Car.newBuilder()
                .id(id)
                .name(name)
                .carModelsNamesEnum(carModelsNamesEnum)
                .engineCapacity(engineCapacity)
                .build();
    }

    private CarModelsNamesEnum getCarModelName(String carModel) {
        switch (carModel) {
            case "Mercedes":
                return CarModelsNamesEnum.MERCEDES;
            case "Volkswagen":
                return CarModelsNamesEnum.VOLKSWAGEN;
            case "Audi":
                return CarModelsNamesEnum.AUDI;
            default:
                return null;
        }
    }

    private Car getRandomCar() {
        int modelsCount = 3;
        int maxEngineCapacityValue = 10;

        String randomName = "Name" + RandomUtil.getElement(0, maxEngineCapacityValue);
        int randomCapacity = RandomUtil.getElement(0, maxEngineCapacityValue);

        return Car.newBuilder()
                .carModelsNamesEnum(getRandomCarModel(modelsCount))
                .name(randomName)
                .engineCapacity(randomCapacity)
                .build();
    }

    private CarModelsNamesEnum getRandomCarModel(int modelCountBound) {
        int nextInt = RandomUtil.getElement(1, modelCountBound);
        switch (nextInt) {
            case 1:
                return CarModelsNamesEnum.MERCEDES;
            case 2:
                return CarModelsNamesEnum.VOLKSWAGEN;
            case 3:
                return CarModelsNamesEnum.AUDI;
            default:
                return null;
        }
    }
}
