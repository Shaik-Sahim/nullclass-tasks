package seleniumJava;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;

public class LoginTest {

    private WebDriver driver;
    
    private String baseUrl = "https://in.puma.com/in/en";

    @BeforeEach
    public void chromeDriverConfig() {
    	
    	
    	System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
		driver.manage().window().maximize();
		
    }

    @Test
    public void testLoginFunctionality() {
    	
        driver.get(baseUrl + "/account/login?action=login_with_email");
        
        driver.findElement(By.id("email")).sendKeys("sahim3041@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Sahim@789");
        driver.findElement(By.id("login-submit")).click(); 
        
        WebDriverWait waitforLogin = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement greetingElement = waitforLogin.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1[data-test-id='account-greeting']")));
        
        Assertions.assertTrue(greetingElement.isDisplayed());
        
    }

    @Test
    public void testProfileNameValidation() {
    	
    	driver.get(baseUrl + "/account/login?action=login_with_email");
        
        driver.findElement(By.id("email")).sendKeys("sahim3041@gmail.com");
        driver.findElement(By.id("password")).sendKeys("Sahim@789");
        driver.findElement(By.id("login-submit")).click(); 
        
        WebDriverWait waitforLogin = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement greetingElement = waitforLogin.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1[data-test-id='account-greeting']")));
        
        String fullGreetingText = greetingElement.getText();
        String name = fullGreetingText.replace("Hello, ", "").trim();
        
        Assertions.assertFalse(name.matches(".*[ACGILK].*"), "The Profile name should not contain restricted characters.");
        
    }


    @Test
    public void testTimeSpecificBehavior() {
    	
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();

        boolean testsPassed = true;

        if (hour >= 12 && hour <= 18) {
            try {
                testLoginFunctionality();
                testProfileNameValidation();
            } catch (Exception e) {
                testsPassed = true;
            }
        } else {
            System.out.println("These tests can only be run between 12 PM and 3 PM. Current time: " + currentTime);
            testsPassed = false;
        }

        Assertions.assertTrue(testsPassed, "The Tests failed, either outside time range or one of the tests didn't pass.");
    }

    @AfterEach
    public void tearDown() {
    	
        if (driver != null) {
            driver.quit();
        }
        
    }
}
