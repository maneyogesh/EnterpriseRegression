package pom.test.UIAction;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.test.UIAction.HomePage;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
//import volley.Log;
import pom.utility.Utils;

public class TC002_VerifyRegistration extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_VerifyRegistration.class.getName());
	HomePage homepage;
	String firstName = "test";
	String lastName = "lastTest";
	// String emailAddress = "automation@gmail.com";
	String emailAddress = System.currentTimeMillis() + "@gmail.com";
	String password = "password";
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("dataproviderDATA.xlsx", "LoginTestData");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	System.out.println("........1............"+this.toString()+"..........1.........");
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	 	//Log.startTestCase(sTestCaseName);
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	//	init();
	  	driver=getDriver();
	  //	driver = Utils.OpenBrowser(iTestCaseRow);
	}

	@Test
	public void verifyRegistration() throws Exception {
		  String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
			System.out.println(RunMode);
			  if(RunMode.equalsIgnoreCase("n")){
				  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
				  throw new SkipException("Not Executed this test case");
			  }
					try {
			log.info("=======started verifyRegistration Test===========");
			System.out.println("verify registration..........123");
			ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
			homepage = new HomePage(driver);
		//	homepage.switchToFrame();
		//	homepage.registorUser(firstName, lastName, emailAddress, password);
			Assert.assertEquals(true, homepage.getRegistrationSuccess());
			log.info("=======finished verifyRegistration Test===========");
			getScreenShot("verifyRegistration");
		} catch (AssertionError e) {
			homepage.switchToDefaultContent();
			getScreenShot("verifyRegistration");
			Assert.assertTrue(false, "verifyRegistration");
		} catch (Exception e) {
			log.info(e.fillInStackTrace().toString());
			homepage.switchToDefaultContent();
			getScreenShot("verifyRegistration");
			Assert.assertTrue(false, "verifyRegistration");
		}
	}

	
	@Test(dataProvider="loginData")
	public void verifyRegistration(String username, String password, String Mode, String CompanyCode) throws Exception {
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  throw new SkipException("Not Executed this test case");
		  }
		 /* if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		  lp = new LoginPage(driver);
		  homepage = new HomePage(driver);
		//try {
			log.info(" login with different users");
			lp.Login(username, password, CompanyCode);
			homepage.log("login successfully");
			homepage.logout();
			Assert.assertEquals(driver.getTitle(), "Onex Software 1");
			log.info("finish : ");
			
			getScreenShot("verifyLoginWithDifferentRecords");
	/*	} catch (Exception e) {
			getScreenShot("verifyLoginWithDifferentRecords");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			homepage.logout();
			Assert.fail("------------");
		}*/
	}
	
	
}
