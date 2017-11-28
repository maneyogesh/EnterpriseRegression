package Module.GeneralSetup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.ObjectPages.uiActions.OrganizationalUnitMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC002_OrganizationalUnitUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_OrganizationalUnitUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	OrganizationalUnitMaster ou;
	BaseClass bc;
	
	@DataProvider(name="OrganizationalUnitMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("OrganizationalUnitMaster.xlsx", "OrganizationalUnitMaster");
		return testRecords;
	}

	@BeforeClass
	public void setUp() throws Exception {
	  	sTestCaseName = this.toString();
	  	sTestCaseName = Utils.getTestCaseName(this.toString());
	  	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
	  	iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,Constant.Col_TestCaseName);
	  	driver=getDriver();
	  }

	@Test(priority=0,groups="OULogin")
	public void Login() throws Exception{
		em = new EntityMaster(driver);
		String RunMode = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_runMode);
		System.out.println(RunMode);
		  if(RunMode.equalsIgnoreCase("n")){
			  ExcelUtils.setCellData("Skip", iTestCaseRow, Constant.Col_Result);
			  ExcelUtils.setCellData("Not Executed this Test Case", iTestCaseRow, Constant.Col_ActualResult);
			  throw new SkipException("Not Executed this test case");
		  }
		em.Test_Login();
	}
		
	@Test(priority=1,dependsOnGroups = {"OULogin"}, groups="OUTargetPage")
	public void Target_Page() throws Exception{
		ou = new OrganizationalUnitMaster(driver);
		ou.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="OUAddNewButton",dependsOnGroups = {"OUTargetPage"})
	public void AddNew_Button(){
		ou = new OrganizationalUnitMaster(driver);
		ou.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"OUAddNewButton"},dataProvider="OrganizationalUnitMasterData")
	public void Data_Upload(String EntityNameData,String OUCodeData, String OUNameData, String DescriptionData, String ParentOUData, String ExcelDate, String ActiveData, String DigitsData, String DomainNamedata, String Initialdata) throws Exception {
		bc = new BaseClass(driver);
		ou = new OrganizationalUnitMaster(driver);
		
		/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
			 ou.Test_AddNewDataUpload(EntityNameData, OUCodeData, OUNameData, DescriptionData, ParentOUData, ExcelDate, ActiveData, DigitsData, DomainNamedata, Initialdata);
			 String AlertMsg = bc.closeAlertAndGetItsText();
			
				System.out.println(">>>>>>>>"+AlertMsg+"<<<<<<<<");
		 try {
			 Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
			getScreenShot("Organizational Unit Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	

	
}

	
	

