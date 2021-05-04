package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.junit.Assert.*;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class MainPage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonCredit = $(byText("Купить в кредит"));
    private SelenideElement inputNumberCard = $(".input__control[placeholder='0000 0000 0000 0000']");
    private SelenideElement inputMonth = $(".input__control[placeholder='08']");
    private SelenideElement inputYear = $(".input__control[placeholder='22']");
    private SelenideElement inputOwner = $(By.xpath("//*[@id='root']/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement inputCVC = $(".input__control[placeholder='999']");
    private SelenideElement buttonNext = $(byText("Продолжить"));
    private SelenideElement successMessageTitle = $(byText("Успешно"));
    private SelenideElement successMessage = $(withText("одобрена Банком"));
    private SelenideElement errorMessageTitle = $(byText("Ошибка"));
    private SelenideElement errorMessage = $(withText("Банк отказал"));
    private SelenideElement error = $(".input__sub");

    public void fillDataForBuy(DataHelper.CardInfo cardInfo) {
        buttonBuy.click();
        inputNumberCard.setValue(cardInfo.getNumber());
        inputMonth.setValue(cardInfo.getMonth());
        inputYear.setValue(cardInfo.getYear());
        inputOwner.sendKeys(cardInfo.getCardHolder());
        inputCVC.setValue(cardInfo.getCvcCode());
        buttonNext.click();
    }

    public void fillDataForCredit(DataHelper.CardInfo cardInfo) {
        buttonCredit.click();
        inputNumberCard.setValue(cardInfo.getNumber());
        inputMonth.setValue(cardInfo.getMonth());
        inputYear.setValue(cardInfo.getYear());
        inputOwner.sendKeys(cardInfo.getCardHolder());
        inputCVC.setValue(cardInfo.getCvcCode());
        buttonNext.click();
    }

    public void checkError() {
        String errorText = error.getText();

        assertEquals(errorText, "Неверно указан срок действия карты");
    }

    public void checkErrorExpired() {
        String errorText = error.getText();

        assertEquals(errorText, "Истёк срок действия карты");
    }

    public void checkSuccessMessage() {
        $(successMessageTitle).shouldBe(Condition.visible, Duration.ofSeconds(12000));
        successMessage.shouldBe(Condition.visible);
    }

    public void checkErrorMessage() {
        errorMessageTitle.shouldBe(Condition.visible, Duration.ofSeconds(12000));
        errorMessage.shouldBe(Condition.visible);
    }
}