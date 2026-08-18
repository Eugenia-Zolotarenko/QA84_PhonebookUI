package de.phonebook.test;

import de.phonebook.core.TestBase;
import de.phonebook.model.Contact;
import de.phonebook.utils.MyDataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @Test(dataProvider = "addNewContactFromCsv", dataProviderClass = MyDataProviders.class) // added dataProvider in signature
    public void addContactPositiveTest(Contact contact){ //added Contact in signature
        app.getContact().clickOnAddLink();
        app.getContact().fillAddContactForm(contact);
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().verifyByPhone(contact.getPhone())); //change method ByName-> ByPhone
    }

    @AfterMethod
    public void postConditions(){
        app.getContact().removeContact();
    }
    //enter info in fields
}
