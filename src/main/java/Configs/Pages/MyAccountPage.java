package Configs.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage {
WebDriver driver;
    @FindBy(linkText = "Edit your account information")
   private WebElement editYourAccoutnInfo;

    public MyAccountPage(WebDriver driver){
        this.driver=driver;
    }

    public boolean getDisplayStatusOfEditYourInformation(){
        boolean displayedStatus=editYourAccoutnInfo.isDisplayed();
        return displayedStatus;
    }
}
