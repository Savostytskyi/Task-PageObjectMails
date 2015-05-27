package mail.pages.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailIYandexMainPage {
	private WebDriver driver;
	@FindBy(xpath = "//label[@id='nb-1']/span/input")
	private WebElement loginField;
	
	@FindBy(xpath = "//label[@id='nb-2']/span/input")
	private WebElement passwordField;
	
	@FindBy(xpath = "//span[text()='Войти']")
	private WebElement loginButton;
	
	public MailIYandexMainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public MailYandexBoxPage loginInMail(String login, String password) {
		loginField.sendKeys(login);
		passwordField.sendKeys(password);
		loginButton.click();
		return new MailYandexBoxPage(driver);
	}	

}
