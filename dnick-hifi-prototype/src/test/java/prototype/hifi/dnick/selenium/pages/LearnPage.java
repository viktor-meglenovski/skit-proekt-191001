package prototype.hifi.dnick.selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LearnPage extends AbstractPage{
    private WebElement back;
    private WebElement startTest;
    private WebElement resultsAndBadges;
    public LearnPage(WebDriver driver){
        super(driver);
    }
    public static boolean checkIfLearnPage(WebDriver driver, LearnPage learnPage){
        return learnPage.title.getText().equals("Научи повеќе");
    }
    public static MyProfilePage clickBackButton(WebDriver driver, LearnPage learnPage){
        learnPage.back.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    public static TestPage clickStartTest(WebDriver driver, LearnPage learnPage){
        learnPage.startTest.click();
        return PageFactory.initElements(driver, TestPage.class);
    }
    public static ResultsAndBadgesPage clickResultsAndBadges(WebDriver driver, LearnPage learnPage){
        learnPage.resultsAndBadges.click();
        return PageFactory.initElements(driver, ResultsAndBadgesPage.class);
    }
    public static TopicPage clickDetailsButton(WebDriver driver, LearnPage learnPage){
        WebElement detailsButton=learnPage.driver.findElements(By.className("detailsButtons")).get(0);
        detailsButton.click();
        return PageFactory.initElements(driver,TopicPage.class);
    }
    public void checkButtons(int finishedButtons, int unfinishedButtons){
        List<WebElement> buttons=driver.findElements(By.className("status"));
        Assertions.assertEquals(finishedButtons, buttons.stream().filter(x->!x.getText().equals("X")).count());
        Assertions.assertEquals(unfinishedButtons, buttons.stream().filter(x->x.getText().equals("X")).count());
    }
    public static LearnPage markTopicAsCompletedSuccessfully(WebDriver driver, LearnPage learnPage) throws InterruptedException {
        WebElement button=learnPage.driver.findElements(By.className("status")).stream().filter(x->x.getText().equals("X")).collect(Collectors.toList()).get(0);
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", button);
        Thread.sleep(2000);
        WebElement yes=driver.findElement(By.className("yes"));
        jse2.executeScript("arguments[0].click()", yes);
        Thread.sleep(2000);
        return learnPage;
    }
    public static LearnPage markTopicAsCompletedUnsuccessfully(WebDriver driver, LearnPage learnPage) throws InterruptedException {
        WebElement button=learnPage.driver.findElements(By.className("status")).stream().filter(x->x.getText().equals("X")).collect(Collectors.toList()).get(0);
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", button);
        Thread.sleep(2000);
        WebElement no=learnPage.driver.findElement(By.className("no"));
        jse2.executeScript("arguments[0].click()", no);
        Thread.sleep(2000);
        return learnPage;
    }
}
