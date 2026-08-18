package de.phonebook.test;

import de.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTest extends TestBase {

    //before - login
    @BeforeMethod
    public void precondition(){
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegisterForm(new de.phonebook.model.User()
                            .setEmail("create@test.account")
                            .setPassword("As11234&"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest(){       
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(
                new de.phonebook.model.Contact().setName("Oliver")
                             .setLastName("Kan")
                             .setPhone("1234567890")
                             .setEmail("Oliver@test.com")
                             .setAddress("Wien")
                             .setDescription("QA"));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().verifyByMane("Oliver"));

    }

    @AfterMethod
    public void postConditions(){
        app.getContact().removeContact();
    }

    //enter info in fields

}
