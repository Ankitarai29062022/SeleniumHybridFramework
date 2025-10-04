package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() throws InterruptedException {
    	
        String textToSearch = ExcelUtils.getCellData("Sheet1", 1, 0);
       

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(textToSearch);
    }
}
