package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import Config.TestDataProvider;
import pages.detailsPage;
import pages.homePage;
import pages.searchPage;

public class testCases extends testBase {

	@Test(dataProvider = "excelData", dataProviderClass = TestDataProvider.class)
	public void booking(String Destination , String CheckInDate , String CheckOutDate) {
		homePageObject= new homePage(driver);
		searchPageObject=new searchPage(driver);
		detailsPageObject=new detailsPage(driver);
		homePageObject.handlePopUp();
		homePageObject.searchForReversation(Destination);
		String titleText1 = searchPageObject.getTitles().get(1).getText();
		String titleText2 = searchPageObject.getTitles().get(2).getText();
		softAssert.assertEquals(titleText1, "Tolip Hotel Alexandria", "Title at index 1 does not match");
		softAssert.assertEquals(titleText2, "Tolip Hotel Alexandria", "Title at index 2 does not match");
		searchPageObject.resultLocation();
		detailsPageObject.switchToNewTab();
		Assert.assertEquals(driver.findElement(detailsPageObject.checkInDate).getText(), CheckInDate);
		Assert.assertEquals(driver.findElement(detailsPageObject.checOutDate).getText(), CheckOutDate);
		detailsPageObject.SelectBedAndAmount();
		Assert.assertTrue(driver.findElement(detailsPageObject.hotleName).isDisplayed());

	}
	

}