package ru.mail.romanov1234567890987.service.impl;

import ru.mail.romanov1234567890987.repository.CarRepository;
import ru.mail.romanov1234567890987.repository.ConnectionRepository;
import ru.mail.romanov1234567890987.repository.impl.CarRepositoryImpl;
import ru.mail.romanov1234567890987.repository.impl.ConnectionRepositoryImpl;
import ru.mail.romanov1234567890987.repository.model.Car;
import ru.mail.romanov1234567890987.service.HomeworkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.romanov1234567890987.util.RandomUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Thread.sleep;


public class HomeworkServiceImpl implements HomeworkService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository = new ConnectionRepositoryImpl();
    private CarRepository carRepository = new CarRepositoryImpl();

    @Override
    public void runFirstTask() {
        int timeDelay = 3000;
        String inputFilePathName = "opt/input.txt";
        String commandList = readFile(inputFilePathName);
        String[] commandArray = commandList.split(System.lineSeparator());
        for (String element : commandArray) {
            logger.debug(element);
            try {
                sleep(timeDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addTenCars() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.add(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteCars() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                carRepository.deleteCars(connection);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void showCars() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Car> cars = carRepository.findCars(connection);
                for (Car element : cars) {
                    logger.info(element);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void countCars() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int maxCapacity = 10;
                int randomCapacity = RandomUtil.getElement(0, maxCapacity);
                int countCars = carRepository.countCars(connection, randomCapacity);
                logger.info("Amount of cars with capacity " + randomCapacity + " : " + countCars);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void changeTitleBySpecificState() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int maxCapacity = 10;
                int randomCapacity = RandomUtil.getElement(0, maxCapacity);
                List<Car> cars = carRepository.changeTitles(connection, randomCapacity);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }


    public String readFile(String pathName) {
        String line = new String();
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName))) {
            line = bufferedReader.readLine();
            while (line != null) {
                stringBuffer.append(line);
                stringBuffer.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            line = stringBuffer.toString();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return line;
    }
}
