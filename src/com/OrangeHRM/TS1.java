package com.OrangeHRM;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TS1 {


	WebDriver driver;
	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@BeforeClass
	public void Launch() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Sreen\\OneDrive\\Documents\\QA\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);

	}
	
	@Test(priority = 1)
	public void Test1() {
		
		String expectedtitle = "OrangeHRM";
		String Actualtitle = driver.getTitle();
		
		Assert.assertEquals(Actualtitle, expectedtitle, "Test failed, application is not directed to expected page");
		
		
	}

	@Test(priority = 2)
	public void Test2() throws Exception {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement uid = driver.findElement(By.xpath("//input[@name='username']"));
		
		Thread.sleep(5000);

		Assert.assertTrue(uid.isDisplayed(), "Test Failed, Username input box is not displayed");
		Assert.assertTrue(uid.isEnabled(), "Test Failed, Username input box is not enabled");

	}
	
	@Test(priority = 3)
	public void Test3() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));

		Assert.assertTrue(pwd.isDisplayed(), "Test Failed, Password input box is not displayed");
		Assert.assertTrue(pwd.isEnabled(), "Test Failed, Password input box is not enabled");

	}
	
	@Test(priority = 4)
	public void Test4() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement lgn = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));

		Assert.assertTrue(lgn.isDisplayed(), "Test Failed, Login Button is not displayed");
		Assert.assertTrue(lgn.isEnabled(), "Test Failed, Login Button is not enabled");

	}
	
	@Test(priority = 5)
	public void Test5() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement frgtpwd = driver.findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']"));

		Assert.assertTrue(frgtpwd.isDisplayed(), "Test Failed, Forgot Password is not displayed");
		Assert.assertTrue(frgtpwd.isEnabled(), "Test Failed, Forgot Password is not enabled");

	}
	
	

	@AfterClass
	public void Close() {
		driver.quit();
	}

}
