package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement buttonBuy = $(byText("Купить"));
    private SelenideElement buttonCredit = $(byText("Купить в кредит"));


    public void clickForBuy() {
        buttonBuy.click();
    }

    public void clickForCredit() {
        buttonCredit.click();
    }
}
