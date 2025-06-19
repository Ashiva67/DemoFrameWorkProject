package COnfigs.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
WebDriver driver;

//Objects
    @FindBy (xpath = "//span[text()='My Account']")
     private WebElement MyAccountDropDown;

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    public HomePage(WebDriver driver){
       this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //Actions
    public void clickOnMyAccount(){
        MyAccountDropDown.click();
    }
    public void clickOnLoginOptions(){
        loginOption.click();
    }
    public void clickOnRegisterOption(){
        registerOption.click();
    }

}

