package Telegram_Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Telegram_Test_Base {
	
	public static AppiumDriver<MobileElement> driver;
	
	@BeforeTest
	public void setUP() {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "android");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
			cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.wedoria.meverolearn");
			cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.wedoria.meverolearn.activity.MeveroSplashActivity");
			cap.setCapability("noReset","true"); cap.setCapability("fullReset","false");
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(url,cap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void openTelegram() {
		System.out.println("App is Running");
	}
	 

}
