package de.phonebook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTest extends TestBase{

    //before - login
    @BeforeMethod
    public void precondition(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create@test.account");
        type(By.name("password"), "As11234&");
        click(By.name("login"));
    }

    @Test
    public void addContactPositiveTest(){
        //click on ADD link
        click(By.cssSelector("[href='/add']"));
        //driver.findElement(By.cssSelector("[href='/add']"));
        //enter name
        type(By.xpath("//input[1]"), "Oliver");
        //enter lastname
        type(By.xpath("//input[2]"), "Kan");
        //enter phone
        type(By.xpath("//input[3]"), "1234567890");
        //enter email
        type(By.xpath("//input[4]"), "Oliver@test.com");
        //enter address
        type(By.xpath("//input[5]"), "Wien");
        //enter description
        type(By.xpath("//input[6]"), "QA");
        //click button SAVE
        click(By.cssSelector(".add_form__2rsm2 button"));
        Assert.assertTrue(verifyByMane("Oliver"));

    }

    public boolean verifyByMane(String text) {
        List<WebElement> contacts = driver.findElements(By.cssSelector("h2"));
        for(WebElement contact: contacts){
            if(contact.getText().contains(text))
                return true;
        }
        return false;
    }

    @AfterMethod
    public void postConditions(){
        //click on card
        click(By.cssSelector(".contact-item_card__2SOIM"));
        //click on Remove button
        click(By.xpath("//button[.='Remove']"));
    }

    //enter info in fields

}
