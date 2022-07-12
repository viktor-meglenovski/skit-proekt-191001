package prototype.hifi.dnick.selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.stream.Collectors;

public class TopicPage extends AbstractPage{
    private WebElement back;
    private WebElement status;
    private WebElement badgeStatus;
    public TopicPage(WebDriver driver){
        super(driver);
    }
    public static boolean checkIfTopicPage(WebDriver driver, TopicPage topicPage){
        return topicPage.title.getText().equals("Крварење");
    }
    public static LearnPage clickBackButton(WebDriver driver, TopicPage topicPage){
        topicPage.back.click();
        return PageFactory.initElements(driver, LearnPage.class);
    }
    public void checkStatus(String badge, String button){
        Assertions.assertEquals(badge,badgeStatus.getText());
        Assertions.assertEquals(button,status.getText());
    }
    public static TopicPage changeStatusUnsuccessfully(WebDriver driver, TopicPage topicPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", topicPage.status);
        Thread.sleep(2000);
        WebElement no=topicPage.driver.findElement(By.className("no"));
        jse2.executeScript("arguments[0].click()", no);
        Thread.sleep(2000);
        return topicPage;
    }
    public static LearnPage changeStatusSuccessfully(WebDriver driver, TopicPage topicPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", topicPage.status);
        Thread.sleep(2000);
        WebElement ye=topicPage.driver.findElement(By.className("yes"));
        jse2.executeScript("arguments[0].click()", ye);
        Thread.sleep(2000);
        return PageFactory.initElements(driver, LearnPage.class);
    }
}
