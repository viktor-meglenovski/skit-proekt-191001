package prototype.hifi.dnick.selenium.tests;

import lombok.extern.java.Log;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MyProfilePageTestScenarios {
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
    public MyProfilePage loginAndReturnMyProfilePage(){
        LoginPage loginPage= LoginPage.openLogin(this.driver);
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        return myProfilePage;
    }
    @Test
    public void testLogout(){
        //login
        MyProfilePage myProfilePage=loginAndReturnMyProfilePage();

        //logout
        LoginPage loginPage=MyProfilePage.logout(this.driver,myProfilePage);
        Assertions.assertTrue(LoginPage.checkIfLoginPage(this.driver,loginPage));
    }
    @Test
    public void testNavigateToLearnPage(){
        //login
        MyProfilePage myProfilePage=loginAndReturnMyProfilePage();

        //go to learn page
        LearnPage learnPage=MyProfilePage.navigateToLearnPage(this.driver,myProfilePage);
        Assertions.assertTrue(LearnPage.checkIfLearnPage(this.driver,learnPage));
    }
    @Test
    public void testNavigateToTestPage(){
        //login
        MyProfilePage myProfilePage=loginAndReturnMyProfilePage();

        //go to test page
        TestPage testPage=MyProfilePage.navigateToTestPage(this.driver,myProfilePage);
        Assertions.assertTrue(TestPage.checkIfTestPage(this.driver,testPage));
    }
    @Test
    public void testNavigateToResultsAndBadgesPage(){
        //login
        MyProfilePage myProfilePage=loginAndReturnMyProfilePage();

        //go to results and badges
        ResultsAndBadgesPage resultsAndBadgesPage=MyProfilePage.navigateToResultsAndBadgesPage(this.driver,myProfilePage);
        Assertions.assertTrue(ResultsAndBadgesPage.checkIfResultsAndBadgesPage(this.driver,resultsAndBadgesPage));
    }
}
