package org.skywalking.springcloud.test.projectb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import lombok.extern.log4j.Log4j2;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.skywalking.springcloud.test.projectb.service.ServiceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class DatabaseOperateDao {


    @Autowired
    private DataSource dataSource;

    @Trace
    public void saveUser(String name) {
        ActiveSpan.tag("user.name", name);
        log.info("===============test================");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO user(name) VALUES(?)");
            preparedStatement.setString(1, name == null ? "" : name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    @Trace(operationName = "selectUser")
    public void selectUser(String name) {
        ActiveSpan.tag("user.name", name);
        log.info("===============test================");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE name =?");
            preparedStatement.setString(1, name == null ? "" : name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
    }

}
