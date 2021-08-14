package com.cloudshiba.shardingsphereproxy.dao;

import com.cloudshiba.shardingsphereproxy.entity.EcOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository @Slf4j
public class EcOrderDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EcOrderDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<EcOrder> findAll() {
        StringBuffer sql = new StringBuffer("SELECT * FROM ec_orders");

        return jdbcTemplate.query(sql.toString(), this::mapRowToEcOrder);
    }

    public Optional<EcOrder> findOne(Long orderId) {
        StringBuffer sql = new StringBuffer("SELECT * FROM ec_orders where order_id = :orderId");
        MapSqlParameterSource params =
                new MapSqlParameterSource()
                        .addValue("orderId", orderId);

        EcOrder order = namedParameterJdbcTemplate.queryForObject(sql.toString(), params, this::mapRowToEcOrder);
        return Optional.ofNullable(order);
    }

    public EcOrder create(EcOrder order) {
        StringBuffer sql = new StringBuffer(
                "INSERT INTO ec_orders(user_id, total_price, create_time, update_time) VALUES(?, ?, ?, ?)");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long currentMilliSeconds = System.currentTimeMillis();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, order.getUserId());
            statement.setBigDecimal(2, order.getTotalPrice());
            statement.setLong(3, currentMilliSeconds);
            statement.setLong(4, currentMilliSeconds);
            return statement;
        }, keyHolder);

        log.info("keyHolder: {}", keyHolder.getKeyList());
        long orderId = keyHolder.getKey().longValue();
        order.setOrderId(orderId);
        order.setCreateTime(currentMilliSeconds);
        order.setUpdateTime(currentMilliSeconds);

        return order;
    }

    public EcOrder update(EcOrder order) {
        StringBuffer sql = new StringBuffer("UPDATE ec_orders SET total_price = ?, update_time = ? where order_id = ?");
        jdbcTemplate.update(sql.toString(), order.getTotalPrice(), System.currentTimeMillis(), order.getOrderId());

        return order;
    }

    public boolean delete(Long orderId) {
        StringBuffer sql = new StringBuffer("DELETE FROM ec_orders WHERE order_id = ?");

        return jdbcTemplate.update(sql.toString(), orderId) > 0;
    }

    private EcOrder mapRowToEcOrder(ResultSet resultSet, int rowNum)  throws SQLException {
        return EcOrder.builder()
                .orderId(resultSet.getLong("order_id"))
                .userId(resultSet.getLong("user_id"))
                .totalPrice(resultSet.getBigDecimal("total_price"))
                .createTime(resultSet.getLong("create_time"))
                .updateTime(resultSet.getLong("update_time"))
                .build();
    }
}
