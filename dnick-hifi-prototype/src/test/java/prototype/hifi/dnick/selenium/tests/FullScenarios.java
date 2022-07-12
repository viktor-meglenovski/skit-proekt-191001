package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FullScenarios {
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
    @Test
    public void testScenario() throws InterruptedException {
        //get register page and register a new user
        RegisterPage registerPage=RegisterPage.openRegister(this.driver);
        LoginPage loginPage=RegisterPage.doRegister(this.driver,registerPage,"NewUser","test","test","Name","Surname");

        //login with the new user
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"NewUser","test");

        //check stats
        myProfilePage.checkPoints(0);
        myProfilePage.checkNumberOfTestsAndBadges(0,0);

        //go to learn page
        LearnPage learnPage=MyProfilePage.navigateToLearnPage(this.driver,myProfilePage);
        learnPage.checkButtons(0,8);

        //check status and mark it as completed
        TopicPage topicPage=LearnPage.clickDetailsButton(this.driver,learnPage);
        topicPage.checkStatus("Незавршено","Маркирај како завршено");
        learnPage=TopicPage.changeStatusSuccessfully(this.driver,topicPage);

        //check status again
        learnPage.checkButtons(1,7);

        //go back to my profile
        myProfilePage=LearnPage.navigationBarToMyProfile(this.driver,learnPage);

        //start a test and end it successfully
        TestPage testPage=MyProfilePage.navigateToTestPage(this.driver,myProfilePage);
        myProfilePage=TestPage.completeTestAndGoToMyProfile(this.driver,testPage);

        //check stats again
        myProfilePage.checkNumberOfTestsAndBadges(1,1);

        //go to badges and results
        ResultsAndBadgesPage resultsAndBadgesPage=MyProfilePage.navigateToResultsAndBadgesPage(this.driver,myProfilePage);
        resultsAndBadgesPage.checkBadges(1,4);

        //go back to my profile
        myProfilePage=ResultsAndBadgesPage.clickBackButton(this.driver, resultsAndBadgesPage);

        //logout
        loginPage=MyProfilePage.logout(this.driver,myProfilePage);
    }

}
