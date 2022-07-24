package com.salesforce.framework.pages.sales;

import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesOpportunitiesListPage extends SalesPage{

    @FindBy(xpath = "//*[@data-target-selection-name='sfdc:StandardButton.Opportunity.New']")
    private WebElement newOpportunityButton;

    @FindBy (xpath = "//div[contains(@class, 'modal')]//h2[text()='New Opportunity']")
    private WebElement newOpportunityModalWindow;

    @FindBy (xpath = "//input[@name='Name']")
    private WebElement opportunityNameTextField;

    @FindBy(xpath = "//input[@name='CloseDate']")
    private WebElement closeDatePickerField;

    @FindBy(xpath = "//input[@name='Amount']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@name='NextStep']")
    private WebElement nextStepTextField;

    @FindBy(xpath = "//input[@name='Probability']")
    private WebElement probabilityField;

    @FindBy(xpath = "//label[text()='Type']/..//button[@type='button']")
    private WebElement typePicklist;

    @FindBy(xpath = "//label[text()='Lead Source']/..//button[@type='button']")
    private WebElement leadSource;

    @FindBy(xpath = "//label[text()='Stage']/..//button[@type='button']")
    private WebElement stagePicklist;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    final String valueInPicklist = "//*[@role='option'][@data-value='%s']";

    @Step("Click on 'New' button")
    public SalesOpportunitiesListPage clickOnNewButton(){
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
        return this;
    }

    @Step("Enter 'Opportunity Name'")
    public SalesOpportunitiesListPage enterOpportunityName(String opportunityName){
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
        opportunityNameTextField.clear();
        opportunityNameTextField.sendKeys(opportunityName);
        return this;
    }

    @Step("Enter 'Close Date'")
    public SalesOpportunitiesListPage enterCloseDate(String closeDate){
        waitHelper().waitElementUntilVisible(closeDatePickerField);
        closeDatePickerField.clear();
        closeDatePickerField.sendKeys(closeDate);
        return this;
    }

    @Step("Enter 'Amount'")
    public SalesOpportunitiesListPage enterAmount(double amount){
        amountField.clear();
        amountField.sendKeys(String.valueOf(amount));
        return this;
    }

    @Step("Enter 'Next Step'")
    public SalesOpportunitiesListPage enterNextStep(String nextStep){
        nextStepTextField.clear();
        nextStepTextField.sendKeys(nextStep);
        return this;
    }

    @Step("Click on 'Stage' picklist")
    public SalesOpportunitiesListPage clickOnStagePicklist(){
        waitHelper().waitElementUntilVisible(stagePicklist);
        stagePicklist.click();
        return this;
    }

    @Step("Choose '{0}' option in picklist")
    public SalesOpportunitiesListPage chooseOptionInPicklist(String option){
        jsHelper().clickJS(findElementByXpath(String.format(valueInPicklist, option)));
        return this;
    }

    @Step("Click on 'Save' button")
    public SalesOpportunitiesListPage clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return this;
    }

    @Step("Add new opportunity")
    public SalesOpportunityRecordPage addNewOpportunity(Opportunity opportunity){
        clickOnNewButton();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        clickOnStagePicklist();
        chooseOptionInPicklist(opportunity.getStage());
        clickOnSaveButton();
        return new SalesOpportunityRecordPage();
    }

    @Step("Add new opportunity")
    public SalesOpportunityRecordPage addOpportunity(Opportunity opportunity){
        clickOnNewButton();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        clickOnStagePicklist();
        chooseOptionInPicklist(opportunity.getStage());
        enterAmount(opportunity.getAmount());
        enterNextStep(opportunity.getNextStep());
        clickOnSaveButton();
        return new SalesOpportunityRecordPage();
    }

}
