package com.rozetka.components.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PersonalInfoForm {

    SelenideElement headerSection = $$(".personal-section h3").findBy(text("Личные данные"));
    SelenideElement personalData = headerSection.parent().parent();

    By lastName = By.cssSelector("#lastName");
    By lastNameLabel = By.cssSelector("[for='lastName']");


    public PersonalInfoForm edit(){
        personalData.$("button").click();
        return this;
    }

    public PersonalInfoForm changeSurname(String surname){
        personalData.$(lastName).shouldBe(visible).clear();
        personalData.$(lastName).val(surname);
        return this;
    }

    public void save(){
        personalData.$("[type='submit'").should(enabled).click();
    }

    public String getActualSurname(){
        return personalData.$(lastNameLabel).parent().$("[class*='value']").shouldBe(visible).getText();
    }
}
