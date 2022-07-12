package prototype.hifi.dnick.selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends AbstractPage{
    private WebElement logout;
    private WebElement learn;
    private WebElement test;
    private WebElement resultsAndBadges;
    private WebElement numberOfTests;
    private WebElement numberOfBadges;
    private WebElement highestScore;
    public MyProfilePage(WebDriver driver){
        super(driver);
    }
    public static boolean checkIfMyProfilePage(WebDriver driver, MyProfilePage myProfilePage){
        return myProfilePage.title.getText().equals("Информации за твојот профил");
    }
    public static LoginPage logout(WebDriver driver,MyProfilePage myProfilePage){
        myProfilePage.logout.click();
        return PageFactory.initElements(driver, LoginPage.class);
    }
    public static LearnPage navigateToLearnPage(WebDriver driver,MyProfilePage myProfilePage){
        myProfilePage.learn.click();
        return PageFactory.initElements(driver, LearnPage.class);
    }
    public static TestPage navigateToTestPage(WebDriver driver,MyProfilePage myProfilePage){
        myProfilePage.test.click();
        return PageFactory.initElements(driver, TestPage.class);
    }
    public static ResultsAndBadgesPage navigateToResultsAndBadgesPage(WebDriver driver,MyProfilePage myProfilePage){
        myProfilePage.resultsAndBadges.click();
        return PageFactory.initElements(driver, ResultsAndBadgesPage.class);
    }
    public void checkNumberOfTestsAndBadges(int tests, int badges){
        Assertions.assertEquals(Integer.valueOf(numberOfTests.getText()),tests);
        Assertions.assertEquals(numberOfBadges.getText(),badges+"/5");
    }
    public void checkPoints(int points){
        Assertions.assertEquals(Integer.valueOf(highestScore.getText()),points);
    }
}
