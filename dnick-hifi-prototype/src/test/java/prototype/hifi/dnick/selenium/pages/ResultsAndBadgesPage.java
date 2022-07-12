package prototype.hifi.dnick.selenium.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsAndBadgesPage extends AbstractPage{
    private WebElement back;
    @FindBy(css = "img[class=red_cross_image]")
    private List<WebElement> completedBadge;
    private List<WebElement> uncompletedBadge;

    public ResultsAndBadgesPage(WebDriver driver){
        super(driver);
    }
    public static boolean checkIfResultsAndBadgesPage(WebDriver driver, ResultsAndBadgesPage resultsAndBadgesPage){
        return resultsAndBadgesPage.title.getText().equals("Твои 3 најдобри резултати");
    }
    public static MyProfilePage clickBackButton(WebDriver driver, ResultsAndBadgesPage resultsAndBadgesPage){
        resultsAndBadgesPage.back.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    public void checkBadges(int completed, int uncompleted){
        int completedBadges=driver.findElements(By.className("completedBadge")).size();
        int uncompletedBadges=driver.findElements(By.className("uncompletedBadge")).size();
        Assertions.assertEquals(completed,completedBadges);
        Assertions.assertEquals(uncompleted,uncompletedBadges);
    }
}
