package pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchPage  {
	private WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;

	public searchPage(WebDriver driver) {
		this.driver = driver;
	}
	 By titles = By.xpath("//div[@data-testid='title']");
	By seeAvailabilityButton=By.xpath("//a[contains(@href,'tulip') and .//text()='See availability']");

	public void resultLocation() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(seeAvailabilityButton));
		driver.findElement(seeAvailabilityButton).click();


	}
	public List<WebElement> getTitles() {
		return driver.findElements(titles);
	}
}
