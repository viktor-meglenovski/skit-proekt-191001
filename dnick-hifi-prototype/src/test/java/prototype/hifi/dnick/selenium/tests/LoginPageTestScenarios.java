package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.LoginPage;
import prototype.hifi.dnick.selenium.pages.MyProfilePage;
import prototype.hifi.dnick.selenium.pages.RegisterPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginPageTestScenarios {
    private ChromeDriver driver;
    @BeforeEach
    private void setup() {
        this.driver = new ChromeDriver();
        //initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }
    public LoginPage getLoginPage(){
        return LoginPage.openLogin(this.driver);
    }
    @Test
    public void testLoginPageRetrieve(){
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,getLoginPage()));
    }
    @Test
    public void testLoginWithNoArguments(){
        //get login
        LoginPage loginPage=getLoginPage();

        //attempt login with no arguments (or 1 missing argument) -> the page should not change
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"","");
        Assertions.assertFalse(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
        myProfilePage=LoginPage.doLogin(this.driver,loginPage,"test","");
        Assertions.assertFalse(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
        myProfilePage=LoginPage.doLogin(this.driver,loginPage,"","test");
        Assertions.assertFalse(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testSuccessfulLogin(){
        //get login
        LoginPage loginPage=getLoginPage();

        //attempt login with valid arguments
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testInvalidArguments(){
        //get login
        LoginPage loginPage=getLoginPage();

        //attempt login with invalid arguments
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"invalid","arguments");
        Assertions.assertFalse(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
        Assertions.assertTrue(LoginPage.checkIfErrorIs(this.driver,loginPage,"Невалидна комбинација на корисничко име и лозинка"));
    }
    @Test
    public void testNavigateToRegisterPage(){
        //get login
        LoginPage loginPage=getLoginPage();

        //click on button for registration
        RegisterPage registerPage=LoginPage.navigateToRegisterPage(this.driver,loginPage);
        Assertions.assertTrue(RegisterPage.checkIfRegisterPage(this.driver,registerPage));
    }
}
