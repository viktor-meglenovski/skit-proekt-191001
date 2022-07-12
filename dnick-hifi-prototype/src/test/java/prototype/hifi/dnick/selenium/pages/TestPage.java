package prototype.hifi.dnick.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestPage extends AbstractPage{
    private WebElement cancel;
    private WebElement finish;
    private WebElement cancelYes;
    private WebElement cancelNo;
    private WebElement answerAllOk;
    @FindBy(css = "input[type=radio]")
    private List<WebElement> radioButtons;
    public TestPage(WebDriver driver){
        super(driver);
    }
    public static boolean checkIfTestPage(WebDriver driver, TestPage testPage){
        return testPage.title.getText().equals("Тестирај го своето знаење");
    }
    public static MyProfilePage cancelTestSuccessful(WebDriver driver, TestPage testPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", testPage.cancel);
        Thread.sleep(1000);
        WebElement btn=driver.findElement(By.className("btn-danger"));
        btn.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }

    public static TestPage cancelTestUnsuccessful(WebDriver driver, TestPage testPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", testPage.cancel);
        Thread.sleep(1000);
        WebElement btn=driver.findElement(By.className("bootbox-cancel"));
        btn.click();
        return testPage;
    }
    public static TestPage tryToSubmitWithoutAllAnswers(WebDriver driver, TestPage testPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("arguments[0].click()", testPage.finish);
        Thread.sleep(1000);
        WebElement btn=driver.findElement(By.className("bootbox-accept"));
        btn.click();
        return testPage;
    }
    public static MyProfilePage completeTestAndGoToMyProfile(WebDriver driver,TestPage testPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        for (WebElement we : testPage.radioButtons){
            jse2.executeScript("arguments[0].scrollIntoView()", we);
            Thread.sleep(100);
            jse2.executeScript("arguments[0].click()", we);
        }
        jse2.executeScript("arguments[0].click()", testPage.finish);
        Thread.sleep(1000);
        WebElement btn=driver.findElement(By.className("bootbox-cancel"));
        btn.click();
        return PageFactory.initElements(driver, MyProfilePage.class);
    }
    public static ResultsAndBadgesPage completeTestAndGoToBadges(WebDriver driver,TestPage testPage) throws InterruptedException {
        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        for (WebElement we : testPage.radioButtons){
            jse2.executeScript("arguments[0].scrollIntoView()", we);
            Thread.sleep(100);
            jse2.executeScript("arguments[0].click()", we);
        }
        jse2.executeScript("arguments[0].click()", testPage.finish);
        Thread.sleep(1000);
        WebElement btn=driver.findElement(By.className("bootbox-accept"));
        btn.click();
        return PageFactory.initElements(driver, ResultsAndBadgesPage.class);
    }
}
