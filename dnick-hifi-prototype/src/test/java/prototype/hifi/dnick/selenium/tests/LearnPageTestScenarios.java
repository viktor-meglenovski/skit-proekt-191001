package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LearnPageTestScenarios {
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
    public LearnPage loginAndGoToLearnPage(){
        LoginPage loginPage= LoginPage.openLogin(this.driver);
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        LearnPage learnPage=MyProfilePage.navigateToLearnPage(this.driver,myProfilePage);
        return learnPage;
    }
    @Test
    public void testBackButton(){
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();

        //click on back button
        MyProfilePage myProfilePage=LearnPage.clickBackButton(this.driver,learnPage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testStartTestButton(){
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();

        //click start test button
        TestPage testPage=LearnPage.clickStartTest(this.driver,learnPage);
        Assertions.assertTrue(TestPage.checkIfTestPage(this.driver,testPage));
    }
    @Test
    public void testResultsAndBadgesButton(){
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();

        //click results and badges button
        ResultsAndBadgesPage resultsAndBadgesPage=LearnPage.clickResultsAndBadges(this.driver,learnPage);
        Assertions.assertTrue(ResultsAndBadgesPage.checkIfResultsAndBadgesPage(this.driver,resultsAndBadgesPage));
    }
    @Test
    public void testClickDetailsButton(){
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();

        //click details button
        TopicPage topicPage=LearnPage.clickDetailsButton(this.driver,learnPage);
        Assertions.assertTrue(TopicPage.checkIfTopicPage(this.driver,topicPage));
    }
    @Test
    public void testInitialButtons(){
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();

        //check initial buttons
        learnPage.checkButtons(0,8);
    }
    @Test
    public void testMarkAsCompletedSuccessfully() throws InterruptedException {
        //login and go to page
        LearnPage learnPage=loginAndGoToLearnPage();
        learnPage.checkButtons(0,8);

        //mark topic as completed but cancel the operation
        learnPage=LearnPage.markTopicAsCompletedUnsuccessfully(this.driver,learnPage);
        learnPage.checkButtons(0,8);

        //mark topic as completed successfully
        learnPage=LearnPage.markTopicAsCompletedSuccessfully(this.driver,learnPage);
        learnPage.checkButtons(1,7);
    }

}
