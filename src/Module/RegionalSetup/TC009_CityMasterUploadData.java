package Module.RegionalSetup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.CityMaster;
import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC009_CityMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC009_CityMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	CityMaster cm;
	BaseClass bc;
	
	@DataProvider(name="CityMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("CityMaster.xlsx", "CityMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	   	driver=getDriver();
	  }

	@Test(priority=0,groups="CMLogin")
	public void Login() throws Exception{
		em = new EntityMaster(driver);
		bc = new BaseClass(driver);
		
		sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  bc.waitFixedTime(1);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1,groups="CMTargetPage",dependsOnGroups={"CMLogin"})
	public void Target_Page() throws Exception{
		cm = new CityMaster(driver);
		cm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="CMAddNewButton",dependsOnGroups={"CMTargetPage"})
	public void AddNew_Button(){
		cm = new CityMaster(driver);
		cm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"CMAddNewButton"},dataProvider="CityMasterData")
	public void Data_Upload(String CountryData, String StateData, String CityCodeData, String CityNameData, String ActiveData) throws Exception {
		bc = new BaseClass(driver);
		cm = new CityMaster(driver);
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		cm.Test_AddNewDataUpload(CountryData, StateData, CityCodeData, CityNameData, ActiveData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("City Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	

	
}

	
	

