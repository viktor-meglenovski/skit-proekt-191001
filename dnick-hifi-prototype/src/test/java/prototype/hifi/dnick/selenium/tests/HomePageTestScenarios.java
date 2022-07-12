package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HomePageTestScenarios {
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
    public HomePage getHomePage(){
        return HomePage.openHome(this.driver);
    }
    @Test
    public void testHomePageRetrieve(){
        Assertions.assertTrue(HomePage.checkIfHomePage(this.driver,getHomePage()));
    }
    @Test
    public void testHomePageNavigationToLoginAndRegister(){
        //get homepage
        HomePage homePage=getHomePage();

        //navigate from homepage to login
        LoginPage loginPage=HomePage.navigateToLogin(this.driver,homePage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));

        //navigate from login to homepage via navbar
        homePage= LoginPage.navigationBarToHomePage(this.driver,loginPage);
        Assertions.assertTrue(HomePage.checkIfHomePage(this.driver,homePage));

        //navigate from homepage to register
        RegisterPage registerPage= HomePage.navigateToRegister(this.driver,homePage);
        Assertions.assertTrue(RegisterPage.checkIfRegisterPage(this.driver,registerPage));
    }
    @Test
    public void testNavigationBarRegisterAndLoginButtons(){
        //get homepage
        HomePage homePage=getHomePage();

        //navigate from homepage to login via navbar
        LoginPage loginPage=HomePage.navigationBarToLogin(this.driver,homePage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));

        //navigate from login to register via navbar
        RegisterPage registerPage=LoginPage.navigationBarToRegister(this.driver,loginPage);
        Assertions.assertTrue(RegisterPage.checkIfRegisterPage(this.driver,registerPage));
    }
    @Test
    public void testHomePageNavigationToMyProfileAndLogout(){
        //get login
        LoginPage loginPage=LoginPage.openLogin(this.driver);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));

        //login with an existing user -> result is MyProfile page
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));

        //navigate from MyProfile to homepage via navbar
        HomePage homePage=MyProfilePage.navigationBarToHomePage(this.driver,myProfilePage);
        Assertions.assertTrue(HomePage.checkIfHomePage(this.driver,homePage));

        //navigate from homepage to MyProfile
        myProfilePage=HomePage.navigateToMyProfile(this.driver,homePage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));

        //navigate from MyProfile to homepage via navbar
        homePage=MyProfilePage.navigationBarToHomePage(this.driver,myProfilePage);
        Assertions.assertTrue(HomePage.checkIfHomePage(this.driver,homePage));

        //logout
        loginPage=HomePage.logout(this.driver,homePage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));
    }
    @Test
    public void testNavigationBarMyProfileAndLogoutButtons(){
        //get login
        LoginPage loginPage=LoginPage.openLogin(this.driver);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));

        //login with an existing user -> result is MyProfile page
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));

        //navigate from MyProfile to homepage via navbar
        HomePage homePage=MyProfilePage.navigationBarToHomePage(this.driver,myProfilePage);
        Assertions.assertTrue(HomePage.checkIfHomePage(this.driver,homePage));

        //navigate from homepage to MyProfile via navbar
        myProfilePage=HomePage.navigationBarToMyProfile(this.driver,homePage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));

        //logout
        loginPage=HomePage.navigationBarLogout(this.driver,homePage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));
    }


}
