package de.phonebook.test;

import de.phonebook.core.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends TestBase {
    //before- login, add contact

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

        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(
                new de.phonebook.model.Contact().setName("Oliver")
                        .setLastName("Kan")
                        .setPhone("1234567890")
                        .setEmail("Oliver@test.com")
                        .setAddress("Wien")
                        .setDescription("QA"));
        app.getContact().clickOnSaveButton();
    }

    @Test
    public void removeContactTest(){
        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        app.getContact().pause(1000);
        int sizeAfter = app.getContact().sizeOfContacts();
        Assert.assertEquals(sizeAfter, sizeBefore-1);
    }
    //click on card
    //click on remove button
    //assert by size
}
