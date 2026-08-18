package de.phonebook.fw;

import de.phonebook.core.BaseHelper;
import de.phonebook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnSaveButton() {
        click(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void fillAddContactForm(Contact contact) {
        type(By.xpath("//input[1]"), contact.getName());
        //enter lastname
        type(By.xpath("//input[2]"), contact.getLastName());
        //enter phone
        type(By.xpath("//input[3]"), contact.getPhone());
        //enter email
        type(By.xpath("//input[4]"), contact.getEmail());
        //enter address
        type(By.xpath("//input[5]"), contact.getAddress());
        //enter description
        type(By.xpath("//input[6]"), contact.getDescription());
    }

    public void clickOnAddLink() {
        click(By.cssSelector("[href='/add']"));
    }

    public boolean verifyByName(String text) {
        if (verifyText(text, By.cssSelector("h2"))) return true;
        return false;
    }

    public boolean verifyText(String text, By locator) {
        List<WebElement> contacts = driver.findElements(locator);
        for(WebElement contact: contacts){
            if(contact.getText().contains(text))
                return true;
        }
        return false;
    }

    public void removeContact() {
        //click on card
        click(By.cssSelector(".contact-item_card__2SOIM"));
        //click on Remove button
        click(By.xpath("//button[.='Remove']"));
    }

    public int sizeOfContacts() {
        if(isElementPresent(By.cssSelector(".add_form__2rsm2 button"))){
          return driver.findElements(By.cssSelector(".add_form__2rsm2 button")).size();
        }
        return 0;
    }

    public boolean verifyByPhone(String text) {
        if (verifyText(text, By.cssSelector("h3"))) return true;

        return false;
    }
}
