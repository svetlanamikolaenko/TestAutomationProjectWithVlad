package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import com.salesforce.framework.models.Opportunity;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.salesforce.framework.enums.opportunity.FieldsNames.*;

public class NewOpportunityPopup extends OpportunitiesPage {

    @FindBy(xpath = "//h2[text()='New Opportunity']")
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

    @FindBy(xpath = "//*[@data-item-id]")
    List<WebElement> picklistItems;

    private static final String INPUT_FIELD = "//*[@name='inputField'][descendant::label[text()='%s']]";
    private static final String INPUT_FIELD_TEXT_INPUT_FORMAT = INPUT_FIELD + "//input";
    //private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']/parent::*//*[contains(@id, 'input')]";
    private static final String INPUT_FIELD_TEXT_AREA_FORMAT = INPUT_FIELD + "//textarea";
    private static final String INPUT_FIELD_ERROR_MESSAGE_FORMAT = INPUT_FIELD + "//*[contains(@class,'help')]";
    //private static final String INPUT_FIELD_ERROR_MESSAGE_FORMAT = "//label[text()='%s']//ancestor::*[@name='inputField']//*[contains(@class, 'help')]";
    private static final String INPUT_FIELD_OPEN_PICKLIST_FORMAT = INPUT_FIELD + "//button";
    //private static final String PICKLIST_LABEL_FORMAT ="//label[text()='%s']/parent::*//button";
    private static final String VALUE_IN_PICKLIST_FORMAT = "//*[@data-value='%s']";

    @Step("Enter '{1}' into {label.fieldLabel}")
    public NewOpportunityPopup enterValueIntoInputField(FieldsNames label, String value) {
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_TEXT_INPUT_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_TEXT_INPUT_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_TEXT_INPUT_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }

    @Step("Enter '{1}' into {label.fieldLabel}")
    public NewOpportunityPopup enterValueIntoTextAreaField(FieldsNames label, String value) {
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_TEXT_AREA_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_TEXT_AREA_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_TEXT_AREA_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }

    @Step("Enter '{opportunity.name}''")
    public NewOpportunityPopup enterOpportunityName(Opportunity opportunity) {
        enterValueIntoInputField(OPPORTUNITY_NAME, opportunity.getName());
        return this;
    }

    @Step("Enter '{opportunity.closeDate}'")
    public NewOpportunityPopup enterCloseDate(Opportunity opportunity) {
        enterValueIntoInputField(CLOSE_DATE, opportunity.getCloseDate());
        return this;
    }

