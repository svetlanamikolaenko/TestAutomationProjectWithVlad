package com.salesforce.framework.pages.opportunity;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteOpportunityDialog extends OpportunitiesPage {

    @FindBy(xpath = "//h2[contains(text(),'Delete')]")
    private WebElement deleteOpportunityConfirmDialog;

    @FindBy(xpath = "//*[contains(@class, 'footer')]//*[@title='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class, 'detail')]")
    private WebElement dialogMessage;

    @Step("Click 'Delete' button")
    public OpportunitiesPage deleteOpportunity(){
        waitHelper().waitElementUntilVisible(deleteButton);
        deleteButton.click();
        return new OpportunitiesPage();
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(deleteOpportunityConfirmDialog);
    }
}
