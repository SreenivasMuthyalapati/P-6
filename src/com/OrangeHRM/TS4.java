package com.OrangeHRM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TS4 {

	WebDriver driver;
	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@BeforeClass
	public void Launch() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Sreen\\OneDrive\\Documents\\QA\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement uid = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement pwd = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement lgn = driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));

		uid.sendKeys("Admin");
		pwd.sendKeys("admin123");
		lgn.click();
	}

	@BeforeMethod
	public void jobtitle() {

		driver.findElement(By.xpath("//*[@href='/web/index.php/admin/viewAdminModule']")).click();
		driver.findElement(By.xpath("//*[text()='Job ']")).click();
		driver.findElement(By.xpath("//*[text()='Job Titles']")).click();

	}

	@Test(priority = 1)
	public void TC001() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		WebElement jobtitle = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']"));

		Assert.assertTrue(jobtitle.isDisplayed(),
				"Test Failed, driver is not redirected to Job titles page after clicking on job titles");

	}
	
	@Test(priority = 2)
	public void TC002() {
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement lst = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(lst.isDisplayed(),"Test failed, user is not able to see list of job titles");
	}
	
	@Test(priority = 3)
	public void TC003() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<String> jobtitles = new ArrayList<>();
	    List<WebElement> jobtitlesCells = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]"));
	    for (WebElement jobtitlesCell : jobtitlesCells) {
	    	jobtitles.add(jobtitlesCell.getText());
	    }

	
	    List<String> sortedJobtitles = new ArrayList<>(jobtitles);
	    Collections.sort(sortedJobtitles);

	   
	    Assert.assertEquals(jobtitles, sortedJobtitles);
		
	}
	
	@Test (priority = 4)
	public void TC004() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		WebElement addfrm = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']"));
		
		Assert.assertTrue(addfrm.isDisplayed(), "Test condition failed, driver is not redirected to add jobtitle form page");
	}
	
	@Test (priority = 5)
	public void TC005()  {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Associate");
		
		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")).click();
		driver.findElement(By.xpath("//*[text()='Job ']")).click();
		driver.findElement(By.xpath("//*[text()='Job Titles']")).click();
		WebElement cnfrm = driver.findElement(By.xpath("//*[text()='Associate']"));
		Assert.assertTrue(cnfrm.isDisplayed(), "Test failed, user is not able to add jobtitle");
		
	}
	
	@Test (priority = 6)
	public void TC006() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/input")).sendKeys("Associate");
		WebElement msg = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
		Assert.assertTrue(msg.isDisplayed(), "Test condition failed, not showing already exist message");
			
	}
	
	@Test (priority = 7)
	public void TC007() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[2]/div/div/div[4]/div/button[2]")).click();
		WebElement mdffrm = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']"));
		Assert.assertTrue(mdffrm.isDisplayed(),"Test failed, driver is not redirected to modify form");
		
	}
	
	
	@Test (priority = 8)
	public void TC008() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[3]/div/div[1]/div/div[1]/div/label/span/i")).click();
		driver.findElement(By.xpath("//button[text()=' Delete Selected ']")).click();
		driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")).click();
		String cnfrm = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[2]/div/span")).getText();
		String expectedresult = "No Records Found";
		Assert.assertEquals(cnfrm,expectedresult, "Test failed, job title is not deleted" );
	}
	
	
	
	
	@AfterClass
	public void close() {
		driver.quit();
	}

}
