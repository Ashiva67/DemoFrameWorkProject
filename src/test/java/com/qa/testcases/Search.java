package com.qa.testcases;

import com.qa.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Search extends BaseClass {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        loadPropertiesFile();
        driver=initializeBrowserApplicationtoUrl(prop.getProperty("browserName"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(priority=1)
    public void verifySearchBarWithValidContent(){
        driver.findElement(By.xpath("//div[@class='col-sm-5']/div/input")).sendKeys(testDataProp.getProperty("ValidProduct"));
        driver.findElement(By.xpath("//button[starts-with(@class,'btn btn-default')]")).click();
        Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(), "Vallid product");

    }
    @Test(priority = 2)
    public void verifySearchWithInvalidContent(){
        driver.findElement(By.xpath("//div[@class='col-sm-5']/div/input")).sendKeys(testDataProp.getProperty("InvalidProduct"));
        driver.findElement(By.xpath("//button[starts-with(@class,'btn btn-default')]")).click();
        String actualMessage=driver.findElement(By.xpath("//p[normalize-space()='There is no product that matches the search criteria.']")).getText();

       Assert.assertEquals(actualMessage, "There is no product that matches the search criteria.", "No Product present in the search criteria");
    }
    @Test(priority = 3)
    public void verifySearchWithoutContent(){
//        driver.findElement(By.xpath("//div[@class='col-sm-5']/div/input")).sendKeys("");
        driver.findElement(By.xpath("//button[starts-with(@class,'btn btn-default')]")).click();
        String actualMessage=driver.findElement(By.xpath("//p[normalize-space()='There is no product that matches the search criteria.']")).getText();

        Assert.assertEquals(actualMessage, "There is no product that matches the search criteria.", "No Product present in the search criteria");
    }
}
