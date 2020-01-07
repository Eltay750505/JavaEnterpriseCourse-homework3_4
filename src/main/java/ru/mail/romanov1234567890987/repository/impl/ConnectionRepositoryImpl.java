package ru.mail.romanov1234567890987.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.mail.romanov1234567890987.repository.ConnectionRepository;
import ru.mail.romanov1234567890987.util.PropertyUtil;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.mail.romanov1234567890987.repository.constant.ConnectionConstant.*;

public class ConnectionRepositoryImpl implements ConnectionRepository {
 private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private PropertyUtil propertyService = new PropertyUtil();

    @Override
    public Connection getConnection() {
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            return DriverManager.getConnection(
                    propertyService.getProperty(DATABASE_URL),
                    propertyService.getProperty(DATABASE_USERNAME),
                    propertyService.getProperty(DATABASE_PASSWORD)
            );
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot get connection to database");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot find MySQL driver at classpath");
        }
    }

}
