package com.salesforce.framework.pages;

import com.salesforce.framework.enums.SalesTabLabels;
import com.salesforce.framework.pages.opportunity.OpportunitiesPage;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@title='Home']")
    private WebElement homeTitleLabel;

    @FindBy(xpath = "//*[text()='Recent Records']")
    private WebElement recentRecordsCard;

    @FindBy(xpath = "//*[text()='Recent Records']//ancestor::div[contains(@class,'homeHomeCard ')]//*[@class='recentsRecordCardList']//*[contains(@class,'recentsRecordCardRow')]//a")
    private List<WebElement> recentRecordsList;

    private final static String SALES_TAB_LABEL_FORMAT = "//*[contains(@class, 'navItem')]/a[@title='%s']";

    @Step("Is 'Recent Records Card' displayed")
    public boolean isRecentRecordsCardDisplayed(){
        jsHelper().scrollToBottom();
        jsHelper().allElementsLoaded();
        jsHelper().refreshBrowser();
        try {
            waitHelper().waitElementUntilVisible(recentRecordsCard);
            return recentRecordsCard.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    @Step("Get 'Recent Records' List")
    public List<String> getRecentRecordsList() {
        List<String> recentRecords = new ArrayList<>();
        if(isRecentRecordsCardDisplayed()){
            recentRecords = recentRecordsList
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }
        return recentRecords;
    }

    @Step("Navigate to '{tab.tabLabel}' tab")
    public void navigateToSalesTab(SalesTabLabels tab){
        String tabLabel = tab.getTabLabel();
        waitHelper().waitLocatorUntilVisible(String.format(SALES_TAB_LABEL_FORMAT, tabLabel));
        jsHelper().clickJS(findElementByXpath(String.format(SALES_TAB_LABEL_FORMAT, tabLabel)));
    }

    @Step("Open 'Opportunities' tab")
    public OpportunitiesPage openOpportunityTab(){
        navigateToSalesTab(SalesTabLabels.OPPORTUNITIES);
        return new OpportunitiesPage();
    }

    @Step("Get 'Home Page' title")
    public String getHomeTitle() {
        return homeTitleLabel.getText();
    }

    @Override
    protected void waitUntilLoaded() {
        waitHelper().waitElementUntilVisible(homeTitleLabel);
    }
}
