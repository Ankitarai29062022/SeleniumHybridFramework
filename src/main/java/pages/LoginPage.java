package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By searchengineInput = By.xpath("//textarea[@title='Search']");
    By passwordField = By.id("password");
    By searchBottonClick = By.xpath("(//input[@value='Google Search'])[2]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String searchthis) throws InterruptedException {
    
        driver.findElement(searchengineInput).sendKeys(searchthis);
        Thread.sleep(5000);
       // driver.findElement(passwordField).sendKeys(password);
        driver.findElement(searchengineInput).sendKeys(Keys.ENTER);
        
        String Capturetitle=driver.getTitle();
        String currentUrl=driver.getCurrentUrl();
        String PageSource=driver.getPageSource();
        
        Thread.sleep(5000);
        System.out.println(Capturetitle);
        System.out.println(currentUrl);
        System.out.println(PageSource);
        
        
    }
}
