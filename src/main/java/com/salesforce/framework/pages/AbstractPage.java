package com.salesforce.framework.pages;

import com.salesforce.framework.browser.Browser;
import com.salesforce.framework.config.TestConfig;
import com.salesforce.framework.helpers.JavaScriptHelper;
import com.salesforce.framework.helpers.WebDriverWaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
     protected static final String BASE_PAGE = TestConfig.CONFIG.baseUrl();
     protected WebDriver driver;

     public AbstractPage() {
          driver = Browser.getWebDriver();
          PageFactory.initElements(driver, this);
     }

     public WebDriverWaitHelper waitHelper() {
          return new WebDriverWaitHelper();
     }

     public JavaScriptHelper jsHelper() {
          return new JavaScriptHelper();
     }

     public abstract void openPage();
     protected abstract void waitUntilLoaded();
}
