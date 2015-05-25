package mail.pages.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLetterGmailPage {
	private WebDriver driver;
	
	@FindBy(xpath = "//textarea[@aria-label='����']")
	private WebElement toTextArea;
	
	@FindBy(xpath = "//input[@name='subjectbox']")
	private WebElement subjectField;
	
	@FindBy(xpath = "//div[@aria-label='���� ������']")
	private WebElement letterTextArea;
	
	@FindBy(xpath = "//img[@aria-label='��������� � �������']")
	private WebElement saveInDraftButton;
	
	@FindBy(xpath = "//div[text()='���������']")
	private WebElement sendButton;
	
	@FindBy(xpath = "//a[text()='������������']")
	private WebElement sentLettersItem;
	
	@FindBy(xpath = "//*[@id=':55']/div/div[1]/span/a")
	private WebElement toDraftItem;

	public NewLetterGmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public DraftGmailPage createNewLetter(String adress, String title,
			String text) {
		toTextArea.sendKeys(adress);
		subjectField.sendKeys(title);
		letterTextArea.sendKeys(text);
		saveInDraftButton.click();
		toDraftItem.click();
		return new DraftGmailPage(driver);
	}

	public SentLettersGmailPage sendDraftLetter() {
		sendButton.click();
		sentLettersItem.click();
		return new SentLettersGmailPage(driver);
	}

}
