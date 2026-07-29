package de.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @Test
    public void loginRegisteredUserPositiveTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create@test.account");
        type(By.name("password"), "As11234&");
        click(By.name("login"));
        Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }



    @Test
    public void loginUserNoEmailInDbNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "As11234&");
        click(By.name("login"));
        Assert.assertTrue(isAlertPresent());
    }

    @Test
    public void loginUserIncorrectPasswordNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create@test.account");
        type(By.name("password"), "As11234&1");
        click(By.name("login"));
        Assert.assertTrue(isAlertPresent());
    }

    @Test
    public void loginUserEmptyPasswordNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create@test.account");
        type(By.name("password"), "");
        click(By.name("login"));
        Assert.assertTrue(isAlertPresent());
    }

    @Test
    public void loginUserEmptyEmailNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "");
        type(By.name("password"), "As11234&1");
        click(By.name("login"));
        Assert.assertTrue(isAlertPresent());
    }



}
