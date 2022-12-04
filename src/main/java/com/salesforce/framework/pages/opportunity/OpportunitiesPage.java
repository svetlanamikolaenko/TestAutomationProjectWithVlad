package com.salesforce.framework.pages.opportunity;

import com.salesforce.framework.enums.SalesTabLabels;
import com.salesforce.framework.models.Opportunity;
import com.salesforce.framework.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class OpportunitiesPage extends HomePage {

    @FindBy(xpath = "//ul[contains(@class,'actions ')]//*[text()='New']")
    private WebElement newOpportunityButton;

    @FindBy(xpath = "//*[contains(@class,'breadcrumb')]//*[text()='Opportunities']")
    private WebElement opportunityTitleLabel;

    @FindBy(xpath = "//div[contains(@class,'forceActionsDropDownMenuList')]")
    private WebElement dropDownMenu;

    @FindBy(xpath = "//a[@role='menuitem']/div[@title='Edit']")
    private WebElement editButton;

    @FindBy(xpath = "//a[@role='menuitem']/div[@title='Delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//table[contains(@class, 'forceRecordLayout')]")
    private WebElement opportunitiesTableLayout;

    @FindBy(xpath = "//*[@name='Opportunity-search-input']")
    private WebElement searchOpportunityField;

    @FindBy(xpath = "//table[descendant::*[text()='Opportunity Name']]//a[@data-refid]")
    private List<WebElement> recordsLinks;

    private static final String OPPORTUNITY_NAME_FORMAT = "//a[text()='%s']";
    private static final String OPPORTUNITY_ACTION_MENU_FORMAT = "//a[text()='%s']//ancestor::tr//td//a[last()]";

    @Step("Open 'Home' tab")
    public HomePage openHomeTab() {
        navigateToSalesTab(SalesTabLabels.HOME);
        return new HomePage();
    }

    @Step("Get opportunity records links")
    public List<String> getOpportunityRecordsNames() {
        return recordsLinks
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Get all opportunities with name '{0}'")
    public List<WebElement> getOpportunityWithName(String name) {
        waitHelper().waitElementsUntilVisible(recordsLinks);
        return recordsLinks
                .stream()
                .filter(record -> record.getText().equals(name))
                .collect(Collectors.toList());
    }

    @Step("Open first opportunity with name '{0}'")
    public OpportunityDetailsPage openFirstOpportunityWithName(String name) {
        waitHelper().waitLocatorUntilVisible(String.format(OPPORTUNITY_NAME_FORMAT, name));
        getOpportunityWithName(name).get(0).click();
        return new OpportunityDetailsPage();
    }

    @Step("Get opportunity links with name '{0}'")
    public List<String> getOpportunityLinksWithName(String name) {
        waitHelper().waitElementsUntilVisible(recordsLinks);
        return getOpportunityWithName(name)
                .stream()
                .map(element -> element.getAttribute("href"))
                .collect(Collectors.toList());
    }

    @Step("Get first opportunity id with name '{0}'")
    public String getFirstOpportunityIdWithName(String name) {
        List<String> list = Arrays.asList(getOpportunityLinksWithName(name).get(0).split("/"));
        return list.get(list.size() - 2);
    }

    @Step("Click on 'New' button")
    public NewOpportunityPopup clickOnNewButton() {
        waitHelper().waitElementUntilVisible(newOpportunityButton);
        newOpportunityButton.click();
        return new NewOpportunityPopup();
    }

    @Step("Click on '{opportunity.name}' action menu")
    public OpportunitiesPage openActionMenu(Opportunity opportunity) {
        waitHelper().waitLocatorUntilVisible(String.format(OPPORTUNITY_ACTION_MENU_FORMAT, opportunity.getName()));
        findElementByXpath(String.format(OPPORTUNITY_ACTION_MENU_FORMAT, opportunity.getName())).click();
        return this;
    }

    @Step("Click on 'Edit' action")
    public void chooseEditAction() {
        waitHelper().waitElementUntilVisible(editButton);
        jsHelper().clickJS(editButton);
    }

    @Step("Click on 'Delete' action")
    public void chooseDeleteAction() {
        waitHelper().waitElementUntilVisible(deleteButton);
        jsHelper().clickJS(deleteButton);
    }

    @Step("Open 'Edit {opportunity.name}' Popup")
    public EditOpportunityPopup openOpportunityEditPopup(Opportunity opportunity) {
        openActionMenu(opportunity);
        chooseEditAction();
        return new EditOpportunityPopup();
    }

    @Step("Open 'Delete Opportunity' Dialog")
    public DeleteOpportunityDialog openDeleteOpportunityDialog(Opportunity opportunity) {
        openActionMenu(opportunity);
        chooseDeleteAction();
        return new DeleteOpportunityDialog();
    }

    @Step("Open opportunity '{opportunity.name}'")
    public OpportunityDetailsPage openOpportunityDetails(Opportunity opportunity) {
        waitHelper().waitLocatorUntilVisible(String.format(OPPORTUNITY_NAME_FORMAT, opportunity.getName()));
        jsHelper().clickJS(findElementByXpath(String.format(OPPORTUNITY_NAME_FORMAT, opportunity.getName())));
        return new OpportunityDetailsPage();
    }

    @Step("Is '{opportunity.name}' displayed")
    public boolean isOpportunityDisplayed(Opportunity opportunity) {
        try {
            waitHelper().waitLocatorUntilInvisible(String.format(OPPORTUNITY_NAME_FORMAT, opportunity.getName()));
            findElementByXpath(String.format(OPPORTUNITY_NAME_FORMAT, opportunity.getName())).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Search for opportunity '{opportunity.name}'")
    public OpportunitiesPage searchForOpportunity(Opportunity opportunity) {
        waitHelper().waitElementUntilVisible(searchOpportunityField);
        searchOpportunityField.clear();
        searchOpportunityField.sendKeys(opportunity.getName());
        waitHelper().waitElementUntilVisible(opportunitiesTableLayout);
        return this;
    }

    @Override
    public void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(opportunitiesTableLayout);
        waitHelper().waitElementUntilVisible(opportunityTitleLabel);
    }
}
