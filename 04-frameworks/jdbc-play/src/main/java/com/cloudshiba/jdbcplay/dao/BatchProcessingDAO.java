package com.cloudshiba.jdbcplay.dao;

import com.cloudshiba.jdbcplay.config.JdbcProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Slf4j
@Repository
public class BatchProcessingDAO {
    private final JdbcProperty jdbcProperty;

    public BatchProcessingDAO(final JdbcProperty jdbcProperty) {
        this.jdbcProperty = jdbcProperty;
    }

    public int[] statementBatchProcessing(String name1, String name2) throws Exception {
        int[] count = null;
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(jdbcProperty.getDriverClassName()).newInstance();
            connection = DriverManager.getConnection(jdbcProperty.getFullUrl());
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "INSERT INTO users (name) VALUES ('" + name1 + "');";
            log.info("sql1: {}", sql);
            statement.addBatch(sql);
            sql = "INSERT INTO users (name) VALUES ('" + name2 + "');";
            log.info("sql2: {}", sql);
            statement.addBatch(sql);
            count = statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count;
    }

    public int[] prepareStatementBatchProcessing(String name1, String name2) throws Exception {
        int[] count = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(jdbcProperty.getDriverClassName()).newInstance();
            connection = DriverManager.getConnection(jdbcProperty.getFullUrl());
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users (name) VALUES (?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name1);
            statement.addBatch();
            log.info("sql1: {}", statement);
            statement.setString(1, name2);
            statement.addBatch();
            log.info("sql2: {}", statement);
            count = statement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count;
    }
}
