package tests;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private ExtentReports extent;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = ExtentReportManager.createInstance("reports/ExtentReport.html");
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String textToSearch, String password) throws InterruptedException {
        test = ExtentReportManager.createTest("Login Test - " + textToSearch);
        try {
            if (textToSearch == null || textToSearch.isEmpty()) {
                throw new IllegalArgumentException("Input text cannot be null or empty");
            }
            LoginPage loginPage = new LoginPage(driver);
            logger.info("Starting login test for username: {}", textToSearch);
            loginPage.login(textToSearch, password);
            String screenshotPath = captureScreenshot(driver, "LoginTestSuccess_" + textToSearch);
            test.pass("Login test passed for: " + textToSearch)
                    .addScreenCaptureFromPath(screenshotPath);
            logger.info("Login test completed for username: {}", textToSearch);
        } catch (Exception e) {
            String screenshotPath = captureScreenshot(driver, "LoginTestFailure_" + textToSearch);
            test.fail("Login test failed for: " + textToSearch + " - " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotPath);
        }
    }
    public String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
            FileUtils.copyFile(srcFile, new File(destPath));
            return destPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//    public String captureScreenshot(WebDriver driver, String screenshotName) {
//        try {
//            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            String destPath = "screenshots/" + screenshotName + ".png";
//            FileUtils.copyFile(srcFile, new File(destPath));
//            return destPath;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        int rowCount = ExcelUtils.getRowCount("Sheet1") - 1;
        Object[][] data = new Object[rowCount][2];
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = ExcelUtils.getCellData("Sheet1", i + 1, 0);
            data[i][1] = ExcelUtils.getCellData("Sheet1", i + 1, 1);
        }
        return data;
    }

    @AfterClass
    public void tearDownReport() {
        ExtentReportManager.flush();
    }
}