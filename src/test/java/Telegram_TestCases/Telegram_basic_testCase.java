package Telegram_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Telegram_Base.Telegram_Test_Base;
import Telegram_page.Telegram_HomePage;
import Telegram_page.logIn_Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Telegram_basic_testCase extends Telegram_Test_Base {
	
	Telegram_HomePage chatListingPage;
	logIn_Page loginPage;
	
	
	@BeforeTest
	public void init() throws InterruptedException {
		setUP();
		chatListingPage = new Telegram_HomePage(driver);
		loginPage = new logIn_Page(driver);
	
	
	}
	
	@Test(priority = 1)
	public void clickOnSiInButton() {
		chatListingPage.clickHomeSignUpButton();
		chatListingPage.clickSignInButton();
	}
	
	@Test(priority = 2)
	public void loginApp() {
		loginPage.loginFunction();
	}
	
	@Test(priority = 3)
	public void clickOnFireSide() throws InterruptedException {
		loginPage.clickOnFireSideIcon();
		loginPage.selectStaticUser();
		//loginPage.sentChat();
		loginPage.readPrevChat();
	}
	
	@Test(priority = 4)
	public void fetchChatHistory() throws InterruptedException {
		loginPage.fetchAccessToken();
		loginPage.fetchChannel();
		loginPage.readChatMessage();
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
