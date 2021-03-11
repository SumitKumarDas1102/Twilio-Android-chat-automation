package Telegram_page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Telegram_Base.Telegram_Test_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Telegram_HomePage extends Telegram_Test_Base {
		 
	@AndroidFindBy(id = "com.wedoria.meverolearn:id/toolbar_iv_menu")
	public WebElement homeSignUpButton;
	
	@AndroidFindBy(id = "com.wedoria.meverolearn:id/navigationDrawer_tv_sign_in")
	public WebElement signInButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@className='androidx.recyclerview.widget.RecyclerView']")
	public List<WebElement> prevChatLists;
	
	
	//PageFactory
	public Telegram_HomePage(AppiumDriver<MobileElement> driver) {
		super();
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	public void clickHomeSignUpButton() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(homeSignUpButton));
		homeSignUpButton.click();
	}
	
	public void clickSignInButton() {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(signInButton));
		signInButton.click();
	}
	
	
}
