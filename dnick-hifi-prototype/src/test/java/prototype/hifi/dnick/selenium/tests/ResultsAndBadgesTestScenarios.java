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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ResultsAndBadgesTestScenarios {
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
    public ResultsAndBadgesPage loginAndGoToResultsAndBadgesPage(){
        LoginPage loginPage= LoginPage.openLogin(this.driver);
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        ResultsAndBadgesPage resultsAndBadgesPage=MyProfilePage.navigateToResultsAndBadgesPage(this.driver,myProfilePage);
        return resultsAndBadgesPage;
    }
    @Test
    public void testBadgeStatuses(){
        //login and go to page
        ResultsAndBadgesPage resultsAndBadgesPage=loginAndGoToResultsAndBadgesPage();

        resultsAndBadgesPage.checkBadges(0,5);
    }
    @Test
    public void testBackButton(){
        //login and go to page
        ResultsAndBadgesPage resultsAndBadgesPage=loginAndGoToResultsAndBadgesPage();

        //click on back button
        MyProfilePage myProfilePage=ResultsAndBadgesPage.clickBackButton(this.driver,resultsAndBadgesPage);
        Assertions.assertTrue(MyProfilePage.checkIfMyProfilePage(this.driver,myProfilePage));
    }
}