    @Step("Click on '{0}' picklist")
    public NewOpportunityPopup clickOnPicklist(String picklistLabel) {
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_OPEN_PICKLIST_FORMAT, picklistLabel));
        findElementByXpath(String.format(INPUT_FIELD_OPEN_PICKLIST_FORMAT, picklistLabel)).click();
        return this;
    }

    @Step("Choose '{0}' option in picklist")
    public NewOpportunityPopup chooseOptionInPicklist(String option) {
        waitHelper().waitLocatorUntilVisible(String.format(VALUE_IN_PICKLIST_FORMAT, option));
        jsHelper().clickJS(findElementByXpath(String.format(VALUE_IN_PICKLIST_FORMAT, option)));
        return this;
    }

    public List<String> getValuesInStageDropDown() {
        waitHelper().waitElementUntilVisible(picklistItems.get(0));
        return picklistItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Select '{1}' in picklist '{label.fieldLabel}'")
    public NewOpportunityPopup selectValueInPicklist(FieldsNames label, String option) {
        clickOnPicklist(label.getFieldLabel());
        chooseOptionInPicklist(option);
        return this;
    }

    @Step("Enter '{opportunity.stage}'")
    public NewOpportunityPopup selectStage(Opportunity opportunity) {
        selectValueInPicklist(STAGE, opportunity.getStage());
    return this;
    }

    @Step("Click on 'Save' button")
    public NewOpportunityPopup clickOnSaveButton() {
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return this;
    }

    @Step("Save opportunity")
    public OpportunityDetailsPage saveOpportunity() {
        clickOnSaveButton();
        return new OpportunityDetailsPage();
    }

    @Step("Click on 'Cancel' button")
    public OpportunitiesPage clickOnCancelButton() {
        waitHelper().waitElementUntilVisible(cancelOpportunityButton);
        cancelOpportunityButton.click();
        return new OpportunitiesPage();
    }

    @Step("Enter All Required fields {opportunity.name}, {opportunity.closeDate}, {opportunity.stage} ")
    public NewOpportunityPopup enterAllRequiredFields(Opportunity opportunity) {
        waitUntilLoaded();
        enterOpportunityName(opportunity);
        enterCloseDate(opportunity);
        selectStage(opportunity);
        return this;
    }

    @Step("Enter '{opportunity.amount}'")
    public NewOpportunityPopup enterAmount(Opportunity opportunity) {
        enterValueIntoInputField(AMOUNT, String.valueOf(opportunity.getAmount()));
        return this;
    }

    @Step("Enter '{opportunity.nextStep}'")
    public NewOpportunityPopup enterNextStep(Opportunity opportunity) {
        enterValueIntoInputField(NEXT_STEP, String.valueOf(opportunity.getNextStep()));
        return this;
    }

    @Step("Enter '{opportunity.orderNumber}'")
    public NewOpportunityPopup enterOrderNumber(Opportunity opportunity) {
        enterValueIntoInputField(ORDER_NUMBER, String.valueOf(opportunity.getOrderNumber()));
        return this;
    }

    @Step("Enter '{opportunity.probability}'")
    public NewOpportunityPopup enterProbability(Opportunity opportunity) {
        enterValueIntoInputField(PROBABILITY, String.valueOf(opportunity.getProbability()));
        return this;
    }

    @Step("Enter '{opportunity.trackingNumber}'")
    public NewOpportunityPopup enterTrackingNumber(Opportunity opportunity) {
        enterValueIntoInputField(TRACKING_NUMBER, opportunity.getTrackingNumber());
        return this;
    }

    @Step("Enter '{opportunity.currentGenerator}'")
    public NewOpportunityPopup enterCurrentGenerator(Opportunity opportunity) {
        enterValueIntoInputField(CURRENT_GENERATOR, opportunity.getCurrentGenerator());
        return this;
    }

    @Step("Enter '{opportunity.mainCompetitor}'")
    public NewOpportunityPopup enterMainCompetitor(Opportunity opportunity) {
        enterValueIntoInputField(MAIN_COMPETITOR, opportunity.getMainCompetitor());
        return this;
    }

    @Step("Enter '{opportunity.description}'")
    public NewOpportunityPopup enterDescription(Opportunity opportunity) {
        enterValueIntoTextAreaField(DESCRIPTION, opportunity.getDescription());
        return this;
    }

    @Step("Enter '{opportunity.type}'")
    public NewOpportunityPopup selectType(Opportunity opportunity) {
        selectValueInPicklist(TYPE, opportunity.getType());
        return this;
    }

    @Step("Enter '{opportunity.deliveryInstallationStatus}'")
    public NewOpportunityPopup selectDeliveryStatus(Opportunity opportunity) {
        selectValueInPicklist(DELIVERY_STATUS, opportunity.getDeliveryInstallationStatus());
        return this;
    }

    @Step("Enter '{opportunity.leadSource}'")
    public NewOpportunityPopup selectLeadSource(Opportunity opportunity) {
        selectValueInPicklist(LEAD_SOURCE, opportunity.getLeadSource());
        return this;
    }

    @Step("Get error message in the form header")
    public String getFormPageErrorDialogHeaderText() {
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formPageErrorDialogHeader.getText();
    }

    @Step("Get error field in Form Page Dialog")
    public String getFieldErrorTextInFormPageDialog() {
        waitHelper().waitElementUntilVisible(formPageErrorDialog);
        return formFieldErrorDialog.getText();
    }

    @Step("Get error message under required field '{label.fieldLabel}'")
    public String getErrorMessageUnderField(FieldsNames label) {
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_ERROR_MESSAGE_FORMAT, fieldLabel));
        return findElementByXpath((String.format(INPUT_FIELD_ERROR_MESSAGE_FORMAT, fieldLabel))).getText();
    }

    @Step("Verify if 'We hit a snag.' is displayed")
    public boolean isFormPageErrorDialogDisplayed() {
        return getFormPageErrorDialogHeaderText().equals("We hit a snag.");
    }

    @Step("Verify if '{label.fieldLabel}' is displayed in Form Page Error Dialog")
    public boolean isFieldDisplayedInFormPageDialog(FieldsNames label) {
        return getFieldErrorTextInFormPageDialog().contains(label.getFieldLabel());
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
    }
}
