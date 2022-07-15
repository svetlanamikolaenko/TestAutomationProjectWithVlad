package com.salesforce.framework.pages.sales;

import com.salesforce.framework.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class,'appName')]/span[@title='Sales']")
    private WebElement salesTitleLabel;

    String salesNavItemName = "data-id='%s";

//    @Step("Get 'Sales' title")
//    public String getSalesTitle() {
//        waitUntilLoaded();
//        return salesTitleLabel.getText();
//    }

    @Step("Open sales nav item")
    public SalesOpportunityPage navigateToSalesTab(String navItemName){
        waitUntilLoaded();
        findElementByXpath(String.format(salesNavItemName, navItemName)).click();
        return new SalesOpportunityPage();
    }


    @Override
    public void waitUntilLoaded(){
        waitHelper().waitElementUntilVisible(salesTitleLabel);
    }

}
