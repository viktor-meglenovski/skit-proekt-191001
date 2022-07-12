package prototype.hifi.dnick.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ResultsAndBadgesPage extends AbstractPage{
    private WebElement back;

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
}
