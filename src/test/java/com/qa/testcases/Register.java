package com.qa.testcases;

import Configs.Pages.HomePage;
import com.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.Utilities.generateEmailWithTimeStamp;

public class Register extends BaseClass {

WebDriver driver;
    @BeforeMethod
    public void verifyRegister(){
        loadPropertiesFile();
          driver=initializeBrowserApplicationtoUrl(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.clickOnRegisterOption();

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 1)
    public void verifyRegisterwithValidCreds(){
        driver.findElement(By.id("input-firstname")).sendKeys(testDataProp.getProperty("FirstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(testDataProp.getProperty("LastName"));
        driver.findElement(By.id("input-email")).sendKeys(generateEmailWithTimeStamp());
        driver.findElement(By.id("input-telephone")).sendKeys(testDataProp.getProperty("Telephone"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

    }

    @Test(priority = 2)
    public void verifyRegisterwithDuplicateEmail(){
        driver.findElement(By.id("input-firstname")).sendKeys(testDataProp.getProperty("FirstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(testDataProp.getProperty("LastName"));
        driver.findElement(By.id("input-email")).sendKeys(testDataProp.getProperty("email"));
        driver.findElement(By.id("input-telephone")).sendKeys(testDataProp.getProperty("Telephone"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("ValidPassword"));
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
    }
@Test
    public void verifyRegisterWithOutFields(){
        driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

        String actualWarningMessage=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
        String ExpectedWarningMessage=testDataProp.getProperty("PrivacyPolicyWarningMessage");
        Assert.assertTrue(actualWarningMessage.equals(ExpectedWarningMessage));

        String actualFirstNameWarningMessage=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
        String ExpectedFirstNameWarningMessage=testDataProp.getProperty("FirstNameWarning");
        Assert.assertEquals(actualFirstNameWarningMessage,ExpectedFirstNameWarningMessage);

        String actualLastNameWarningMessage=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
        String ExpectedLastNameWarningMessage=testDataProp.getProperty("LastNameWarning");
        Assert.assertEquals(actualLastNameWarningMessage,ExpectedLastNameWarningMessage);

        String actualEmailWarningMessage=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
        String ExpectedEmailWarningMessage=testDataProp.getProperty("EmailWarning");
        Assert.assertEquals(actualEmailWarningMessage,ExpectedEmailWarningMessage);

        String actualTelephoneWarningMessage=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
        String ExpectedTelephoneWarningMessage=testDataProp.getProperty("TelephoneWarning");
        Assert.assertEquals(actualTelephoneWarningMessage,ExpectedTelephoneWarningMessage);

        String actualPasswordWarningMessage=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
        String ExpectedPasswordWarningMessage=testDataProp.getProperty("PasswordMatchWarning");
        Assert.assertEquals(actualPasswordWarningMessage,ExpectedPasswordWarningMessage);






    }
}
