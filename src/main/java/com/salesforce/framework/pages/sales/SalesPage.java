package com.salesforce.framework.pages.sales;

import com.salesforce.framework.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'appName')]/span[@title='Sales']")
    private WebElement salesTitleLabel;

    String salesNavItemName = "//*[contains(@class, 'navItem')]//*[text()='%s']";

    @Step("Navigate to 'Sales' tab")
    public SalesOpportunityPage navigateToSalesTab(String navItemName){
        waitUntilLoaded();
        jsHelper().clickJS(findElementByXpath(String.format(salesNavItemName, navItemName)));
        return new SalesOpportunityPage();
    }

    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(salesTitleLabel);
    }

}
