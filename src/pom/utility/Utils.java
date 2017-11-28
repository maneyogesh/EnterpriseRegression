package pom.utility;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utils {
	
		/*public WebDriver driver;
		public static ExtentReports extent;
		public static ExtentTest test;
		public ITestResult result;
		static String driverPath = "D:\\Grid\\";*/
	/*	
		public WebDriver getDriver() {
			return driver;
		}

		private void setDriver(String browserType, String appURL) {
			switch (browserType) {
			case "chrome":
				driver = initChromeDriver(appURL);
				break;
			case "firefox":
				driver = initFirefoxDriver(appURL);
				break;
			default:
				System.out.println("browser : " + browserType
						+ " is invalid, Launching Firefox as browser of choice..");
				driver = initFirefoxDriver(appURL);
			}
		}

		private static WebDriver initChromeDriver(String appURL) {
			System.out.println("Opening google chrome..");
			System.setProperty("webdriver.chrome.driver", driverPath
					+ "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}

		private static WebDriver initFirefoxDriver(String appURL) {
			System.out.println("Opening Firefox browser..");
			
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("default");
			WebDriver driver = new FirefoxDriver(myprofile);
			
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}

		@Parameters({ "browserType" })
		@BeforeClass
		public void initializeTestBaseSetup(String browserType) throws IOException {
			//List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Smoke Testing Data\\LoginDetails.xls","Sheet1");
			String appURL = Constant.URL;
			try {
				setDriver(browserType, appURL);

			} catch (Exception e) {
				System.out.println("Error....." + e.getStackTrace());
			}
		}
		*/
		/*@AfterClass
		public void closeBrowser() {
			driver.close();
		}*/
		
		
/*	public static WebDriver OpenBrowser(int iTestCaseRow) throws Exception{
		String sBrowserName;
		try{
		sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
		if(sBrowserName.equals("Mozilla")){
			driver = new FirefoxDriver();
			Log.info("New driver instantiated");
			driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    Log.info("Implicit wait applied on the driver for 10 seconds");
		    driver.get(Constant.URL);
		    Log.info("Web application launched successfully");
			}
		if(sBrowserName.equals("Chrome")){
			System.out.println("Browser Chrome is open");
		}
		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}
*/	
	public static String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;
				}catch (Exception e){
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
					}
			}
	
/*	 public static void mouseHoverAction(WebElement mainElement, String subElement){
		
		 Actions action = new Actions(driver);
         action.moveToElement(mainElement).perform();
         if(subElement.equals("Accessories")){
        	 action.moveToElement(driver.findElement(By.linkText("Accessories")));
        	 Log.info("Accessories link is found under Product Category");
         }
         if(subElement.equals("iMacs")){
        	 action.moveToElement(driver.findElement(By.linkText("iMacs")));
        	 Log.info("iMacs link is found under Product Category");
         }
         if(subElement.equals("iPads")){
        	 action.moveToElement(driver.findElement(By.linkText("iPads")));
        	 Log.info("iPads link is found under Product Category");
         }
         if(subElement.equals("iPhones")){
        	 action.moveToElement(driver.findElement(By.linkText("iPhones")));
        	 Log.info("iPhones link is found under Product Category");
         }
         action.click();
         action.perform();
         Log.info("Click action is performed on the selected Product Type");
	 }
	 public static void waitForElement(WebElement element){
		 
		 WebDriverWait wait = new WebDriverWait(driver, 10);
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	 	}*/
		
/*	 public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{
			try{
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName +".jpg"));	
			} catch (Exception e){
				Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
				throw new Exception();
			}
		}
	 
	 static {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			extent = new ExtentReports(System.getProperty("user.dir") + "/src/Report/" + formater.format(calendar.getTime()) + ".html", false);
		}
	 
	 public String captureScreen(String name) {
		 
		 	File destFile = null;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/Screenshots/";
				destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + "111.png");
				FileUtils.copyFile(scrFile, destFile);
				// This will help us to link the screen shot in testNG report
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destFile.toString();
		}*/
	 
/*		public void getresult(ITestResult result) {
			if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(LogStatus.PASS, result.getName() + " test is pass");
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
			} else if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
				String screen = captureScreen("");
				test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			} else if (result.getStatus() == ITestResult.STARTED) {
				test.log(LogStatus.INFO, result.getName() + " test is started");
			}
		}
	 */
	/*	@BeforeMethod()
		public void beforeMethod(Method result) {
			test = extent.startTest(result.getName());
			test.log(LogStatus.INFO, result.getName() + " test Started");
		}*/
		
	/*	@AfterSuite()
		public void closeBrowser() {
			
			extent.endTest(test);
			extent.flush();
			
		}*/
	 
	/*  @AfterClass
	  public void afterMethod() {
		    // Printing beautiful logs to end the test case
		  //  Log.endTestCase(sTestCaseName);
		    // Closing the opened driver
		  System.out.println("xyz.....");
		  extent.endTest(test);
			extent.flush();
		  System.out.println("abcd.....");
		    driver.close();
	  		}*/
	}
