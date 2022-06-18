package com.springcloud.learn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class PaymentTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testDataSource(){

        System.out.println("dataSource==>" + dataSource.getClass());

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("connection==>" + connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
