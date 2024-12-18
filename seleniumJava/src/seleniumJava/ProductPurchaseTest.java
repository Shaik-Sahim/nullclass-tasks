package seleniumJava;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.time.LocalTime;

public class ProductPurchaseTest {

    WebDriver driver;

    public ProductPurchaseTest() {
		System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");
		
        driver = new ChromeDriver();
        
		driver.manage().window().maximize();
    }

    public void searchProductAndAddToCart() {
        driver.get("https://www.amazon.in/");
        
        WebDriverWait waitforSearch = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBox = waitforSearch.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        searchBox.sendKeys("Washing machine" + Keys.RETURN);


        WebElement addToCartButton = waitforSearch.until(ExpectedConditions.elementToBeClickable(By.id("a-autoid-3-announce")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
    }

    public void makePayment() {
        WebDriverWait waitforCheckout = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        WebDriverWait waitforPopup = new WebDriverWait(driver, Duration.ofSeconds(50));
        

        WebElement cartButton = waitforPopup.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartButton);
        cartButton.click();
        
        
        WebElement checkoutButton = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("sc-buy-box-ptc-button")));
        checkoutButton.click();
        
        //Login  functionality automation
        
        WebElement emailInputField = waitforCheckout.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));

	     
        emailInputField.sendKeys("sahim3041@gmail.com");
        
	     
	     WebElement continueButton = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
	     continueButton.click();
	     
	   
	     WebElement pwdInputField = waitforCheckout.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));

	     pwdInputField.sendKeys("sahim@789");
        
	     
	     WebElement signInButton = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit")));
	     signInButton.click();
	     
	     WebElement confirmAddressButton = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("shipToThisAddressButton")));
	        
	     confirmAddressButton.click();
	        
	        
  	     WebElement paymentAmount = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("subtotals-marketplace-table")));

        String amountText = paymentAmount.getText();
        
        double amount = Double.parseDouble(amountText.replaceAll("[^0-9.]", ""));
        
        if (amount > 500) {
        	
        	System.out.println("order amount is more than 500");
        	System.out.println("*******************************");
        	
        	//below commented code will click on the pay button and place the order, so instead I have added console output.
        	
	   	     //WebElement payButton = waitforCheckout.until(ExpectedConditions.elementToBeClickable(By.id("submitOrderButtonId")));
	         //payButton.click();
        	
        	System.out.println("**Your order has been placed Sucessfully**");

        }
    }


    public void runTest() {
        if (TimeCheck.isValidTime()) {
            try {
                searchProductAndAddToCart();
                makePayment();
            } finally {
                driver.quit();
            }
        } else {
            System.out.println("Test can only be run between 6 PM and 7 PM.");
        }
    }

    public static void main(String[] args) {
        ProductPurchaseTest test = new ProductPurchaseTest();
        test.runTest();
    }
}
