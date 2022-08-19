import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class TestNumberGame {
    @Test
    public void test_number() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://testsheepnz.github.io/random-number.html");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");

        Select selectFirstElement = new Select(driver.findElement(By.id("buildNumber")));
        selectFirstElement.selectByVisibleText("Demo");

        WebElement btnRoll = driver.findElement(By.id("rollDiceButton"));
        btnRoll.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement numberInput = driver.findElement(By.xpath("//input[@maxlength=\"6\"]"));
        numberInput.sendKeys("string");

        WebElement btnSubmit = driver.findElement(By.id("submitButton"));
        btnSubmit.click();

        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");

        WebElement label = driver.findElement(By.xpath("//label[@id=\"feedbackLabel\"]"));
        String msg = label.getText();
        System.out.println("Появилось сообщение: "+msg);
        Assert.assertEquals(msg, "string: Not a number!");

        if (msg.equals("string: Not a number!")) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }
}
