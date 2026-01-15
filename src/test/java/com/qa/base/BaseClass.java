package com.qa.base;

import Utils.Utilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\Configs\\Config.properties");
//        String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
        try{
            FileInputStream fis=new FileInputStream(propFile);
            prop.load(fis);
        }catch (Throwable e){
            e.printStackTrace();
        }
        testDataProp=new Properties();
        File propFile1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\Configs\\TestData.properties");
        try{
            FileInputStream fis1=new FileInputStream(propFile1);
            testDataProp.load(fis1);
        }catch (Throwable e){
            e.printStackTrace();
        }


    }
 public WebDriver initializeBrowserApplicationtoUrl(String browserName){
     loadPropertiesFile();
//     if (browserName.equalsIgnoreCase("chrome")){
//        ChromeOptions options=new ChromeOptions();
//         if (browserName.contains("headless")){
//             options.addArguments("headless");
//         }
     String headlessvalue=System.getProperty("headless",prop.getProperty("headless"));
     boolean isHeadless=headlessvalue.equalsIgnoreCase("true");
     if (browserName.equalsIgnoreCase("chrome")){
         ChromeOptions options=new ChromeOptions();
         if (isHeadless){

             options.addArguments("--headless=new");
             options.addArguments("--window-size=1440,900");
             options.addArguments("--disable-gpu");
         }
         driver=new ChromeDriver(options);
//         driver.manage().window().setSize(new Dimension(1440,900));
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
