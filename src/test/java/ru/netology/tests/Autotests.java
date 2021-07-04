package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataBaseHelper;
import ru.netology.data.DataHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.Form;

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
        DataBaseHelper.clearAllData();
        openSite();
    }

    void openSite() {
        open("http://localhost:8080");
    }

//    Автоматизация покупки через карту (первая карта)

    @Test
    public void shouldBeTestBuyTravelSuccessOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val validCard = DataHelper.getValidCardOne();

        mainPage.clickForBuy();
        pullForm.fillData(validCard);
        pullForm.checkSuccessMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertEquals(paymentEntity.getAmount(), 4500000);
        assertEquals(paymentEntity.getStatus(), DataBaseHelper.APPROVED_STATUS);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertEquals(orderEntity.getPayment_id(),paymentEntity.getId());

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);
    }

    @Test
    public void shouldBeTestBuyTravelErrorDateOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateOne();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkError();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);

    }

    @Test
    public void shouldBeTestBuyTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorExpired();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }


    @Test
    public void shouldBeTestBuyTravelErrorNameOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNameOne();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);

    }

//    Автоматизация покупки через карту (вторая карта)

    @Test
    public void shouldBeTestBuyTravelSuccessTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.clickForBuy();
        pullForm.fillData(validCard);
        pullForm.checkSuccessMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertEquals(paymentEntity.getAmount(), 4500000);
        assertEquals(paymentEntity.getStatus(), DataBaseHelper.DECLINED_STATUS);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertEquals(orderEntity.getPayment_id(),paymentEntity.getId());

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);
    }

    @Test
    public void shouldBeTestBuyTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateTwo();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkError();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestBuyTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorExpired();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }


    @Test
    public void shouldBeTestBuyTravelErrorNameTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNameCardTwo();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//      Неверный номер карты
    @Test
    public void shouldBeTestBuyTravelErrorNumber() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNumber();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestCreditTravelErrorNumber() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNumber();

        mainPage.clickForBuy();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//    Автоматизация покупки через кредит (первая карта)

    @Test
    public void shouldBeTestCreditTravelSuccessOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val validCard = DataHelper.getValidCardOne();

        mainPage.clickForCredit();
        pullForm.fillData(validCard);
        pullForm.checkSuccessMessage();

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertEquals(creditRequestEntity.getStatus(), DataBaseHelper.APPROVED_STATUS);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertEquals(orderEntity.getCredit_id(),creditRequestEntity.getId());

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

    }

    @Test
    public void shouldBeTestCreditTravelErrorDateOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateOne();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkError();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestCreditTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorExpired();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestCreditTravelInvalidNameOne() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNameOne();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

//    Автоматизация покупки через кредит (вторая карта)

    @Test
    public void shouldBeTestCreditTravelSuccessTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.clickForCredit();
        pullForm.fillData(validCard);
        pullForm.checkSuccessMessage();

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertEquals(creditRequestEntity.getStatus(), DataBaseHelper.DECLINED_STATUS);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertEquals(orderEntity.getCredit_id(),creditRequestEntity.getId());

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);
    }

    @Test
    public void shouldBeTestCreditTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateTwo();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkError();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestCreditTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorExpired();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }

    @Test
    public void shouldBeTestCreditTravelInvalidNameTwo() {
        MainPage mainPage = new MainPage();
        Form pullForm = new Form();

        val invalidCard = DataHelper.getInvalidNameCardTwo();

        mainPage.clickForCredit();
        pullForm.fillData(invalidCard);
        pullForm.checkErrorMessage();

        val paymentEntity = DataBaseHelper.getPaymentEntity();
        assertNull(paymentEntity);

        val creditRequestEntity = DataBaseHelper.getCreditRequestEntity();
        assertNull(creditRequestEntity);

        val orderEntity = DataBaseHelper.getOrderEntity();
        assertNull(orderEntity);
    }
}

