package wolf_j.com.github.tdd.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumCaseTest {

	private static WebDriver driver;

	@BeforeAll
	static void prepare() {
		System.setProperty("webdriver.chrome.driver", "D:\\Software\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// set implicitlyWait time
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	@AfterAll
	static void reset() {
		// Close the browser
		driver.quit();
	}

	@Test
	void testSeleniumClickByChrome() {
		driver.get("https://www.baidu.com/");
		WebElement input = driver.findElement(By.id("kw"));
		input.sendKeys("good");
		
		WebElement submit = driver.findElement(By.id("su"));
		submit.submit();
		
		assertEquals("百度一下，你就知道", driver.getTitle());
	}
	
	@Test
	@Disabled
	void testSeleniumCheckBybutton() {
		driver.get("http://cigp3r8cweb01.aia.biz/tcs/clock_checkrec.asp");

		WebElement check = driver.findElement(By.id("submit"));
		check.click();

		assertEquals("Time Clock System - Web Version - 3", driver.getTitle());
	}
	
	@Test
	void testSeleniumCheckByform() {
		driver.get("http://cigp3r8cweb01.aia.biz/tcs/clock_checkrec.asp");

		WebElement check = driver.findElement(By.name("FrontPage_Form2"));
		check.submit();

		assertEquals("Time Clock System - Web Version - 3", driver.getTitle());
	}

	@Test
	void testSeleniumCheckByGet() {
		driver.get("http://cigp3r8cweb01.aia.biz/tcs/clock_checkrec.asp");

		driver.get("http://cigp3r8cweb01.aia.biz/tcs/clock_logrec.asp?Tuserid=ASNPHM6");

		assertEquals("Time Clock System - Web Version - 3", driver.getTitle());
	}

}
