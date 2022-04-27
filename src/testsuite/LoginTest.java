package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    @Before
    public void openBrowserLoginTest() {
        openBrowser("http://the-internet.herokuapp.com/login");
    }

    @Test
    public void login(){
        sendTextToElement(By.name("username"),"tomsmith");
        sendTextToElement(By.name("password"),"SuperSecretPassword!");
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));
        verifyText("Message","Secure Area", By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));
    }
    @Test
    public void verifyTheUsernameErrorMessage(){
        sendTextToElement(By.name("username"),"tomsmith1");
        sendTextToElement(By.name("password"),"SuperSecretPassword!");
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));

        String expectedMessage = "Your username is invalid!";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']")).substring(0,25);
        Assert.assertEquals(expectedMessage,actualMessage);
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        sendTextToElement(By.name("username"),"tomsmith");
        sendTextToElement(By.name("password"),"SuperSecretPassword");
        clickOnElement(By.xpath("//i[contains(text(),'Login')]"));


        String expectedMessage = "Your password is invalid!";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']")).substring(0,25);
        Assert.assertEquals(expectedMessage,actualMessage);
    }


    @After
    public void closeBrowserLoginTest(){
       closeBrowser();
    }
}
