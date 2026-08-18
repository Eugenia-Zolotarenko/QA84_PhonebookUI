package de.phonebook.test;

import de.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

   @Test(enabled = false)
    public void newUserRegisterPositiveTest(){
       app.getUser().clickOnLoginLink();
       app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                               .setEmail(app.getUser().newEmail())
                               .setPassword("As11234&"));
       app.getUser().clickOnRegistrationButton();
       Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void existedUserNegativeTest(){
       //click on Login link
        app.getUser().clickOnLoginLink();
        //enters email and password
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword("As11234&"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //assert alert
       Assert.assertTrue(app.getUser().isAlertPresent());
   }

    @Test
    public void loginUserEmptyEmailNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword(""));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }



    @Test
    public void loginUserInvalidPasswordMoreThan15SymbolsNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword("Aa1234!qwertyui4"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void loginUserInvalidPasswordWithoutSpecialSymbolNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword("Aa1234567"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }
    @Test
    public void loginUserInvalidPasswordWithoutLowercaseLetterNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword("A1234567+"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void loginUserInvalidPasswordWithoutUppercaseLetterNegativeTest(){
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create1@test.account")
                            .setPassword("a1234567+"));
        app.getUser().clickOnRegistrationButton();
        Assert.assertTrue(app.getUser().isAlertPresent());
   }

    @Test
    public void existedUserEmailRemainNegativeTest(){
        //click on Login link
        app.getUser().clickOnLoginLink();
        //enters email and password
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                .setEmail("create1@test.account")
                .setPassword("As11234&"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        app.getUser().acceptAlert();

        Assert.assertEquals(app.getUser().emailFieldValue(), "create1@test.account");
    }
    @Test
    public void existedUserPasswordEmptyNegativeTest(){
        //click on Login link
        app.getUser().clickOnLoginLink();
        //enters email and password
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                .setEmail("create1@test.account")
                .setPassword("As11234&"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        app.getUser().acceptAlert();

        Assert.assertTrue(app.getUser().isPasswordFieldEmpty());
    }


}

