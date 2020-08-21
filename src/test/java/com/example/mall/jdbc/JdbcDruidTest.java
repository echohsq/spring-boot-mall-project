package com.example.mall.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author hsqzs
 * date 2020/8/20 16:38
 */
@SpringBootTest
class JdbcDruidTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        PreparedStatement prepareStatement = connection
                .prepareStatement("select * from ums_member");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("username");
            System.out.println(name);
        }
        connection.close();
    }

}