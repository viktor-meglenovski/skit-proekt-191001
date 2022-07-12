package prototype.hifi.dnick.selenium.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import prototype.hifi.dnick.selenium.pages.LearnPage;
import prototype.hifi.dnick.selenium.pages.LoginPage;
import prototype.hifi.dnick.selenium.pages.MyProfilePage;
import prototype.hifi.dnick.selenium.pages.TopicPage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TopicPageTestScenarios {
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
    public TopicPage loginAndGoToTopicPage(){
        LoginPage loginPage= LoginPage.openLogin(this.driver);
        MyProfilePage myProfilePage=LoginPage.doLogin(this.driver,loginPage,"ViktorM","test");
        LearnPage learnPage=MyProfilePage.navigateToLearnPage(this.driver,myProfilePage);
        TopicPage topicPage=LearnPage.clickDetailsButton(this.driver,learnPage);
        return topicPage;
    }
    @Test
    public void testBackButton(){
        //login and navigate to topic page
        TopicPage topicPage=loginAndGoToTopicPage();

        //click back
        LearnPage learnPage=TopicPage.clickBackButton(this.driver,topicPage);
        Assertions.assertTrue(LearnPage.checkIfLearnPage(this.driver,learnPage));
    }
    @Test
    public void testInitialStatus(){
        //login and navigate to topic page
        TopicPage topicPage=loginAndGoToTopicPage();

        //check status
        topicPage.checkStatus("Незавршено","Маркирај како завршено");
    }
    @Test
    public void testChangeStatus() throws InterruptedException {
        //login and navigate to topic page
        TopicPage topicPage=loginAndGoToTopicPage();
        topicPage.checkStatus("Незавршено","Маркирај како завршено");

        topicPage=TopicPage.changeStatusUnsuccessfully(this.driver,topicPage);
        topicPage.checkStatus("Незавршено","Маркирај како завршено");

        LearnPage learnPage=TopicPage.changeStatusSuccessfully(this.driver,topicPage);
        topicPage=LearnPage.clickDetailsButton(this.driver,learnPage);
        topicPage.checkStatus("Завршено","Маркирај како незавршено");
    }
}
