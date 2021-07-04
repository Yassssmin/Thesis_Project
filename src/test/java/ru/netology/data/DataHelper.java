package ru.netology.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.*;

public class DataHelper {
    private DataHelper() {}
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
                .filter(gateCard -> gateCard.getStatus().equalsIgnoreCase(DataBaseHelper.APPROVED_STATUS))
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
}