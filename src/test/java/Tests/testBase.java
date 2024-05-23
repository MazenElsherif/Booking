package Tests;

import java.io.File;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.detailsPage;
import pages.homePage;
import pages.searchPage;

import com.aventstack.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;


import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class testBase {
	 protected static WebDriver driver;
	    public static Properties prop;
	    protected ATUTestRecorder recorder;
	    SoftAssert softAssert;
	    public static com.relevantcodes.extentreports.ExtentReports report;
	    public static com.relevantcodes.extentreports.ExtentTest logger;
	    ExtentReports extent;
	    LocalDate myObj = LocalDate.now();
	    homePage homePageObject;
	    searchPage searchPageObject;
	    detailsPage detailsPageObject;
	    
	    public static String downloadpath=System.getProperty("user.dir")+"\\download";
	    @BeforeMethod
	    public void start(Method method) throws ATUTestRecorderException {
	    	
	    	softAssert = new SoftAssert();
	    	String browser = System.getProperty("browser", Config.Constants.BROWESR);

	        if(browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            driver=new ChromeDriver(chromeopthion());}
	        else if (browser.equalsIgnoreCase("firefox")) {
	            WebDriverManager.firefoxdriver().setup();
	            driver=new FirefoxDriver();
	        }
	        else if(browser.equalsIgnoreCase("edge")) {
	            WebDriverManager.edgedriver().setup();
	            driver= new EdgeDriver();
	        }
	        driver.manage().window().maximize();
	        startrecord(method.getName());
	        logger=report.startTest(method.getName());
	        driver.navigate().to(Config.Constants.BASE_URL);
	    }
	    @AfterMethod
	    public void end(Method method,ITestResult result) throws ATUTestRecorderException, IOException {

	        takescreenshot(method.getName());
	        recorder.stop();
	        if(result.getStatus()==ITestResult.SUCCESS) {
	            logger.log(LogStatus.PASS, "Test Pass");
	            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
	            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
	            logger.setDescription("DHA Report");

	        }
	        else if(result.getStatus()==ITestResult.FAILURE) {
	            logger.log(LogStatus.FAIL, result.getThrowable());
	            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".png"+"'><span class='label info'>Download Snapshot</span></a>");
	            logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov"+"'><span class='label info'>Download Video</span></a>");
	        }
	        else {
	            logger.log(LogStatus.SKIP, "Test Skipped");
	        }
//	        driver.quit();

	    }
	    @BeforeSuite
	    public void startreport() {
	        String reportpath=System.getProperty("user.dir")+"\\TestReport\\ExtentReportResults"+myObj+".html";
	        report=new com.relevantcodes.extentreports.ExtentReports(reportpath,true);
	        report.addSystemInfo("Reported By", "Mazen");
	        report.loadConfig(new File(System.getProperty("user.dir")+"\\extent2.xml"));

	    }
	    @AfterSuite
	    public void endreport() {
	        report.flush();
	    }
	  
	    public static void takescreenshot(String name) throws IOException, ATUTestRecorderException {
	        File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\TestReport\\"+name+".png"));

	    }
	    public void startrecord(String name) throws ATUTestRecorderException {
	        recorder=new ATUTestRecorder(System.getProperty("user.dir")+"\\TestReport", name, false);
	        recorder.start();
	    }
	    public static ChromeOptions chromeopthion() {
	        ChromeOptions option =new ChromeOptions();
	        HashMap<String, Object> chromeprefs=new HashMap<String, Object>();
	        chromeprefs.put("profile.default.content_settings.popups", 0);
	        chromeprefs.put("download.default_directory", downloadpath);
	        option.setExperimentalOption("prefs", chromeprefs);
	        option.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        option.addArguments("--remote-allow-origins=*");
	        option.addArguments("--start-maximized"); 
	        option.addArguments("--disable-popup-blocking");
	        option.addArguments("--incognito");
	        return option;
	    }

}
