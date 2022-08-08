package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.OpportunityFieldsNames;
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

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    @FindBy(xpath = "//button[@name='CancelEdit']")
    private WebElement cancelOpportunityButton;

    @FindBy(xpath = "//div[contains(@class, 'forceFormPageError')]")
    private WebElement formPageErrorDialog;

    @FindBy(xpath = "//div[contains(@class, 'forceFormPageError')]//*[contains(@class,'pageErrorHeader')]")
    private WebElement formPageErrorDialogHeader;

    @FindBy(xpath = "//div[contains(@class, 'forceFormPageError')]//*[contains(@class,'fieldLevelErrors')]")
    private WebElement formFieldErrorDialog;

    private static final String VALUE_IN_PICKLIST_FORMAT = "//*[@role='option'][@data-value='%s']";
    private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']//..//input[@type='text']";
    private static final String PICKLIST_LABEL_FORMAT ="//label[text()='%s']/..//button[@type='button']";

    public NewOpportunityPopup enterValuesInField(String fieldLabel, String value){
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }

    @Step("Enter '{0}' to field 'Opportunity Name'")
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

    @Step("Click on '{0}' picklist")
    public NewOpportunityPopup clickOnPicklist(String picklistLabel){
        waitHelper().waitLocatorUntilVisible(String.format(PICKLIST_LABEL_FORMAT, picklistLabel));
        findElementByXpath(String.format(PICKLIST_LABEL_FORMAT, picklistLabel)).click();
        return this;
    }

    @Step("Choose '{0}' option in picklist")
    public NewOpportunityPopup chooseOptionInPicklist(String option){
        waitHelper().waitLocatorUntilVisible(String.format(VALUE_IN_PICKLIST_FORMAT, option));
        jsHelper().clickJS(findElementByXpath(String.format(VALUE_IN_PICKLIST_FORMAT, option)));
        return this;
    }

    @Step("Select '{0}' in picklist")
    public NewOpportunityPopup selectValueInPicklist(String picklistLabel, String option){
        clickOnPicklist(picklistLabel);
        chooseOptionInPicklist(option);
        return this;
    }

    @Step("Click on 'Save' button")
    public void clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
    }

    @Step("Click on 'Cancel' button")
    public void clickOnCancelButton(){
        waitHelper().waitElementUntilVisible(cancelOpportunityButton);
        cancelOpportunityButton.click();
    }

    @Step("Enter all required fields in the new opportunity")
    public NewOpportunityPopup enterAllRequiredFields(Opportunity opportunity){
        waitUntilLoaded();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        selectValueInPicklist(OpportunityFieldsNames.STAGE.getFieldLabel(), opportunity.getStage());
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

    public String getFormPageErrorDialogHeaderText(){
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formPageErrorDialogHeader.getText();
    }

    public String getFormFieldErrorText(){
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formFieldErrorDialog.getText();
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
    }
}
