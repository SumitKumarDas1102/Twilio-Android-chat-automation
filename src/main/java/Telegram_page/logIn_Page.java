package Telegram_page;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Telegram_Base.Telegram_Test_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.chat.v2.service.ChannelReader;
import com.twilio.rest.chat.v2.service.channel.Message;
import com.twilio.rest.chat.v2.service.channel.MessageReader;
import com.twilio.rest.chat.v2.service.Channel;

public class logIn_Page extends Telegram_Test_Base {

	WebDriverWait wait = new WebDriverWait(driver, 500);
	public static String jwtToken;
	public static String ImNumber;
	public static String channelSid;

	String twilioAccountSid = "ACfc6b2c7d6abc527c2a344571bb415678";
	String twilioApiKey = "SKb11b53e6002342b75c63d0eab5b33d55";
	String twilioApiSecret = "lAYQXVpXJTRdwqlnrd2AZMSJkS8JGYZm";
	String serviceSid = "IS050f4f4664ad470081451edbb5094b20";
	String senderIdentity = "us-east-1:19aaa785-cb79-4429-8a0f-5cd689b5acf2";
	String receiverIdentity = "us-east-1:10e14bb6-b8f0-4e8b-b7d3-8d07d962e643";
	String ChannelId = "CHa9cbd302c2064480b6b14224760c831c";
	String twilio_auth_token = "200d549e5b41d72362903b1d15541c23";

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/login_etEmailIdSignIn")
	public WebElement emailID;

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/login_etPasswordSignIn")
	public WebElement passWord;

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/login_tvContinue")
	public WebElement continueButton;

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/toolbar_iv_Fireside")
	public WebElement fireSideButton;

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/llDialogDescription")
	public WebElement selectStaticUser;

	@AndroidFindBy(id = "com.wedoria.meverolearn:id/edit_chat_message")
	public WebElement openKeyboard;

	@AndroidFindBy(className = "android.widget.FrameLayout")
	public List<WebElement> prevChatLists;

	// PageFactory
	public logIn_Page(AppiumDriver<MobileElement> driver) {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void loginFunction() {
		wait.until(ExpectedConditions.visibilityOf(emailID));
		emailID.clear();
		emailID.sendKeys("qa1102sumit@gmail.com");

		wait.until(ExpectedConditions.visibilityOf(passWord));
		passWord.clear();
		passWord.sendKeys("12345678");

		wait.until(ExpectedConditions.elementToBeClickable(continueButton));
		continueButton.click();
	}

	public void clickOnFireSideIcon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(fireSideButton));
		fireSideButton.click();
	}

	public void selectStaticUser() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(selectStaticUser));
		selectStaticUser.click();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.NANOSECONDS);
	}

	public void sentChat() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(openKeyboard));
		openKeyboard.click();
		openKeyboard.sendKeys("How are you!");
	}

	public void readPrevChat() {
		wait.until(ExpectedConditions.visibilityOfAllElements(prevChatLists));
		System.out.println(prevChatLists.size());
		for (WebElement prevChat : prevChatLists) {
		}
	}

	public void fetchAccessToken() {
		ChatGrant grant = new ChatGrant();
		grant.setServiceSid(serviceSid);
		AccessToken token = new AccessToken.Builder(twilioAccountSid, twilioApiKey, twilioApiSecret)
				.identity(senderIdentity).grant(grant).build();
		jwtToken = token.toJwt();
	}

	public void fetchChannel() {
		Twilio.init(twilioAccountSid, twilio_auth_token);
		for (int i = 0; i < 5; i++) {
			Message message = Message.creator(serviceSid, ChannelId).create();
			Message createMessage = Message.updater(serviceSid, ChannelId, message.getSid())
					.setBody("Hello User(02/03) " + i + " message from Admin to user").setFrom(senderIdentity)
					.update();
			System.out.println("The Newly created admin message is: " + createMessage.getBody());
			Message ReceverMessage = Message.creator(serviceSid, ChannelId).create();
			Message createReceiverMessage = Message.updater(serviceSid, ChannelId, ReceverMessage.getSid())
					.setBody("Hello Admin (02/03) " + i + " message from User to Admin").setFrom(receiverIdentity)
					.update();
			System.out.println("The Newly created user message is: " + createReceiverMessage.getBody());
		}
	}

	public void readChatMessage() {
		Twilio.init(twilioAccountSid, twilio_auth_token);
		Channel channel = Channel.fetcher(serviceSid, ChannelId).fetch();
		ResourceSet<Message> fullUpdateMessages = Message.reader(serviceSid, ChannelId).limit(500).read();
		for (Message record : fullUpdateMessages) {
			System.out.println("Updated chat history " + record.getBody());
		}
		//System.out.println("Message count of that channel " + channel.getMessagesCount());
	}

}
