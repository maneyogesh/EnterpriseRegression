package Module.GeneralSetup;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.ObjectPages.uiActions.EntityMaster;
import pom.ObjectPages.uiActions.UploadMasterRevised;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC003_UploadMaster extends TestBase {

	public static final Logger log = Logger.getLogger(TC003_UploadMaster.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	UploadMasterRevised um;
	EntityMaster em;
	BaseClass bc;
	
	@DataProvider(name="UploadMaster")
	public String[][] getTestData(){
		String[][] testRecords = getData("UploadMaster.xlsx", "UploadMaster");
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

	@Test(priority=0,groups={"UMLogin"})
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
		
	@Test(priority=1,groups={"UMTargetPage"},dependsOnGroups = {"UMLogin"})
	public void Target_Page() throws Exception{
		um = new UploadMasterRevised(driver);
		um.Test_Open_Target_Page();
	}
		
	@Test(priority=2,dependsOnGroups = {"UMTargetPage"},dataProvider="UploadMaster")
	public void Data_Upload(String dropdown_Value,String UploadFileNameData, String UploadTypeText, String EXEFilePath, String Mode) throws Exception {
		bc = new BaseClass(driver);
		um = new UploadMasterRevised(driver);
		
		  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}
		  um.Test_Search_Record(dropdown_Value, UploadFileNameData);
		  um.Test_data_upload(UploadFileNameData, UploadTypeText, EXEFilePath);
		  String AlertMsg = bc.closeAlertAndGetItsText();
		  if(AlertMsg.equalsIgnoreCase("Record Uploaded Successfully .") || AlertMsg.equalsIgnoreCase("Records committed successfully.") || AlertMsg.equalsIgnoreCase("Data Rollback Successfully.")){
				 try {
					 Assert.assertEquals(driver.getTitle(), "Upload Master");
					ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
					ExcelUtils.setCellData("Data Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
					} catch (AssertionError e) {
					getScreenShot("Upload Master");
					ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
					ExcelUtils.setCellData("Data not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
					Assert.fail("Expected : [Upload Master] But Found : ["+driver.getTitle()+"]");
				}
		  }
		  else{
			  
			  WebElement cancelbutton = driver.findElement(By.id("ContentPlaceHolder_Imagecolse1"));
			  if(cancelbutton.isDisplayed()){
				  bc.click(cancelbutton, "Cancel Button");
			  }else{
				  
			  }
			  ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
				ExcelUtils.setCellData("Data not Added Successfully", iTestCaseRow, Constant.Col_ActualResult);
			  Assert.fail("expected : [data committed successfully] or [rollback successfully] but found : [Page crashed] or [data neither committed nor rollback]");
		  }
	
	}
	


	
}

	
	

