package com.salesforce.framework.pages.sales;

import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesOpportunitiesRecentlyViewedPage extends SalesPage{

    @FindBy(xpath = "//*[@data-target-selection-name='sfdc:StandardButton.Opportunity.New']")
    private WebElement newOpportunityButton;

    @FindBy (xpath = "//div[contains(@class, 'modal')]//h2[text()='New Opportunity']")
    private WebElement newOpportunityModalWindow;

    @FindBy(xpath = "//label[text()='Opportunity Name']//..//input[@type='text']")
    private WebElement opportunityNameTextField;

    @FindBy(xpath = "//label[text()='Close Date']//..//input[@type='text']")
    private WebElement closeDatePickerField;

    @FindBy(xpath = "//label[text()='Amount']//..//input[@type='text']")
    private WebElement amountField;

    @FindBy(xpath = "//label[text()='Next Step']//..//input[@type='text']")
    private WebElement nextStepTextField;

    @FindBy(xpath = "//label[text()='Tracking Number']//..//input[@type='text']")
    private WebElement trackingNumberField;

    @FindBy(xpath = "//label[text()='Order Number']//..//input[@type='text']")
    private WebElement orderNumberField;

    @FindBy(xpath = "//label[text()='Probability (%)']//..//input[@type='text']")
    private WebElement probabilityField;

    @FindBy(xpath = "//label[text()='Current Generator(s)']//..//input[@type='text']")
    private WebElement currentGeneratorField;

    @FindBy(xpath = "//label[text()='Main Competitor(s)']//..//input[@type='text']")
    private WebElement mainCompetitorField;

    @FindBy(xpath = "//label[text()='Description']//..//textarea")
    private WebElement descriptionField;

    @FindBy(xpath = "//label[text()='Type']/..//button[@type='button']")
    private WebElement typePicklist;

    @FindBy(xpath = "//label[text()='Lead Source']/..//button[@type='button']")
    private WebElement leadSourcePicklist;

    @FindBy(xpath = "//label[text()='Stage']/..//button[@type='button']")
    private WebElement stagePicklist;

    @FindBy(xpath = "//label[text()='Delivery/Installation Status']/..//button[@type='button']")
    private WebElement deliveryStatusPicklist;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    final String valueInPicklist = "//*[@role='option'][@data-value='%s']";

    @Step("Click on 'New' button")
    public SalesOpportunitiesRecentlyViewedPage clickOnNewButton(){
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
        return this;
    }

    @Step("Enter 'Opportunity Name'")
    public SalesOpportunitiesRecentlyViewedPage enterOpportunityName(String opportunityName){
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
        opportunityNameTextField.clear();
        opportunityNameTextField.sendKeys(opportunityName);
        return this;
    }

    @Step("Enter 'Close Date'")
    public SalesOpportunitiesRecentlyViewedPage enterCloseDate(String closeDate){
        waitHelper().waitElementUntilVisible(closeDatePickerField);
        closeDatePickerField.clear();
        closeDatePickerField.sendKeys(closeDate);
        return this;
    }


    @Step("Click on 'Stage' picklist")
    public SalesOpportunitiesRecentlyViewedPage clickOnStagePicklist(){
        waitHelper().waitElementUntilVisible(stagePicklist);
        stagePicklist.click();
        return this;
    }

    @Step("Choose '{0}' option in picklist")
    public SalesOpportunitiesRecentlyViewedPage chooseOptionInPicklist(String option){
        jsHelper().clickJS(findElementByXpath(String.format(valueInPicklist, option)));
        return this;
    }

    @Step("Click on 'Save' button")
    public SalesOpportunityRecordPage clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return new SalesOpportunityRecordPage();
    }

    @Step("Enter all required fields in the new opportunity")
    public SalesOpportunitiesRecentlyViewedPage enterAllRequiredFields(Opportunity opportunity){
        clickOnNewButton();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        clickOnStagePicklist();
        chooseOptionInPicklist(opportunity.getStage());
        return this;
    }

    @Step("Enter 'Amount'")
    public SalesOpportunitiesRecentlyViewedPage enterAmount(Opportunity opportunity){
        amountField.clear();
        amountField.sendKeys(String.valueOf(opportunity.getAmount()));
        return this;
    }

    @Step("Enter 'Next Step'")
    public SalesOpportunitiesRecentlyViewedPage enterNextStep(Opportunity opportunity){
        nextStepTextField.clear();
        nextStepTextField.sendKeys(opportunity.getNextStep());
        return this;
    }

    @Step("Enter 'Order Number'")
    public SalesOpportunitiesRecentlyViewedPage enterOrderNumber(Opportunity opportunity){
        orderNumberField.clear();
        orderNumberField.sendKeys(String.valueOf(opportunity.getOrderNumber()));
        return this;
    }

    @Step("Enter 'Probability'")
    public SalesOpportunitiesRecentlyViewedPage enterProbability(Opportunity opportunity){
        probabilityField.clear();
        probabilityField.sendKeys(String.valueOf(opportunity.getProbability()));
        return this;
    }

    @Step("Enter 'Tracking Number'")
    public SalesOpportunitiesRecentlyViewedPage enterTrackingNumber(Opportunity opportunity){
        trackingNumberField.clear();
        trackingNumberField.sendKeys(opportunity.getTrackingNumber());
        return this;
    }

    @Step("Enter 'Current Generator'")
    public SalesOpportunitiesRecentlyViewedPage enterCurrentGenerator(Opportunity opportunity){
        currentGeneratorField.clear();
        currentGeneratorField.sendKeys(opportunity.getCurrentGenerator());
        return this;
    }

    @Step("Enter 'Main Competitor'")
    public SalesOpportunitiesRecentlyViewedPage enterMainCompetitor(Opportunity opportunity){
        mainCompetitorField.clear();
        mainCompetitorField.sendKeys(opportunity.getMainCompetitor());
        return this;
    }

    @Step("Enter 'Description'")
    public SalesOpportunitiesRecentlyViewedPage enterDescription(Opportunity opportunity){
        descriptionField.clear();
        descriptionField.sendKeys(opportunity.getDescription());
        return this;
    }

    @Step("Click on 'Type' picklist")
    public SalesOpportunitiesRecentlyViewedPage selectTypeInPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(typePicklist);
        typePicklist.click();
        chooseOptionInPicklist(opportunity.getType());
        return this;
    }

    @Step("Click on 'Lead Source' picklist")
    public SalesOpportunitiesRecentlyViewedPage selectLeadSourceInPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(leadSourcePicklist);
        leadSourcePicklist.click();
        chooseOptionInPicklist(opportunity.getLeadSource());
        return this;
    }

    @Step("Click on 'Lead Source' picklist")
    public SalesOpportunitiesRecentlyViewedPage selectDeliveryStatusPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(deliveryStatusPicklist);
        deliveryStatusPicklist.click();
        chooseOptionInPicklist(opportunity.getDeliveryStatus());
        return this;
    }
}
