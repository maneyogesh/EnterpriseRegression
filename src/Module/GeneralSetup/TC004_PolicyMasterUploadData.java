package Module.GeneralSetup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.ObjectPages.uiActions.PolicyMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC004_PolicyMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC004_PolicyMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	PolicyMaster pm;
	BaseClass bc;
	
	@DataProvider(name="PolicyMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("PolicyMaster.xlsx", "PolicyMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="PMLogin")
	public void Login() throws Exception{
		em = new EntityMaster(driver);
		
		sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
		
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  Thread.sleep(1);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1,groups="PMTargetPage",dependsOnGroups={"PMLogin"})
	public void Target_Page() throws Exception{
		pm = new PolicyMaster(driver);
		pm.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="PMAddNewButton",dependsOnGroups={"PMTargetPage"})
	public void AddNew_Button(){
		pm = new PolicyMaster(driver);
		pm.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"PMAddNewButton"},dataProvider="PolicyMasterData")
	public void Data_Upload(String PolicyNameData,String PolicyDescriptionData, String ExcelDate, String EXEFilePath, String ActiveData) throws Exception {
		bc = new BaseClass(driver);
		pm = new PolicyMaster(driver);
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
			pm.Test_AddNewDataUpload(PolicyNameData, PolicyDescriptionData, ExcelDate, EXEFilePath, ActiveData);
			 String AlertMsg = bc.closeAlertAndGetItsText();
			System.out.println(">>>>>>>>"+AlertMsg+"<<<<<<<<");
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Policy Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	

	
}

	
	

