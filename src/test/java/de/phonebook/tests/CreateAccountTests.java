package de.phonebook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

   @Test
    public void newUserRegisterPositiveTest(){

       //click on Login link
       click(By.cssSelector("[href='/login']"));
       //enters email
       type(By.name("email"), newEmail());
       //enters password
       type(By.name("password"), "As11234&");
       //click on Registration button
       //click(By.cssSelector("button[name='registration']"));
       //assert SignOut button
       Assert.assertTrue(isElementPresent(By.xpath("//*[.='Sign Out']")));
    }

    @Test
    public void existedUserNegativeTest(){
       //click on Login link
        click(By.cssSelector("[href='/login']"));
        //enters email
        type(By.name("email"), "create@test.account");
        //enters password
        type(By.name("password"), "As11234&");
        //click on Registration button
         click(By.cssSelector("button[name='registration']"));
        //assert alert
       Assert.assertTrue(isAlertPresent());
   }

    @Test
    public void loginUserInvalidPasswordLessThan8SymbolsNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "Aa1234!");
        click(By.name("registration"));
        Assert.assertTrue(isAlertPresent());
    }
    @Test
    public void loginUserInvalidPasswordMoreThan15SymbolsNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "Aa1234!qwertyui4");
        click(By.name("registration"));
        Assert.assertTrue(isAlertPresent());
    }

    @Test
    public void loginUserInvalidPasswordWithoutSpecialSymbolNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "Aa1234567");
        click(By.name("registration"));
        Assert.assertTrue(isAlertPresent());
    }
    @Test
    public void loginUserInvalidPasswordWithoutLowercaseLetterNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "A1234567+");
        click(By.name("registration"));
        Assert.assertTrue(isAlertPresent());
    }

    @Test
    public void loginUserInvalidPasswordWithoutUppercaseLetterNegativeTest(){
        click(By.cssSelector("[href='/login']"));
        type(By.name("email"), "create1@test.account");
        type(By.name("password"), "a1234567+");
        click(By.name("registration"));
        Assert.assertTrue(isAlertPresent());
   }
}

