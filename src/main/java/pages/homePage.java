package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilties.CommonActions;

public class homePage  {
	private WebDriver driver;
	CommonActions commonActionObject=new CommonActions();
	WebDriverWait wait;
	String checkInDate=CommonActions.getCheckInDate();
	String checkOutDate=CommonActions.getCheckOutDate(checkInDate);
	String formattedCheckInDate=CommonActions.changeFormat(checkInDate);
	String formattedCheckOutDate=CommonActions.changeFormat(checkOutDate);

	public homePage(WebDriver driver) {
		this.driver = driver;
	}
	By pupupButton=By.xpath("//button[@aria-label='Dismiss sign-in info.']");
	By destinationCity=By.xpath("//input[@name='ss']");
	By alexAutoComplete=By.xpath("//span[@data-testid='autocomplete-icon-default']/following-sibling::div//div[text()='Alexandria']");
	By searchButton=By.xpath("//button[contains(.,'Search')]");
	By checkinDate =By.xpath("//span[@aria-label='"+checkInDate+"']");
	By checkoutDate =By.xpath("//span[@aria-label='"+checkOutDate+"']");
	public void handlePopUp() {
		wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(pupupButton));
			driver.findElement(pupupButton).click();
		} catch (Exception e) {
			System.out.println("Popup doesn't appear.");		
		}
	}
	public void searchForReversation(String city) {
		driver.findElement(destinationCity).clear();
		driver.findElement(destinationCity).sendKeys(city);
		driver.findElement(alexAutoComplete).click();
		driver.findElement(checkinDate).click();
		driver.findElement(checkoutDate).click();
		driver.findElement(searchButton).click();
		CommonActions.editExcelFile(formattedCheckInDate,formattedCheckOutDate);

	}
}
