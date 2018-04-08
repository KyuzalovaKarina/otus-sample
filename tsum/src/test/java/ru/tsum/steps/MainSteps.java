package ru.tsum.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import ru.tsum.pages.MainPage;

public class MainSteps {
    MainPage mainPage;


    @Пусть("^открыта главная страница$")
    public void openMainPage() {
        mainPage.open();
    }

    @Когда("^нажата кнопка \"Личный кабинет\"$")
    public void clickLogin() {
        mainPage.btnLogin.click();
    }
}
