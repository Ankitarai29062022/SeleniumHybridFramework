package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

//    @Test
//    public void testLogin() throws InterruptedException {
//
//        String textToSearch = ExcelUtils.getCellData("Sheet1", 1, 0);
//
//
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login(textToSearch);
//
//    }

//    @Test(dataProvider = "loginData")
//    public void testLogin(String textToSearch) throws InterruptedException {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login(textToSearch);
//    }
    @Test(dataProvider = "loginData")
    public void testLogin(String textToSearch) throws InterruptedException {
        if (textToSearch == null || textToSearch.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(textToSearch);
    }


    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        int rowCount = ExcelUtils.getRowCount("Sheet1")-1;
        Object[][] data = new Object[rowCount][1];
        for (int i = 0; i < rowCount; i++) {
            data[i][0] = ExcelUtils.getCellData("Sheet1", i+1, 0);
        }
        return data;
    }
}
