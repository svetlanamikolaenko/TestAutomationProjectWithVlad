package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

public class NewOpportunityPopup extends OpportunitiesPage{

    @FindBy (xpath = "//*[contains(@class, 'modal')]//*[text()='New Opportunity']")
    private WebElement newOpportunityModalWindow;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    @FindBy(xpath = "//button[@name='CancelEdit']")
    private WebElement cancelOpportunityButton;

    @FindBy(xpath = "//div[contains(@class, 'forceFormPageError')]")
    private WebElement formPageErrorDialog;

    @FindBy(xpath = "//*[contains(@class,'pageErrorHeader')]")
    private WebElement formPageErrorDialogHeader;

    @FindBy(xpath = "//*[contains(@class,'fieldLevelErrors')]")
    private WebElement formFieldErrorDialog;

    @FindBy(xpath = "//label[text()='Stage']/following-sibling::div//*[@data-item-id]")
    List<WebElement> stagesDropDownList;

    private static final String VALUE_IN_PICKLIST_FORMAT = "//*[@role='option'][@data-value='%s']";
    private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']/parent::*//*[contains(@id, 'input')]";
    private static final String INPUT_FIELD_ERROR_MESSAGE_FORMAT = "//label[text()='%s']//ancestor::*[@name='inputField']//*[contains(@class, 'help')]";
    private static final String PICKLIST_LABEL_FORMAT ="//label[text()='%s']/parent::*//button";

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

    public List<String> getValuesInStageDropDown(){
        waitHelper().waitElementUntilVisible(stagesDropDownList.get(0));
        return stagesDropDownList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
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
        enterValueIntoInputField(AMOUNT, String.valueOf(opportunity.getAmount()));
        return this;
    }

    @Step("Enter '{opportunity.nextStep}'")
    public NewOpportunityPopup enterNextStep(Opportunity opportunity){
        enterValueIntoInputField(NEXT_STEP, String.valueOf(opportunity.getNextStep()));
        return this;
    }

    @Step("Enter '{opportunity.orderNumber}'")
    public NewOpportunityPopup enterOrderNumber(Opportunity opportunity){
        enterValueIntoInputField(ORDER_NUMBER, String.valueOf(opportunity.getOrderNumber()));
        return this;
    }

    @Step("Enter '{opportunity.probability}'")
    public NewOpportunityPopup enterProbability(Opportunity opportunity){
        enterValueIntoInputField(PROBABILITY, String.valueOf(opportunity.getProbability()));
        return this;
    }

    @Step("Enter '{opportunity.trackingNumber}'")
    public NewOpportunityPopup enterTrackingNumber(Opportunity opportunity){
        enterValueIntoInputField(TRACKING_NUMBER, opportunity.getTrackingNumber());
        return this;
    }

    @Step("Enter '{opportunity.currentGenerator}'")
    public NewOpportunityPopup enterCurrentGenerator(Opportunity opportunity){
        enterValueIntoInputField(CURRENT_GENERATOR, opportunity.getCurrentGenerator());
        return this;
    }

    @Step("Enter '{opportunity.mainCompetitor}'")
    public NewOpportunityPopup enterMainCompetitor(Opportunity opportunity){
        enterValueIntoInputField(TRACKING_NUMBER, opportunity.getMainCompetitor());
        return this;
    }

    @Step("Enter '{opportunity.description}'")
    public NewOpportunityPopup enterDescription(Opportunity opportunity){
        enterValueIntoInputField(DESCRIPTION, opportunity.getDescription());
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
