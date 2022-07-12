package prototype.hifi.dnick.selenium.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AbstractPage {
    protected WebDriver driver;
    protected WebElement navToMyProfile;
    protected WebElement navToLogout;
    protected WebElement navToRegister;
    protected WebElement navToLogin;
    protected WebElement navToHome;
    protected WebElement title;
    public AbstractPage(WebDriver driver){
        this.driver=driver;
    }
    static void get(WebDriver driver,String relativeUrl){
        String url=System.getProperty("geb.build.baseUrl", "http://localhost:8080")+relativeUrl;
        driver.get(url);
    }
    public static HomePage navigationBarToHomePage(WebDriver driver, AbstractPage page){
        page.navToHome.click();
        return PageFactory.initElements(driver, HomePage.class);
    }
    public static LoginPage navigationBarToLogin(WebDriver driver, AbstractPage page){
        page.navToLogin.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static RegisterPage navigationBarToRegister(WebDriver driver, AbstractPage page){
        page.navToRegister.click();
        return PageFactory.initElements(driver, RegisterPage.class);
    }
    public static LoginPage navigationBarLogout(WebDriver driver, AbstractPage page){
        page.navToLogout.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static MyProfilePage navigationBarToMyProfile(WebDriver driver, AbstractPage page){
        page.navToMyProfile.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
}
