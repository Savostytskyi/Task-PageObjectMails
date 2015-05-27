package mail.pages.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailIUAMainPage {
	private WebDriver driver;
	@FindBy(xpath = "//input[@name='login']")
	private WebElement loginField;
	
	@FindBy(xpath = "//input[@name='pass']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@title='���� �� �����']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//a[@title='�����']")
	private WebElement logoutButton;
	
	public MailIUAMainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public MailIUABoxPage loginInMail(String login, String password) {
		loginField.sendKeys(login);
		passwordField.sendKeys(password);
		loginButton.click();
		return new MailIUABoxPage(driver);
	}

	public void logOut() {
		logoutButton.click();		
	}
	
	

}
