package prototype.hifi.dnick.selenium.pages;

import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractPage{
    private WebElement toLogin;
    private WebElement toRegister;
    private WebElement toLogout;
    private WebElement toMyProfile;
    @FindBy(css = "img[class=red_cross_image]")
    private List<WebElement> images;
    public HomePage(WebDriver driver){
        super(driver);
    }
    public static HomePage openHome(WebDriver driver) {
        get(driver, "/home");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, HomePage.class);
    }
    public static LoginPage navigateToLogin(WebDriver driver,HomePage homePage){
        homePage.toLogin.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static RegisterPage navigateToRegister(WebDriver driver,HomePage homePage){
        homePage.toRegister.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }
    public static LoginPage logout(WebDriver driver,HomePage homePage){
        homePage.toLogout.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static MyProfilePage navigateToMyProfile(WebDriver driver,HomePage homePage){
        homePage.toMyProfile.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    public static boolean checkIfHomePage(WebDriver driver,HomePage homePage){
        return homePage.images.size()==2;
    }
}
