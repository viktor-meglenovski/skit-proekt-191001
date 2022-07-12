package prototype.hifi.dnick.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends AbstractPage{
    private WebElement logout;
    private WebElement learn;
    private WebElement test;
    private WebElement resultsAndBadges;
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
}
