package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewOpportunityPopup extends OpportunitiesPage{

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
    private WebElement deliveryInstallationStatusPicklist;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    private static final String VALUE_IN_PICKLIST_FORMAT = "//*[@role='option'][@data-value='%s']";
    private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']//..//input[@type='text']";

    public NewOpportunityPopup enterValuesInField(String fieldLabel, String value){
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }

    @Step("Enter 'Opportunity Name'")
    public NewOpportunityPopup enterOpportunityName(String opportunityName){
        waitUntilLoaded();
        opportunityNameTextField.clear();
        opportunityNameTextField.sendKeys(opportunityName);
        return this;
    }

    @Step("Enter 'Close Date'")
    public NewOpportunityPopup enterCloseDate(String closeDate){
        waitHelper().waitElementUntilVisible(closeDatePickerField);
        closeDatePickerField.clear();
        closeDatePickerField.sendKeys(closeDate);
        return this;
    }

    @Step("Click on 'Stage' picklist")
    public NewOpportunityPopup clickOnStagePicklist(){
        waitHelper().waitElementUntilVisible(stagePicklist);
        stagePicklist.click();
        return this;
    }

    @Step("Choose '{0}' option in picklist")
    public NewOpportunityPopup chooseOptionInPicklist(String option){
        waitHelper().waitLocatorUntilVisible(String.format(VALUE_IN_PICKLIST_FORMAT, option));
        jsHelper().clickJS(findElementByXpath(String.format(VALUE_IN_PICKLIST_FORMAT, option)));
        return this;
    }

    @Step("Click on 'Save' button")
    public OpportunityHeaderPage clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return new OpportunityHeaderPage();
    }

    @Step("Enter all required fields in the new opportunity")
    public NewOpportunityPopup enterAllRequiredFields(Opportunity opportunity){
        waitUntilLoaded();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        clickOnStagePicklist();
        chooseOptionInPicklist(opportunity.getStage());
        return this;
    }

    @Step("Enter 'Amount'")
    public NewOpportunityPopup enterAmount(Opportunity opportunity){
        amountField.clear();
        amountField.sendKeys(String.valueOf(opportunity.getAmount()));
        return this;
    }

    @Step("Enter 'Next Step'")
    public NewOpportunityPopup enterNextStep(Opportunity opportunity){
        nextStepTextField.clear();
        nextStepTextField.sendKeys(opportunity.getNextStep());
        return this;
    }

    @Step("Enter 'Order Number'")
    public NewOpportunityPopup enterOrderNumber(Opportunity opportunity){
        orderNumberField.clear();
        orderNumberField.sendKeys(String.valueOf(opportunity.getOrderNumber()));
        return this;
    }

    @Step("Enter 'Probability'")
    public NewOpportunityPopup enterProbability(Opportunity opportunity){
        probabilityField.clear();
        probabilityField.sendKeys(String.valueOf(opportunity.getProbability()));
        return this;
    }

    @Step("Enter 'Tracking Number'")
    public NewOpportunityPopup enterTrackingNumber(Opportunity opportunity){
        trackingNumberField.clear();
        trackingNumberField.sendKeys(opportunity.getTrackingNumber());
        return this;
    }

    @Step("Enter 'Current Generator'")
    public NewOpportunityPopup enterCurrentGenerator(Opportunity opportunity){
        currentGeneratorField.clear();
        currentGeneratorField.sendKeys(opportunity.getCurrentGenerator());
        return this;
    }

    @Step("Enter 'Main Competitor'")
    public NewOpportunityPopup enterMainCompetitor(Opportunity opportunity){
        mainCompetitorField.clear();
        mainCompetitorField.sendKeys(opportunity.getMainCompetitor());
        return this;
    }

    @Step("Enter 'Description'")
    public NewOpportunityPopup enterDescription(Opportunity opportunity){
        descriptionField.clear();
        descriptionField.sendKeys(opportunity.getDescription());
        return this;
    }

    @Step("Click on 'Type' picklist")
    public NewOpportunityPopup selectTypeInPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(typePicklist);
        typePicklist.click();
        chooseOptionInPicklist(opportunity.getType());
        return this;
    }

    @Step("Click on 'Lead Source' picklist")
    public NewOpportunityPopup selectLeadSourceInPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(leadSourcePicklist);
        leadSourcePicklist.click();
        chooseOptionInPicklist(opportunity.getLeadSource());
        return this;
    }

    @Step("Click on 'Lead Source' picklist")
    public NewOpportunityPopup selectDeliveryInstallationStatusPicklist(Opportunity opportunity){
        waitHelper().waitElementUntilVisible(deliveryInstallationStatusPicklist);
        deliveryInstallationStatusPicklist.click();
        chooseOptionInPicklist(opportunity.getDeliveryInstallationStatus());
        return this;
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
    }
}
