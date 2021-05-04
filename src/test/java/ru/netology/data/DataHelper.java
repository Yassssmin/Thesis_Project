package ru.netology.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
                .filter(gateCard -> gateCard.getStatus().equalsIgnoreCase("APPROVED"))
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


        Faker faker = new Faker();

        return new CardInfo(
            validCardNumber,
            faker.name().firstName() + faker.name().lastName(),
            "0" + faker.number().numberBetween(1, 9),
            "2" + faker.number().numberBetween(2, 6),
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


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                "0" + faker.number().numberBetween(1,9 ),
                "1" + faker.number().numberBetween(2, 9),
                faker.numerify("###")
        );
    }


//    Методы для второй карты
    public static CardInfo getValidCardTwo() {
    String validCardNumber = getValidCardNumberTwo();


    Faker faker = new Faker();

    return new CardInfo(
            validCardNumber,
            faker.name().firstName() + faker.name().lastName(),
            "0" + faker.number().numberBetween(1, 9),
            "2" + faker.number().numberBetween(2, 6),
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


        Faker faker = new Faker();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                "0" + faker.number().numberBetween(1, 9),
                "1" + faker.number().numberBetween(2, 9),
                faker.numerify("###")
        );
    }

    public static CardInfo getInvalidNumber() {
        Faker faker = new Faker();

        String validCardNumber = faker.business().creditCardNumber();

        return new CardInfo(
                validCardNumber,
                faker.name().firstName() + faker.name().lastName(),
                "0" + faker.number().numberBetween(1, 9),
                "2" + faker.number().numberBetween(2, 6),
                faker.numerify("###")
        );
    }

}
