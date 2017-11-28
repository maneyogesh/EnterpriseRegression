package Module.GeneralSetup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC001_EntityMasterUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC001_EntityMasterUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	BaseClass bc;
	
	@DataProvider(name="EntityMasterData")
	public String[][] getTestData(){
		String[][] testRecords = getData("EntityMaster.xlsx", "EntityMasterData");
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

	@Test(priority=0, groups="EMLogin")
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
		
	@Test(priority=1,dependsOnGroups = {"EMLogin"},groups="EMTargetPage")
	public void Target_Page() throws Exception{
		em = new EntityMaster(driver);
		em.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="EMAddNewButton",dependsOnGroups = {"EMTargetPage"})
	public void AddNew_Button(){
		em = new EntityMaster(driver);
		em.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"EMAddNewButton"},dataProvider="EntityMasterData")
	public void Data_Upload(String EntityCodeData,String EntityNameData, String EntityDescriptionDate, String ParentEntityDate, String Exceldate, String ActiveData, String CompanyLevelData) throws Exception {
		bc = new BaseClass(driver);
		em = new EntityMaster(driver);
					
		 try {
			 em.Test_AddNewDataUpload(EntityCodeData, EntityNameData, EntityDescriptionDate, ParentEntityDate, Exceldate, ActiveData, CompanyLevelData);
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (Exception e) {
			getScreenShot("Entity Master");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record Not addedd successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Record Not Added Successfully");
		}
	}
	
	

	
}

	
	

