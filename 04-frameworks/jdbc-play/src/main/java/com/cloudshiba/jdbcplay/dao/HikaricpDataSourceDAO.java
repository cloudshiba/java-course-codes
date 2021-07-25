package com.cloudshiba.jdbcplay.dao;

import com.cloudshiba.jdbcplay.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class HikaricpDataSourceDAO {
    private final DataSource dataSource;

    public HikaricpDataSourceDAO(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findAll() throws Exception {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM users";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                User user = User.newRecord(id, name);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return users;
    }

    public Optional<User> findById(long queryId) throws Exception {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, queryId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                user = User.newRecord(id, name);
            }
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return user == null ? Optional.empty() : Optional.of(user);
    }

    public int create(String name) throws Exception {
        int createCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "INSERT INTO users (name) VALUES (?);";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            createCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return createCount;
    }

    public int update(long id, String name) throws Exception {
        int updateCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "UPDATE users SET name=? WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setLong(2, id);
            updateCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return updateCount;
    }

    public int delete(long id) throws Exception {
        int deleteCount = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            String sql = "DELETE FROM users WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            deleteCount = statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQLException: {}", e.getMessage());
            log.error("SQLState: {}", e.getSQLState());
            log.error("VendorError: {}", e.getErrorCode());
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return deleteCount;
    }
}
