package com.salesforce.framework.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.salesforce.framework.browser.Browser.getWebDriver;


public class AllureListener extends AllureTestNg {

    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        System.out.println(result.getMethod().getMethodName() + " failed!");

        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        // attach screenshots to report
        saveFailureScreenShot(driver);
    }


    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
//
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
//        try {
//            Allure.addAttachment(screenshot.getName(), new FileInputStream(screenshot));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        super.onTestFailure(result);
//    }
//
//    @Override
//    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
//        if (!testResult.isSuccess()) {
//            File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
//            try {
//                Allure.addAttachment(screenshot.getName(), new FileInputStream(screenshot));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
