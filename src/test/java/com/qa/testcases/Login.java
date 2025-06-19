package com.qa.testcases;

import COnfigs.Pages.HomePage;
import COnfigs.Pages.LoginPage;
import COnfigs.Pages.MyAccountPage;
import Utils.Utilities;
import com.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Date;

import static Utils.Utilities.generateEmailWithTimeStamp;

public class Login extends BaseClass {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
loadPropertiesFile();
        driver=initializeBrowserApplicationtoUrl(prop.getProperty("browserName"));
        HomePage homePage=new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.clickOnLoginOptions();
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority = 2)
    public void verifyLoginPageWithInValidCreds(){

        LoginPage loginpage=new LoginPage(driver);
        loginpage.enterEmailAddress(generateEmailWithTimeStamp());

        loginpage.enterPasswordFeild(testDataProp.getProperty("InvalidPassword"));
        loginpage.clickOnLoginButton();



        String actualWaringMessage =loginpage.retriveEmailPasswordNotMatchingWarning();
        String expectedWaringingMessage=testDataProp.getProperty("emailPasswordNoMatchingWarning");

        Assert.assertEquals(actualWaringMessage,expectedWaringingMessage);



    }
    @Test(priority = 1, dataProvider="validCredentialSupplier")
    public void verifyLoginWithValidCreds(String email, String password){

        LoginPage loginpage=new LoginPage(driver);
        loginpage.enterEmailAddress(email);

        loginpage.enterPasswordFeild(password);
        loginpage.clickOnLoginButton();

//        String actualWaringMessage =driver.findElement(By.xpath("//div[contains(@class, 'alert alert-danger alert-dismissible')]")).getText();
//        String expectedWaringingMessage=testDataProp.getProperty("emailPasswordNoMatchingWarning");

//        Assert.assertEquals(actualWaringMessage,expectedWaringingMessage);
        MyAccountPage myAccountPage=new MyAccountPage(driver);

        Assert.assertTrue(myAccountPage.getDisplayStatusOfEditYourInformation());
    }
    @DataProvider(name="validCredentialSupplier")
    public Object[][] supplyTestData(){
        Object[][] data=Utilities.getTestDataFromExcel("login");


                /*for giving data manually
                {{"ashiva1@gmail.com", "12345"},
                {"ashiva2@gmail.com", "12345"},
                {"ashiva3@gmail.com", "12345"}};*/
        return data;
    }
    @Test(priority = 3)
    public void VerifyLoginWithInvalidEmailValidPassword(){
        LoginPage loginpage=new LoginPage(driver);
        loginpage.enterEmailAddress(generateEmailWithTimeStamp());

        loginpage.enterPasswordFeild(prop.getProperty("ValidPassword"));
        loginpage.clickOnLoginButton();



        String actualWaringMessage =loginpage.retriveEmailPasswordNotMatchingWarning();
        String expectedWaringingMessage=testDataProp.getProperty("emailPasswordNoMatchingWarning");

        Assert.assertEquals(actualWaringMessage,expectedWaringingMessage);

        driver.quit();

    }

    @Test(priority = 4)
    public void VerifyLoginWithValidEmailInValidPassword(){

        LoginPage loginpage=new LoginPage(driver);
        loginpage.enterEmailAddress(generateEmailWithTimeStamp());

        loginpage.enterPasswordFeild(testDataProp.getProperty("InvalidPassword"));
        loginpage.clickOnLoginButton();


        String actualWaringMessage =loginpage.retriveEmailPasswordNotMatchingWarning();
        String expectedWaringingMessage=testDataProp.getProperty("emailPasswordNoMatchingWarning");

        Assert.assertEquals(actualWaringMessage,expectedWaringingMessage);



    }
    @Test(priority = 5)
    public void VerifyLoginWithoutProvidingDetails(){

        LoginPage loginpage=new LoginPage(driver);
        loginpage.clickOnLoginButton();
//
        String actualWaringMessage =loginpage.retriveEmailPasswordNotMatchingWarning();
        String expectedWaringingMessage=testDataProp.getProperty("emailPasswordNoMatchingWarning");

        Assert.assertEquals(actualWaringMessage,expectedWaringingMessage);




    }


}
