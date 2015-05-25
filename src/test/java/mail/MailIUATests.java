package mail;


import mail.pages.iua.DraftIUAPage;
import mail.pages.iua.MailIUABoxPage;
import mail.pages.iua.MailIUAMainPage;
import mail.pages.iua.NewLetterIUAPage;
import mail.pages.iua.SentLettersIUAPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import driver.DriverInit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MailIUATests extends DriverInit {

	
	private MailIUABoxPage box;
	private MailIUAMainPage main;
	private NewLetterIUAPage letter;
	private DraftIUAPage draft;
	private SentLettersIUAPage sent;
	private String letterTopic = "It is test letter";
	private String letterText = "Some text for test"; 
	private String letterAdress = "savostytskyi.anton@gmail.com";
	

	@BeforeClass
	public void beforeClass() {				
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.i.ua/");
	}

	@Test(description = "Login to mail")
	public void loginToMail() {
		main = new MailIUAMainPage(driver);
		box = main.loginInMail("test_mail_box@i.ua", "testmail");
		Assert.assertTrue(isElementPresent(By.xpath("//p[@class='make_message']//a")));
	}

	@Test(description = "Begin new letter creation", dependsOnMethods = { "loginToMail" })
	public void beginCreationOfLetter() {
		letter = box.chouseNewLetter();
		draft = letter.createNewLetter(letterAdress, letterTopic, letterText);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='mesgList']//span[@class='sbj']/span")).getText(), letterTopic);
	}
	
	@Test(description = "Checking the contains of letter", dependsOnMethods = { "beginCreationOfLetter" })
	public void checkTheLetter() {
		letter = draft.openDraftLetter(letterTopic);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='text']")).getText(), letterText);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@id='to']")).getText(), letterAdress);
	}

	@Test(description = "Send the letter", dependsOnMethods = { "checkTheLetter" })
	public void sendTheLetter() {
		sent = letter.sendDraftLetter();
		Assert.assertTrue(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));
		sent.goToGrft();
		Assert.assertFalse(isElementPresent(By.xpath("//span[text()='"+letterTopic+"']")));
		
	}

	@AfterClass
	public void afterClass() {
		main = draft.goToMainPage();
		main.logOut();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.quit();
	}

	private boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

}
