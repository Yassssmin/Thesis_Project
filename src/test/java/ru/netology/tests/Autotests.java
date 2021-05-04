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

public class Autotests {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure",new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
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
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateOne() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOne();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkError();
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorExpired();
    }

//    Автоматизация покупки через карту (вторая карта)

    @Test
    public void ShouldBeTestBuyTravelSuccessTwo() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.fillDataForBuy(validCard);
        mainPage.checkSuccessMessage();
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwo();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkError();
    }

    @Test
    public void ShouldBeTestBuyTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorExpired();
    }

    @Test
    public void ShouldBeTestBuyTravelErrorNumber() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNumber();

        mainPage.fillDataForBuy(InvalidCard);
        mainPage.checkErrorMessage();
    }

    @Test
    public void ShouldBeTestCreditTravel() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardOne();

        mainPage.fillDataForCredit(validCard);
    }

//    Автоматизация покупки через кредит (первая карта)

    @Test
    public void ShouldBeTestCreditTravelSuccessOne() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardOne();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkSuccessMessage();
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateOne() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOne();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkError();
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateOneExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateOneExpired();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorExpired();
    }

//    Автоматизация покупки через кредит (вторая карта)

    @Test
    public void ShouldBeTestCreditTravelSuccessTwo() {
        MainPage mainPage = new MainPage();

        val validCard = DataHelper.getValidCardTwo();

        mainPage.fillDataForCredit(validCard);
        mainPage.checkSuccessMessage();
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateTwo() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwo();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkError();
    }

    @Test
    public void ShouldBeTestCreditTravelErrorDateTwoExpired() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidDateTwoExpired();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorExpired();
    }

    @Test
    public void ShouldBeTestCreditTravelErrorNumber() {
        MainPage mainPage = new MainPage();

        val InvalidCard = DataHelper.getInvalidNumber();

        mainPage.fillDataForCredit(InvalidCard);
        mainPage.checkErrorMessage();
    }
}

