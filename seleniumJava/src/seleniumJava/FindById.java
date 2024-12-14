package seleniumJava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 


public class FindById {
 public static void main(String[] args) {
	 
	 System.setProperty("webdriver.chrome.driver", "c:\\Drivers\\chromedriver.exe");

     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();

     
     driver.get("https://www.w3.org/WAI/ARIA/apg/patterns/button/examples/button/");

     
     WebElement element = driver.findElement(By.id("action"));

     
     System.out.println(element.getText());

     
     driver.quit();
 }
}
