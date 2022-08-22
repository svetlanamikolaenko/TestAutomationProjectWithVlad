package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

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

    @FindBy(xpath = "//*[contains(@class,'pageErrorHeader')]")
    private WebElement formPageErrorDialogHeader;

    @FindBy(xpath = "//div[contains(@class, 'forceFormPageError')]//*[contains(@class,'fieldLevelErrors')]")
    private WebElement formFieldErrorDialog;

    private static final String VALUE_IN_PICKLIST_FORMAT = "//*[@role='option'][@data-value='%s']";
    private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']//..//input[@type='text']";
    private static final String INPUT_FIELD_ERROR_MESSAGE_FORMAT = "//label[text()='%s']//..//..//*[@class='slds-form-element__help']";
    private static final String PICKLIST_LABEL_FORMAT ="//label[text()='%s']/..//button[@type='button']";

    @Step("Enter '{1}' into {label.fieldLabel}")
    public NewOpportunityPopup enterValueIntoInputField(FieldsNames label, String value){
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }


    @Step("Enter '{0}' to field 'Opportunity Name'")
    public NewOpportunityPopup enterOpportunityName(String opportunityName){
        enterValueIntoInputField(OPPORTUNITY_NAME, opportunityName);
        return this;
    }

    @Step("Enter '{0}' into close date")
    public NewOpportunityPopup enterCloseDate(String closeDate){
        enterValueIntoInputField(CLOSE_DATE, closeDate);
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

    @Step("Select '{1}' in picklist '{label.fieldLabel}'")
    public NewOpportunityPopup selectValueInPicklist(FieldsNames label, String option){
        clickOnPicklist(label.getFieldLabel());
        chooseOptionInPicklist(option);
        return this;
    }

    @Step("Click on 'Save' button")
    public NewOpportunityPopup clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return this;
    }

    @Step("Save opportunity")
    public OpportunityDetailsPage saveOpportunity(){
        clickOnSaveButton();
        return new OpportunityDetailsPage();
    }

    @Step("Click on 'Cancel' button")
    public OpportunitiesPage clickOnCancelButton(){
        waitHelper().waitElementUntilVisible(cancelOpportunityButton);
        cancelOpportunityButton.click();
        return new OpportunitiesPage();
    }

    @Step("Enter All Required fields {opportunity.name}, {opportunity.closeDate}, {opportunity.stage} ")
    public NewOpportunityPopup enterAllRequiredFields(Opportunity opportunity){
        waitUntilLoaded();
        enterOpportunityName(opportunity.getName());
        enterCloseDate(opportunity.getCloseDate());
        selectValueInPicklist(STAGE, opportunity.getStage());
        return this;
    }

    @Step("Enter '{opportunity.amount}'")
    public NewOpportunityPopup enterAmount(Opportunity opportunity){
        amountField.clear();
        amountField.sendKeys(String.valueOf(opportunity.getAmount()));
        return this;
    }

    @Step("Enter '{opportunity.nextStep}'")
    public NewOpportunityPopup enterNextStep(Opportunity opportunity){
        nextStepTextField.clear();
        nextStepTextField.sendKeys(opportunity.getNextStep());
        return this;
    }

    @Step("Enter '{opportunity.orderNumber}'")
    public NewOpportunityPopup enterOrderNumber(Opportunity opportunity){
        orderNumberField.clear();
        orderNumberField.sendKeys(String.valueOf(opportunity.getOrderNumber()));
        return this;
    }

    @Step("Enter '{opportunity.probability}'")
    public NewOpportunityPopup enterProbability(Opportunity opportunity){
        probabilityField.clear();
        probabilityField.sendKeys(String.valueOf(opportunity.getProbability()));
        return this;
    }

    @Step("Enter '{opportunity.trackingNumber}'")
    public NewOpportunityPopup enterTrackingNumber(Opportunity opportunity){
        trackingNumberField.clear();
        trackingNumberField.sendKeys(opportunity.getTrackingNumber());
        return this;
    }

    @Step("Enter '{opportunity.currentGenerator}'")
    public NewOpportunityPopup enterCurrentGenerator(Opportunity opportunity){
        currentGeneratorField.clear();
        currentGeneratorField.sendKeys(opportunity.getCurrentGenerator());
        return this;
    }

    @Step("Enter '{opportunity.mainCompetitor}'")
    public NewOpportunityPopup enterMainCompetitor(Opportunity opportunity){
        mainCompetitorField.clear();
        mainCompetitorField.sendKeys(opportunity.getMainCompetitor());
        return this;
    }

    @Step("Enter '{opportunity.description}'")
    public NewOpportunityPopup enterDescription(Opportunity opportunity){
        descriptionField.clear();
        descriptionField.sendKeys(opportunity.getDescription());
        return this;
    }

    @Step("Get error message in the form header")
    public String getFormPageErrorDialogHeaderText(){
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formPageErrorDialogHeader.getText();
    }

    @Step("Get error field in Form Page Dialog")
    public String getFieldErrorTextInFormPageDialog(){
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formFieldErrorDialog.getText();
    }

    @Step("Get error message under required field '{label.fieldLabel}'")
    public String getErrorMessageUnderField(FieldsNames label){
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_ERROR_MESSAGE_FORMAT, fieldLabel));
        return findElementByXpath((String.format(INPUT_FIELD_ERROR_MESSAGE_FORMAT, fieldLabel))).getText();
    }

    @Step("Verify if 'We hit a snag.' is displayed")
    public boolean isFormPageErrorDialogDisplayed(){
        return getFormPageErrorDialogHeaderText().equals("We hit a snag.");
    }

    @Step("Verify if '{label.fieldLabel}' is displayed in Form Page Error Dialog")
    public boolean isFieldDisplayedInFormPageDialog(FieldsNames label){
        return getFieldErrorTextInFormPageDialog().contains(label.getFieldLabel());
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
    }
}
