package com.OrangeHRM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TS3 {

	WebDriver driver;
	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	@BeforeClass
	public void launch() {
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
	}

	@BeforeMethod
	public void adminUM() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@href='/web/index.php/admin/viewAdminModule']")).click();

	}

	@Test(priority = 1)
	public void TC001() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement um = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h5 oxd-table-filter-title']"));
		Assert.assertTrue(um.isDisplayed(), "Test Failed, Driver is not redirected to Usermanagement Page");
	}

	@Test(priority = 2)
	public void TC002() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By
				.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input"))
				.sendKeys("Admin");
		driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"))
				.click();
		WebElement result = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(result.isDisplayed(), "Test Failed, user is not able to search with username");

	}

	@Test(priority = 3)
	public void TC003() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]"))
				.click();
		driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"))
				.click();
		WebElement result = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(result.isDisplayed(), "Test Failed, user is not able to search with username");

	}

	@Test(priority = 4)
	public void TC004() {

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@placeholder='Type for hints...']")).sendKeys("Paul Collings");
		driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"))
				.click();
		WebElement result = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(result.isDisplayed(), "Test Failed, user is not able to search with username");

	}

	@Test(priority = 5)
	public void TC005() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[2]"))
				.click();
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]"))
				.click();
		driver.findElement(
				By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"))
				.click();
		WebElement result = driver.findElement(By.xpath("//*[@class='orangehrm-container']"));
		Assert.assertTrue(result.isDisplayed(), "Test Failed, user is not able to search with username");

	}

	@Test(priority = 6)
	public void TC006() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement list = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]"));
		Assert.assertTrue(list.isDisplayed(), "Test failed, user is not able to see list of employees");

	}

	@Test(priority = 7)
	public void TC007() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement userToDelete = driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[5]/div/div[6]/div/button[1]"));
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[5]/div/div[6]/div/button[1]"))
				.click();
		driver.findElement(By
				.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']"))
				.click();
		WebElement deletedUser = driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[5]/div/div[6]/div/button[1]"));
		Assert.assertNotEquals(deletedUser, userToDelete, "User was not deleted successfully.");
	}

	@Test(priority = 8)
	public void TC008() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(
				"/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[7]/div/div[6]/div/button[2]/i"))
				.click();
		WebElement editform = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']"));
		Assert.assertTrue(editform.isDisplayed(), "Test Failed, user is not able to see modify form");
	}

	@Test(priority = 9)
	public void TC009() throws Exception {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[2]/div/div[1]/div/div/label/span/i"))
				.click();
		WebElement s = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/span"));
		Thread.sleep(2000);
		Assert.assertTrue(s.isDisplayed(), "Test Failed, user is not able to select multiple users");
	}

	@Test(priority = 10)
	public void TC010() {

		List<String> usernames = new ArrayList<>();
		List<WebElement> usernameCells = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]"));
		for (WebElement usernameCell : usernameCells) {
			usernames.add(usernameCell.getText());
		}

		List<String> sortedUsernames = new ArrayList<>(usernames);
		Collections.sort(sortedUsernames);

		Assert.assertEquals(usernames, sortedUsernames);

	}

	@Test(priority = 11)
	public void TC011() {

		driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		WebElement adfrm = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--h6 orangehrm-main-title']"));

		Assert.assertTrue(adfrm.isDisplayed(), "Test Failed, user is not able to see add user form");
	}

	@Test(priority = 12)
	public void TC012() {

		driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		driver.findElement(
				By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']"))
				.click();
		WebElement errmsg = driver
				.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span"));

		Assert.assertTrue(errmsg.isDisplayed(),
				"Test Failed, error message is not showing if we not filled mandatory fields");

	}

	@AfterClass
	public void close() {
		driver.quit();
	}

}
