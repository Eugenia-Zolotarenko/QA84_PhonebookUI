package de.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase{
    //before- login, add contact

    @BeforeMethod
    public void precondition(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create@test.account");
        type(By.name("password"), "As11234&");
        click(By.name("login"));

        click(By.cssSelector("[href='/add']"));
        type(By.xpath("//input[1]"), "Oliver");
        type(By.xpath("//input[2]"), "Kan");
        type(By.xpath("//input[3]"), "1234567890");
        type(By.xpath("//input[4]"), "Oliver@test.com");
        type(By.xpath("//input[5]"), "Wien");
        type(By.xpath("//input[6]"), "QA");
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    @Test
    public void removeContactTest(){
        int sizeBefore = sizeOfContacts();
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[.='Remove']"));
        pause(1000);
        int sizeAfter = sizeOfContacts();
        Assert.assertEquals(sizeAfter, sizeBefore-1);
        //assert by size
    }
    public void  pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int sizeOfContacts() {
        if(isElementPresent(By.cssSelector(".add_form__2rsm2 button"))){
          return driver.findElements(By.cssSelector(".add_form__2rsm2 button")).size();
        }
        return 0;
    }


    //click on card
    //click on remove button
    //assert by size
}
