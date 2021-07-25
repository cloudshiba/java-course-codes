package com.cloudshiba.jdbcplay.dao;

import com.cloudshiba.jdbcplay.config.JdbcProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Repository
public class TransactionDAO {
    private final JdbcProperty jdbcProperty;

    public TransactionDAO(final JdbcProperty jdbcProperty) {
        this.jdbcProperty = jdbcProperty;
    }

    /**
     * 在一次 Transaction 中建立兩筆 User 資料
     * 最終順利完成這次 Transaction
     */
    public int transactionSuccessfully(String name1, String name2) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(jdbcProperty.getDriverClassName()).newInstance();
            connection = DriverManager.getConnection(jdbcProperty.getFullUrl());
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users (name) VALUES (?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name1);
            count = statement.executeUpdate();

            statement.setString(1, name2);
            count += statement.executeUpdate();

            connection.commit();

            log.info("transactionSuccessfully: count: {} - name1: {} - name2: {}", count, name1, name2);
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

    /**
     * 在一次 Transaction 中建立兩筆 User 資料
     * 建立第一筆 User 後，在建立第二筆時拋出異常中斷 Transaction 並執行 rollback 操作
     */
    public int transactionUnSuccessfully(String name1, String name2) throws Exception {
        int count = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName(jdbcProperty.getDriverClassName()).newInstance();
            connection = DriverManager.getConnection(jdbcProperty.getFullUrl());
            connection.setAutoCommit(false);
            String sql = "INSERT INTO users (name) VALUES (?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name1);
            count = statement.executeUpdate();

            statement.setString(1, name2);
            count += statement.executeUpdate();
            throw new SQLException("Exception in create user for " + name2);
//            connection.close();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
            if (connection != null) connection.rollback();
            count = 0;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count;
    }
}
