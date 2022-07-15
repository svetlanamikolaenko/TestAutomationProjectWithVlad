package com.salesforce.framework.pages.sales;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SalesOpportunityPage extends SalesPage{

//    @FindBy (xpath = "//div[contains(@class, 'breadcrumb')]//*[text()='Opportunities']")
//    private WebElement opportunitiesBreadcrumbLabel;

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

    @FindBy(xpath = "//label[text()='Stage']/..//*[@role='option'][@data-value='Needs Analysis']")
    private WebElement needsAnalysisValue;

    public void clickNewOpportunityButton(){
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
    }

    public void enterOpportunityName(String opportunityName){
        waitHelper().waitElementUntilVisible(newOpportunityModalWindow);
        opportunityNameTextField.clear();
        opportunityNameTextField.sendKeys(opportunityName);
    }

    public void enterCloseDate(String closeDate){
        waitHelper().waitElementUntilVisible(closeDatePickerField);
        closeDatePickerField.clear();
        closeDatePickerField.sendKeys();
    }

    public void clickOnStagePicklist(){
        waitHelper().waitElementUntilVisible(stagePicklist);
        jsHelper().clickJS(stagePicklist);
    }


//    @Step("Populate Stage")
//    public SalesOpportunityPage selectStage(WebElement stage) {
//        waitHelper().waitElementUntilVisible(stagePicklist);
//        Actions actions = new Actions(driver);
//        actions.click(stagePicklist)
//                .moveToElement(stage)
//                .click(stage)
//                .build()
//                .perform();
//        return this;
//    }

    public void chooseStage(){
        jsHelper().clickJS(needsAnalysisValue);
    }


}
