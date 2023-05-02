package com.OrangeHRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS7 {
	
	WebDriver driver;
	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@BeforeClass
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Sreen\\OneDrive\\Documents\\QA\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement uid = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement lgn = driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));

		uid.sendKeys("Admin");
		pwd.sendKeys("admin123");
		lgn.click();
		driver.findElement(By.xpath("//*[@href='/web/index.php/admin/viewAdminModule']")).click();

	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	@BeforeMethod
	public void tojobcategries() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[text()='Job ']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a")).click();
		
		
	}
	
	@Test(priority = 1)
	public void TC001() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String ttl = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']")).getText();
		String expectedttl = "Job Categories";
		Assert.assertEquals(ttl, expectedttl, "Test failed, driver is not redirected to the expected page");
		
	}
	
	@Test(priority = 2)
	public void TC002() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement lst = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(lst.isDisplayed(),"Test failed, list not displayed");
	}
	
	@Test(priority = 3)
	public void TC003() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[text()=' Add ']")).click();
		String ttl = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']")).getText();
		String expectedttl = "Add Job Category";
		Assert.assertEquals(ttl, expectedttl, "Test failed, not redirected to add job categories form page");
	}
	
	@Test(priority = 4)
	public void TC004() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[text()=' Add ']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("L7");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.xpath("//*[text()='Job ']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[4]/a")).click();
		WebElement cnfrm = driver.findElement(By.xpath("//*[text()='L7']"));
		
		Assert.assertTrue(cnfrm.isDisplayed(), "Test failed, job category not addes");
		
	}
	
	@Test(priority = 5)
	public void TC005() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[text()=' Add ']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("L7");
		String msg = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']")).getText();
		String expectedmsg = "Already exists";
		
		Assert.assertEquals(msg,expectedmsg,"Test failed, system is not showing error message");
	
	}
	@Test(priority = 6)
	public void TC006() {
		
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/label/span/i")).click();
		driver.findElement(By.xpath("//*[text()=' Delete Selected ']")).click();
		driver.findElement(By.xpath("//*[text()=' Yes, Delete ']")).click();
		WebElement cnfrm = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--span']"));
		String exp = "No Records Found";
		Assert.assertEquals(cnfrm.getText(), exp, "test failed");
		
	}
	
	
}
