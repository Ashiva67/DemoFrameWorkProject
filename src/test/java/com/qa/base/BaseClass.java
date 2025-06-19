package com.qa.base;

import Utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    WebDriver driver;
    public Properties prop;
    public Properties testDataProp;
    public void loadPropertiesFile(){
        prop=new Properties();
        File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\COnfigs\\Config.properties");
        try{
            FileInputStream fis=new FileInputStream(propFile);
            prop.load(fis);
        }catch (Throwable e){
            e.printStackTrace();
        }
        testDataProp=new Properties();
        File propFile1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\COnfigs\\TestData.properties");
        try{
            FileInputStream fis1=new FileInputStream(propFile1);
            testDataProp.load(fis1);
        }catch (Throwable e){
            e.printStackTrace();
        }


    }
 public WebDriver initializeBrowserApplicationtoUrl(String browserName){


     if (browserName.equalsIgnoreCase("chrome")){
         driver=new ChromeDriver();

     }else if (browserName.equalsIgnoreCase("firefox")){
         driver =new FirefoxDriver();
     }else if (browserName.equalsIgnoreCase("edge")){
         driver=new EdgeDriver();
     }
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
     driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
     driver.get(prop.getProperty("url"));
     return driver;
 }
}
