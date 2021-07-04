package ru.netology.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DataBaseHelper {
    public static final String APPROVED_STATUS = "APPROVED";
    public static final String DECLINED_STATUS = "DECLINED";

    private static Connection getConnection() {
        String datasourceUrl = System.getProperty("datasource.url");
        String datasourceUser = System.getProperty("datasource.user");
        String datasourcePassword = System.getProperty("datasource.password");

        try {
            return DriverManager.getConnection(datasourceUrl, datasourceUser, datasourcePassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
    @Data
    @NoArgsConstructor
    public static class PaymentEntity {
        private String id;
        private int amount;
        private Timestamp created;
        private String status;
        private String transaction_id;
    }

    @Data
    @NoArgsConstructor
    public static class CreditRequestEntity {
        private String id;
        private String bank_id;
        private Timestamp created;
        private String status;
    }

    @Data
    @NoArgsConstructor
    public static class OrderEntity {
        private String id;
        private Timestamp created;
        private String credit_id;
        private String payment_id;
    }

    public static void clearAllData() {
        String sql = "DELETE FROM payment_entity; DELETE FROM order_entity; DELETE FROM credit_request_entity;";

        QueryRunner runner = new QueryRunner();

        try {
            runner.execute(getConnection(), sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static OrderEntity getOrderEntity() {
        String sql = "SELECT * FROM order_entity LIMIT 1";

        QueryRunner runner = new QueryRunner();

        OrderEntity orderEntity = null;
        try {
            orderEntity = runner.query(getConnection(), sql, new BeanHandler<>(OrderEntity.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return orderEntity;
    }

    public static PaymentEntity getPaymentEntity() {
        String sql = "SELECT * FROM payment_entity LIMIT 1";

        QueryRunner runner = new QueryRunner();

        PaymentEntity paymentEntity = null;
        try {
            paymentEntity = runner.query(getConnection(), sql, new BeanHandler<>(PaymentEntity.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return paymentEntity;
    }

    public static CreditRequestEntity getCreditRequestEntity() {
        String sql = "SELECT * FROM credit_request_entity LIMIT 1";

        QueryRunner runner = new QueryRunner();

        CreditRequestEntity creditRequestEntity = null;
        try {
            creditRequestEntity = runner.query(getConnection(), sql, new BeanHandler<>(CreditRequestEntity.class));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return creditRequestEntity;
    }
}
