package com.salesforce.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BasePage extends AbstractPage {

    public WebElement findElementByXpath(String element){
        return driver.findElement(By.xpath(element));
    }
    @Override
    protected void waitUntilLoaded() throws Exception {
        throw new Exception("waitUntilLoaded should be implemented");
    }
}
