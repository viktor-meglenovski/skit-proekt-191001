package prototype.hifi.dnick.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends AbstractPage{
    private WebElement username;
    private WebElement password;
    private WebElement submit;
    private WebElement toRegister;
    private WebElement error;

    public LoginPage(WebDriver driver){
        super(driver);
    }
    public static LoginPage openLogin(WebDriver driver) {
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static MyProfilePage doLogin(WebDriver driver, LoginPage loginPage, String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    public static RegisterPage navigateToRegisterPage(WebDriver driver,LoginPage loginPage){
        loginPage.toRegister.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }
    public static boolean checkIfLoginPage(WebDriver driver, LoginPage loginPage){
        return loginPage.title.getText().equals("Најави се");
    }
    public static boolean checkIfErrorIs(WebDriver driver, LoginPage loginPage, String text){
        return loginPage.error.getText().equals(text);
    }

}
