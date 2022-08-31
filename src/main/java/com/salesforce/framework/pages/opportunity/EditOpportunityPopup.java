package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.opportunity.FieldsNames;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditOpportunityPopup extends OpportunitiesPage{

    @FindBy(xpath = "//div[contains(@class, 'modal')]//h2[contains(text(),'Edit')]")
    private WebElement editOpportunityModalWindow;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement saveOpportunityButton;

    private static final String INPUT_FIELD_FORMAT = "//label[text()='%s']//..//input[@type='text']";

    @Step("Enter '{1}' into {label.fieldLabel}")
    public EditOpportunityPopup changeValueInField(FieldsNames label, String value){
        String fieldLabel = label.getFieldLabel();
        waitHelper().waitLocatorUntilVisible(String.format(INPUT_FIELD_FORMAT, fieldLabel));
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).clear();
        findElementByXpath(String.format(INPUT_FIELD_FORMAT, fieldLabel)).sendKeys(value);
        return this;
    }

    @Step("Click on 'Save' button")
    public EditOpportunityPopup clickOnSaveButton(){
        waitHelper().waitElementUntilVisible(saveOpportunityButton);
        saveOpportunityButton.click();
        return this;
    }

    @Step("Save opportunity")
    public OpportunitiesPage saveOpportunity(){
        clickOnSaveButton();
        return new OpportunitiesPage();
    }

    @Override
    public void waitUntilLoaded() {
            waitHelper().waitElementUntilVisible(editOpportunityModalWindow);
        }
}
