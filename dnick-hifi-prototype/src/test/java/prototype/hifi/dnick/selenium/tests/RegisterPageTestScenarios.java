package prototype.hifi.dnick.selenium.tests;

import lombok.extern.java.Log;
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
public class RegisterPageTestScenarios {
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
    public RegisterPage getRegisterPage(){
        return RegisterPage.openRegister(this.driver);
    }
    @Test
    public void testRegisterPageRetrieve(){
        Assertions.assertTrue(RegisterPage.checkIfRegisterPage(this.driver,getRegisterPage()));
    }
    @Test
    public void testNavigateToLoginPage(){
        //get register page;
        RegisterPage registerPage=getRegisterPage();

        //click on already have an account button
        LoginPage loginPage=RegisterPage.navigateToLogin(this.driver,registerPage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));
    }
    @Test
    public void testRegistrationWithNoArguments(){
        //get register page;
        RegisterPage registerPage=getRegisterPage();

        //attempt registration with no arguments -> the page should not change
        LoginPage loginPage=RegisterPage.doRegister(this.driver,registerPage,"","","","","");
        Assertions.assertFalse(LoginPage.checkIfLoginPage(this.driver,loginPage));
    }
    @Test
    public void testSuccessfulRegistration(){
        //get register page;
        RegisterPage registerPage=getRegisterPage();

        //attempt registration with valid arguments
        LoginPage loginPage=RegisterPage.doRegister(this.driver,registerPage,"NewUser","test","test","New","User");
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));

        MyProfilePage myProfilePage= LoginPage.doLogin(this.driver,loginPage,"NewUser","test");
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testExistingUsernameFailure(){
        //get register page;
        RegisterPage registerPage=getRegisterPage();

        //attempt registration with invalid username
        LoginPage loginPage=RegisterPage.doRegister(this.driver,registerPage,"ViktorM","test","test","New","User");
        Assertions.assertFalse(LoginPage.checkIfLoginPage(this.driver,loginPage));
        Assertions.assertTrue(RegisterPage.checkIfErrorIs(this.driver,registerPage,"Корисничкото име: ViktorM веќе постои"));
    }
    @Test
    public void testPasswordsDoNotMatchFailure(){
        //get register page;
        RegisterPage registerPage=getRegisterPage();

        //attempt registration with invalid username
        LoginPage loginPage=RegisterPage.doRegister(this.driver,registerPage,"NewUser","test1","test2","New","User");
        Assertions.assertFalse(LoginPage.checkIfLoginPage(this.driver,loginPage));
        Assertions.assertTrue(RegisterPage.checkIfErrorIs(this.driver,registerPage,"Лозинките не се совпаѓаат"));
    }

}
