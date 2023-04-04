
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MCStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I have started a {string}")
    public void iHaveStartedA(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Selenium\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().window().maximize();
        sendClick(By.id("onetrust-accept-btn-handler"));

    }

    @And("I have given an {string}")
    public void iHaveGivenAn(String email) {
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
    }

    @And("I have entered the {string}")
    public void iHaveEnteredThe(String username) {
        if (username.equals(username)) {
            Random rand = new Random();
            String rand1 = Integer.toString(rand.nextInt(1000));
            username = username + rand1;
            driver.findElement(By.name("username")).sendKeys(Keys.CONTROL + "a");// select the autofill
            driver.findElement(By.name("username")).sendKeys(Keys.DELETE);//Delete the autofill
            //driver.findElement(By.name("username")).sendKeys(username);
            sendKeys(By.name("username"), username);
        }
        if (username.equals("mnbvcxzas")) {
            sendKeys(By.name("username"), "mnbvcxzas");
        }
    }

    @And("I have entered  {string}")
    public void iHaveEntered(String password) {
        // driver.findElement(By.id("new_password")).sendKeys(password);
        sendKeys(By.id("new_password"), password);
    }

    @When("I click on Signup button")
    public void iClickOnSignupButton() {
        //sendClick(By.id("create-account-enabled"));
        // Actions action = new Actions(driver);
        WebElement signup = driver.findElement(By.id("create-account-enabled"));
        signup.click();
        // action.doubleClick(signup).perform();

      /* Actions action = new Actions(driver);
        waitIsDisplayed(By.id("create-account-enabled"));
        WebElement signup = driver.findElement(By.id("create-account-enabled"));
        action.moveToElement(signup).perform();*/


    }

    @Then("The user is {string}")
    public void theUserIs(String registered) throws InterruptedException {
        String actual;
        String expected;

        if (registered.equalsIgnoreCase("yes")) {
            waitIsDisplayed(By.cssSelector("[class='!margin-bottom--lv3 no-transform center-on-medium ']"));
            actual = driver.findElement(By.cssSelector("[class='!margin-bottom--lv3 no-transform center-on-medium ']")).getText();
            expected = "Check your email";
            assertEquals(expected, actual);
        } else if (registered.equalsIgnoreCase("no")) {

//waitIsDisplayed(By.linkText("Please check your entry and try again."));
            //  driver.findElement(By.id("create-account-enabled")).isDisplayed();


            waitIsDisplayed(By.cssSelector("[class='invalid-error']"));
            String error = driver.findElement(By.cssSelector("[class='invalid-error']")).getText();
            System.out.println(error);
            actual = driver.getTitle();
            expected = "Signup | Mailchimp";
            assertEquals(expected, actual);
        }
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    private void sendKeys(By by, String text) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.sendKeys(text);
    }

    private void sendClick(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.click();

    }

    private void waitIsDisplayed(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        element.isDisplayed();
    }


}
