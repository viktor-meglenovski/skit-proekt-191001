package prototype.hifi.dnick.selenium.pages;

import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends AbstractPage{
    private WebElement username;
    private WebElement password;
    private WebElement repeatedPassword;
    private WebElement name;
    private WebElement surname;
    private WebElement submit;
    private WebElement toLogin;
    private WebElement error;
    public RegisterPage(WebDriver driver){
        super(driver);
    }
    public static RegisterPage openRegister(WebDriver driver) {
        get(driver, "/register");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, RegisterPage.class);
    }
    public static boolean checkIfRegisterPage(WebDriver driver, RegisterPage registerPage){
        return registerPage.title.getText().equals("Регистрација");
    }
    public static LoginPage navigateToLogin(WebDriver driver, RegisterPage registerPage){
        registerPage.toLogin.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static LoginPage doRegister(WebDriver driver, RegisterPage registerPage, String username, String password, String repeatedPassword, String name, String surname) {
        registerPage.username.sendKeys(username);
        registerPage.password.sendKeys(password);
        registerPage.repeatedPassword.sendKeys(repeatedPassword);
        registerPage.name.sendKeys(name);
        registerPage.surname.sendKeys(surname);
        registerPage.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static boolean checkIfErrorIs(WebDriver driver, RegisterPage registerPage, String text){
        return registerPage.error.getText().equals(text);
    }
}
