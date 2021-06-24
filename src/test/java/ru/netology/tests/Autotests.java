package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataHelper;
import ru.netology.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class Autotests {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        DataHelper.clearAllData();
        openSite();
    }

    void openSite() {
        open("http://localhost:8080");
    }

//    Автоматизация покупки через карту (первая карта)

    @Test
    public void ShouldBeTestBuyTravelSuccessOne() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardOne();

        mainPage.fillDataForBuy(validCard);
        mainPage.checkSuccessMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertEquals(paymentEntity.getAmount(), 4500000);
        assertEquals(paymentEntity.getStatus(), DataHelper.APPROVED_STATUS);

        val orderEntity = DataHelper.getOrderEntity();
        assertEquals(orderEntity.getPayment_id(),paymentEntity.getId());

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateOne() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOne();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkError();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);

    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorExpired();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }


    @Test
    public void ShouldBeTestBuyTravelErrorNameOne() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNameOne();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);

    }

//    Автоматизация покупки через карту (вторая карта)

    @Test
    public void ShouldBeTestBuyTravelSuccessTwo() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.fillDataForBuy(validCard);
        mainPage.checkSuccessMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertEquals(paymentEntity.getAmount(), 4500000);
        assertEquals(paymentEntity.getStatus(), DataHelper.DECLINED_STATUS);

        val orderEntity = DataHelper.getOrderEntity();
        assertEquals(orderEntity.getPayment_id(),paymentEntity.getId());

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwo();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkError();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorExpired();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }


    @Test
    public void ShouldBeTestBuyTravelErrorNameTwo() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNameCardTwo();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//      Неверный номер карты
    @Test
    public void ShouldBeTestBuyTravelErrorNumber() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNumber();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelErrorNumber() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNumber();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//    Автоматизация покупки через кредит (первая карта)

    @Test
    public void ShouldBeTestCreditTravelSuccessOne() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardOne();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkSuccessMessage();

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertEquals(creditRequestEntity.getStatus(), DataHelper.APPROVED_STATUS);

        val orderEntity = DataHelper.getOrderEntity();
        assertEquals(orderEntity.getCredit_id(),creditRequestEntity.getId());

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateOne() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOne();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkError();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorExpired();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelInvalidNameOne() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getInvalidNameOne();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//    Автоматизация покупки через кредит (вторая карта)

    @Test
    public void ShouldBeTestCreditTravelSuccessTwo() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkSuccessMessage();

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertEquals(creditRequestEntity.getStatus(), DataHelper.DECLINED_STATUS);

        val orderEntity = DataHelper.getOrderEntity();
        assertEquals(orderEntity.getCredit_id(),creditRequestEntity.getId());

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwo();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkError();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorExpired();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void ShouldBeTestCreditTravelInvalidNameTwo() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getInvalidNameCardTwo();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkErrorMessage();

        val paymentEntity = DataHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataHelper.getOrderEntity();
        assertNull(orderEntity);
    }
}

