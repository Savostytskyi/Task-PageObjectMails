package mail.pages.gmail;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailGmailMainPage {
	private WebDriver driver;
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement loginField;
	
	@FindBy(xpath = "//input[@id='Passwd']")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@id='signIn']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//a[@title='выйти']")
	private WebElement logoutButton;
	
	@FindBy(xpath = "//a[@title='Сервисы']")
	private WebElement servicesButton;
	
	@FindBy(xpath = "//span[text()='Почта']")
	private WebElement mailButton;
	
	public MailGmailMainPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public MailGmailBoxPage loginInMail(String login, String password) {
		loginField.sendKeys(login);
		passwordField.sendKeys(password);
		loginButton.click();
		servicesButton.click();
		mailButton.click();
		//driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(login);
		//driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys(password);
		//driver.findElement(By.xpath("//input[@id='signIn']")).click();		
		//driver.findElement(By.xpath("//a[@title='Сервисы']")).click();
		//driver.findElement(By.xpath("//span[text()='Почта']")).click();
		(new WebDriverWait(driver, 20))
	       .until(ExpectedConditions.titleContains("Входящие"));
		return new MailGmailBoxPage(driver);
	}

	public void logOut() {
		logoutButton.click();
		//driver.findElement(By.xpath("//a[@title='выйти']")).click();		
	}
	
	

}
