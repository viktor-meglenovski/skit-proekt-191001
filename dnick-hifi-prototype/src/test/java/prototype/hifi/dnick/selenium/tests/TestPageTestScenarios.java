package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.LoginPage;
import prototype.hifi.dnick.selenium.pages.MyProfilePage;
import prototype.hifi.dnick.selenium.pages.ResultsAndBadgesPage;
import prototype.hifi.dnick.selenium.pages.TestPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestPageTestScenarios {
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
    public TestPage loginAndStartTest(){
        LoginPage loginPage= LoginPage.openLogin(this.driver);
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        TestPage testPage=MyProfilePage.navigateToTestPage(this.driver,myProfilePage);
        return testPage;
    }
    @Test
    public void testCancelTestingSuccessful() throws InterruptedException {
        //login and start test
        TestPage testPage=loginAndStartTest();

        //cancel the test successfully
        MyProfilePage myProfilePage=TestPage.cancelTestSuccessful(this.driver,testPage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testCancelTestingUnsuccessful() throws InterruptedException {
        //login and start test
        TestPage testPage=loginAndStartTest();

        //cancel the test unsuccessfully
        testPage=TestPage.cancelTestUnsuccessful(this.driver,testPage);
        Assertions.assertTrue(TestPage.checkIfTestPage(this.driver,testPage));
    }
    @Test
    public void testTryToSubmitWithoutAllAnswers() throws InterruptedException {
        //login and start test
        TestPage testPage=loginAndStartTest();

        testPage=TestPage.tryToSubmitWithoutAllAnswers(this.driver,testPage);
        Assertions.assertTrue(TestPage.checkIfTestPage(this.driver,testPage));
    }
    @Test
    public void testCompleteTestAndGoToMyProfile() throws InterruptedException {
        //login and start test
        TestPage testPage=loginAndStartTest();

        MyProfilePage myProfilePage=TestPage.completeTestAndGoToMyProfile(this.driver,testPage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
    @Test
    public void testCompleteTestAndGoToBadges() throws InterruptedException {
        //login and start test
        TestPage testPage=loginAndStartTest();

        ResultsAndBadgesPage resultsAndBadgesPage=TestPage.completeTestAndGoToBadges(this.driver,testPage);
        Assertions.assertTrue(ResultsAndBadgesPage.checkIfResultsAndBadgesPage(this.driver,resultsAndBadgesPage));
    }
}
