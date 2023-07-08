package seleniumTesting;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import reusable.Scrolling;

import javax.swing.plaf.basic.BasicSliderUI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ElementsStepdefs {
    Scrolling scrolling;
WebDriver driver;
WebDriverWait wait;

    @Before
    public void browserSetup()
    {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
    }
    @Given("Scroll until Elements visible on page")
    public void openElementsTab()
    {
        WebElement Elements=driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        js.executeScript("arguments[0].scrollIntoView();",Elements);
        Elements.click();
    }

    @Then("perform actions under text box")
    public void performActionsUnderIt() {
        driver.findElement(By.cssSelector("div.element-group li>span")).click();
        driver.findElement(By.id("userName")).sendKeys("prava");
        driver.findElement(By.id("userEmail")).sendKeys("prava@email.com");
        driver.findElement(By.id("currentAddress")).sendKeys("address1");
        driver.findElement(By.id("permanentAddress")).sendKeys("address2");
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        new Scrolling().scrollToElement(driver,driver.findElement(By.id("submit")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("submit")))).click();

    }

    @Then("perform actions under checkbox box")
    public void performActionsUnderCheckboxBox() {
        WebElement CheckBox=driver.findElement(By.xpath("//span[text()='Check Box']"));
        new Scrolling().scrollToElement(driver,driver.findElement(By.id("userName")));
        CheckBox.click();
        driver.findElement(By.xpath("//button[@class='rct-collapse rct-collapse-btn']")).click();
        driver.findElement(By.xpath("//span[@class='rct-title' and text()='Downloads']")).click();
        Actions actions=new Actions(driver);
        WebElement toggle =driver.findElement(By.xpath("//span[text()='Desktop']//ancestor::label//parent::span/button"));
        actions.moveToElement(toggle).perform();
        toggle.click();
        driver.findElement(By.xpath("//span[text()='Commands']")).click();


    }

    @Then("perform actions under Radio Button")
    public void performActionsUnderRadioButton() {
      driver.findElement(By.xpath("//span[text()='Radio Button']")).click();
      driver.findElement(By.xpath("//label[@for='impressiveRadio']")).click();
    }

    @Then("perform actions under Web Tables")
    public void performActionsUnderWebTables() {
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

    }

    @Then("perform actions under Button")
    public void performActionsUnderButton() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Buttons']")).click();
        Actions actions=new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
//        Wait wait1=new FluentWait(driver)
//                .withTimeout(Duration.ofSeconds(20))
//                .pollingEvery(Duration.ofSeconds(5));
//        Thread.sleep(5000);

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[text()='Buttons']")),"Buttons" ));

        WebElement doubleClickElement=driver.findElement(By.xpath("//button[text()='Double Click Me']"));
        wait.until(ExpectedConditions.elementToBeClickable(doubleClickElement));
        actions.doubleClick(doubleClickElement).perform();
        WebElement rightClickElement=driver.findElement(By.xpath("//button[text()='Right Click Me']"));
        wait.until(ExpectedConditions.elementToBeClickable(rightClickElement));
        actions.contextClick(rightClickElement).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        actions.click(driver.findElement(By.xpath("//button[text()='Click Me']"))).perform();


    }
//    @After
//    public void close()
//    {
//        driver.close();
//    }
}
