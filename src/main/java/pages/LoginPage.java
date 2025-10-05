package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    By usernameInput = By.id("username");
    By passwordField = By.id("password");
    By searchBottonClick = By.xpath("//button[@id='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username,String password) throws InterruptedException {
        logger.info("Attempting to log in with username: {}", username);
        // Existing code
        logger.info("Login successful for username: {}", username);
        driver.findElement(usernameInput).sendKeys(username);
        Thread.sleep(5000);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(searchBottonClick).click();
        
        String Capturetitle=driver.getTitle();
        String currentUrl=driver.getCurrentUrl();
       // String PageSource=driver.getPageSource();
        
        Thread.sleep(5000);
        System.out.println(Capturetitle);
        System.out.println(currentUrl);
        //System.out.println(PageSource);
        
        
    }
}
