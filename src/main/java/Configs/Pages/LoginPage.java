package Configs.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

   //objects
    @FindBy(id = "input-email")
    private WebElement emailFeild;

   @FindBy(id = "input-password")
    private WebElement passwordFeild;

   @FindBy(xpath = "//input[@value='Login']")
   private WebElement loginButton;

   @FindBy(xpath = "//div[contains(@class, 'alert alert-danger alert-dismissible')]")
   private WebElement emailPasswordNotMatchingWarning;

   public LoginPage(WebDriver driver){
       this.driver=driver;
       PageFactory.initElements(driver, this);
   }

   //Actions

    public void enterEmailAddress(String emailText){
       emailFeild.sendKeys();
    }
    public void enterPasswordFeild(String passwordText){
       passwordFeild.sendKeys();
    }
    public void clickOnLoginButton(){
       loginButton.click();
    }
    public String retriveEmailPasswordNotMatchingWarning(){
       String warningText=emailPasswordNotMatchingWarning.getText();
       return warningText;
    }

}
