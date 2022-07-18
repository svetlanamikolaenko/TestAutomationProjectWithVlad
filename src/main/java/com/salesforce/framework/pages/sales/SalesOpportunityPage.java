package com.salesforce.framework.pages.sales;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesOpportunityPage extends SalesPage{

    @FindBy(xpath = "//*[@data-target-selection-name='sfdc:StandardButton.Opportunity.New']")
    private WebElement newOpportunityButton;

    @FindBy (xpath = "//div[contains(@class, 'modal')]//h2[text()='New Opportunity']")
    private WebElement newOpportunityModalWindow;

    @FindBy (xpath = "//label[text()='Opportunity Name']/..//input[@name='Name']")
    private WebElement opportunityNameTextField;

    @FindBy(xpath = "//label[text()='Close Date']/..//input[@name='CloseDate']")
    private WebElement closeDatePickerField;

    @FindBy(xpath = "//label[text()='Stage']/..//button[@type='button']")
    private WebElement stagePicklist;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    @FindBy(xpath = "//h1//*[@slot='primaryField']")
    private WebElement opportunityLabel;

    final String stageOptionInPicklist = "//label[text()='Stage']/..//*[@role='option'][@data-value='%s']";

    @Step("Click on 'New' button")
    public SalesOpportunityPage clickOnNewButton(){
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
        return this;
    }

    @Step("Enter 'Opportunity Name'")
    public SalesOpportunityPage enterOpportunityName(String opportunityName){
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
        opportunityNameTextField.clear();
        opportunityNameTextField.sendKeys(opportunityName);
        return this;
    }

    @Step("Enter 'Close Date'")
    public SalesOpportunityPage enterCloseDate(String closeDate){
        waitHelper().waitElementUntilVisible(closeDatePickerField);
        closeDatePickerField.clear();
        closeDatePickerField.sendKeys(closeDate);
        return this;
    }

    @Step("Click on 'Stage' picklist")
    public SalesOpportunityPage clickOnStagePicklist(){
        waitHelper().waitElementUntilVisible(stagePicklist);
        stagePicklist.click();
        return this;
    }

    @Step("Choose option in 'Stage' picklist")
    public SalesOpportunityPage chooseOptionInStagePicklist(String stageOption){
        jsHelper().clickJS(findElementByXpath(String.format(stageOptionInPicklist, stageOption)));
        return this;
    }

    @Step("Click on 'Save' button")
    public SalesOpportunityPage clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return this;
    }

    @Step("Add new opportunity")
    public SalesOpportunityPage addNewOpportunity(String opportunityName, String closeDate, String stageOption){
        clickOnNewButton();
        enterOpportunityName(opportunityName);
        enterCloseDate(closeDate);
        clickOnStagePicklist();
        chooseOptionInStagePicklist(stageOption);
        clickOnSaveButton();
        return this;
    }

    @Step("Get Opportunity Name")
    public String getOpportunityName(){
        waitHelper().waitElementUntilVisible(opportunityLabel);
        return opportunityLabel.getText();
    }
}
