package de.phonebook.core;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {
    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator){
       return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        //focus: put cursor in a field
        click(locator);
        //clear
        driver.findElement(locator).clear();
        //send: fill the field with text
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public boolean isAlertPresent() {
       Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20))
                       .until(ExpectedConditions.alertIsPresent());
       if ( alert == null) {
           return false;
       } else {
           driver.switchTo().alert().accept();//click on OK button in alert
           return true;
       }
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptAlert(){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public String getFieldValue(By locator){
        return driver.findElement(locator).getAttribute("value");
    }

    public String takeScreenshot(){
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);//temporary
        File screen = new File("screenshots/screen-" + System.currentTimeMillis() + ".png");//
        try {
            Files.copy(tmp,screen); //com.google.common.io
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screen.getAbsolutePath();
    }

}
