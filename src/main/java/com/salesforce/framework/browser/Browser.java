package com.salesforce.framework.browser;

import com.salesforce.framework.config.TestConfig;
import com.salesforce.framework.models.Customer;
import com.salesforce.framework.pages.LoginPage;
import com.salesforce.framework.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Browser {
    private static WebDriver driver;
    private static final String BASE_URL = TestConfig.CONFIG.baseUrl();

    public static WebDriver getWebDriver() {
        if (driver == null) {
            switch (TestConfig.CONFIG.browser()) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static String getBaseUrl(){
        return BASE_URL + "/";
    }

    @Step("Open Login page")
    public LoginPage openLoginPage(){
        getWebDriver().get(getBaseUrl());
        return new LoginPage();
    }

    public String getCurrentUrl(){
        return getWebDriver().getCurrentUrl();
    }

    @Step("Login as ['{customer.email}', '{customer.password}']")
    public HomePage loginAs(Customer customer) {
        LoginPage loginPage = openLoginPage();
        HomePage homePage;
        if (loginPage.isOpened()) {
            homePage = loginPage.loginAsUserAndOpenHomePage(customer);
        } else {
            homePage = new HomePage();
        }
        return homePage;
    }

    public static void closeWebDriver(){
        if (driver != null){
            driver.quit();
        }
    }
}
