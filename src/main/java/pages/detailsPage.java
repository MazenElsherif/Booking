package pages;
import java.time.Duration;
import java.util.ArrayList;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class detailsPage  {
	private WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	Select dropdown;
	

	public detailsPage(WebDriver driver) {
		this.driver = driver;
	}
	By hotelHader=By.xpath("//h2[text()='Tolip Hotel Alexandria']");
	By bedButton=By.xpath("(//input[@value='2'])[1]");
	By Amount=By.xpath("(//select[contains(@class,'hprt-nos-select js-hprt-nos-select')])[1]");
	By resreveButton =By.xpath("//button[@class='txp-bui-main-pp bui-button bui-button--primary  hp_rt_input px--fw-cta js-reservation-button']");

	public By checkInDate=By.xpath("(//button[@data-testid=\"date-display-field-start\"])[2]//span");
	public By checOutDate=By.xpath("(//button[@data-testid=\"date-display-field-end\"])[2]//span");
public By hotleName= By.xpath("//h1[contains(.,'Tolip Hotel Alexandria')]");
	public void switchToNewTab() {
		Set<String> handles = driver.getWindowHandles();
		ArrayList<String> tabs = new ArrayList<>(handles);
		driver.switchTo().window(tabs.get(1));
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(hotelHader));
	}
	public void SelectBedAndAmount() {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(bedButton));
		dropdown= new Select(driver.findElement(Amount));
		dropdown.selectByValue("1");
		driver.findElement(resreveButton).click();
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(hotleName));
	}

}
