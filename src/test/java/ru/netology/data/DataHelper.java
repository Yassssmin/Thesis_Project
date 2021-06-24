package ru.netology.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.*;

public class DataHelper {
    private DataHelper() {}

    public static final String APPROVED_STATUS = "APPROVED";
    public static final String DECLINED_STATUS = "DECLINED";

    private static Properties getProperties() {
        Properties properties = new Properties();

        try (InputStream is = Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/application.properties"))) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return properties;
    }

    private static Connection getConnection() {
        Properties properties = getProperties();

        String datasourceUrl = properties.getProperty("spring.datasource.url");
        String datasourceUsername = properties.getProperty("spring.datasource.username");
        String datasourcePassword = properties.getProperty("spring.datasource.password");

        try {
            return DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    private static final String cardDataFilePath = System.getProperty("user.dir") + "/gate-simulator/data.json";

    @Data
    @NoArgsConstructor
    private static class GateCard {
        private String number;
        private String status;
    }

    private static List<GateCard> getCardNumbers() {
        List<GateCard> gateCards = null;

        try {
            ObjectMapper mapper = new ObjectMapper();

            gateCards = Arrays.asList(mapper.readValue(Paths.get(cardDataFilePath).toFile(), GateCard[].class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return gateCards;
    }

    @SneakyThrows
    private static String getValidCardNumberOne() {
        List<GateCard> gateCards = getCardNumbers();

        Optional<GateCard> validGateCard = gateCards
                .stream().parallel()
                .filter(gateCard -> gateCard.getStatus().equalsIgnoreCase(APPROVED_STATUS))
                .findFirst();

        if (validGateCard.isPresent()) {
            return validGateCard.get().getNumber();
        }

        throw new Exception("VALID card does not exist");
    }

    @SneakyThrows
    private static String getValidCardNumberTwo() {
        List<GateCard> gateCards = getCardNumbers();

        Optional<GateCard> validGateCard = gateCards
                .stream().parallel()
                .filter(gateCard -> gateCard.getStatus().equalsIgnoreCase("DECLINED"))
                .findFirst();

        if (validGateCard.isPresent()) {
            return validGateCard.get().getNumber();
        }

        throw new Exception("VALID card does not exist");
    }

    @Data
    @AllArgsConstructor
    public static class CardInfo {
        private String number;
        private String cardHolder;
        private String month;
        private String year;
        private String cvcCode;
    }

//    Методы для первой карты

    public static CardInfo getValidCardOne() {
        String validCardNumber = getValidCardNumberOne();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();

        Faker faker = new Faker();

        return new CardInfo(
            validCardNumber,
            faker.name().firstName() + faker.name().lastName(),
            StringUtils.leftPad(String.valueOf(dateNow.getMonthValue()), 2, '0'),
            String.valueOf(dateNow.getYear()).substring(2),
            faker.numerify("###")
        );
    }

    public static CardInfo getInvalidDateOne() {
        String validCardNumber = getValidCardNumberOne();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                "4" + faker.number().numberBetween(1, 9),
                "3" + faker.number().numberBetween(2, 6),
                faker.numerify("###")
        );
    }

    public static CardInfo getInvalidDateOneExpired() {
        String validCardNumber = getValidCardNumberOne();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                StringUtils.leftPad(String.valueOf(dateNow.minusMonths(1).getMonthValue()), 2, '0'),
                String.valueOf(dateNow.getYear()).substring(2),
                faker.numerify("###")
        );
    }

    public static CardInfo getInvalidNameOne() {
        String validCardNumber = getValidCardNumberOne();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.numerify("###############"),
                StringUtils.leftPad(String.valueOf(dateNow.getMonthValue()), 2, '0'),
                String.valueOf(dateNow.getYear()).substring(2),
                faker.numerify("###")
        );
    }


//    Методы для второй карты
    public static CardInfo getValidCardTwo() {
    String validCardNumber = getValidCardNumberTwo();
    ZonedDateTime dateNow = java.time.ZonedDateTime.now();


    Faker faker = new Faker();

    return new CardInfo(
            validCardNumber,
            faker.name().firstName() + faker.name().lastName(),
            StringUtils.leftPad(String.valueOf(dateNow.getMonthValue()), 2, '0'),
            String.valueOf(dateNow.getYear()).substring(2),
            faker.numerify("###")
    );
    }

    public static CardInfo getInvalidDateTwo() {
        String validCardNumber = getValidCardNumberTwo();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                "4" + faker.number().numberBetween(1, 9),
                "3" + faker.number().numberBetween(2, 6),
                faker.numerify("###")
        );
    }

    public static CardInfo getInvalidDateTwoExpired() {
        String validCardNumber = getValidCardNumberTwo();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                StringUtils.leftPad(String.valueOf(dateNow.minusMonths(1).getMonthValue()), 2, '0'),
                String.valueOf(dateNow.getYear()).substring(2),
                faker.numerify("###")
        );
    }

    public static CardInfo getInvalidNameCardTwo() {
        String validCardNumber = getValidCardNumberTwo();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.numerify("################"),
                StringUtils.leftPad(String.valueOf(dateNow.getMonthValue()), 2, '0'),
                String.valueOf(dateNow.getYear()).substring(2),
                faker.numerify("###")
        );
    }


    public static CardInfo getInvalidNumber() {
        Faker faker = new Faker();
        ZonedDateTime dateNow = java.time.ZonedDateTime.now();

        String validCardNumber = faker.business().creditCardNumber();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                StringUtils.leftPad(String.valueOf(dateNow.getMonthValue()), 2, '0'),
                String.valueOf(dateNow.getYear()).substring(2),
                faker.numerify("###")
        );
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
