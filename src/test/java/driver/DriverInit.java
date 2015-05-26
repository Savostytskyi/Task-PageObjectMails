package driver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class DriverInit {
	private String driverName = "firefox";
	public WebDriver driver;
	
	
  @BeforeSuite
  public void beforeSuite() throws IOException {
		switch (driverName) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "opera":
			System.setProperty("webdriver.opera.driver", "libdrivers/operadriver64");
			driver = new OperaDriver();
			;
			break;
		case "chrome":
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("libdrivers/chromedriver.exe"))
					.usingAnyFreePort().build();
			service.start();
			driver = new ChromeDriver(service);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://google.com");
			;
			break;
		case "ie":
			File file = new File("libdrivers/IEDriverServer32.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			driver = new InternetExplorerDriver();
			;
			break;
		case "remote":
			driver = new RemoteWebDriver(new URL(
					"http://10.23.11.127:4444/wd/hub"),
					DesiredCapabilities.firefox());
			driver.get("http://google.com");

			break;
		case "cloud":
		    driver = new RemoteWebDriver(new URL("http://Savostytskyi:10a26e45-99a7-4f43-a4db-fdc17cf19190@ondemand.saucelabs.com:80/wd/hub"), 
		    		DesiredCapabilities.internetExplorer());
	
			break;

		default:
			System.out.println("Invalid driver");
			break;
		}
  }

  @AfterSuite
  public void afterSuite() {
  }

}
