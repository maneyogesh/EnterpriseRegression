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
import pom.ObjectPages.uiActions.PollQuizSetup;
import pom.TestBase.BaseClass;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.utility.Constant;
import pom.utility.ExcelUtils;
import pom.utility.Utils;

public class TC006_PollQuizSetupUploadData extends TestBase {

	public static final Logger log = Logger.getLogger(TC006_PollQuizSetupUploadData.class.getName());
	private String sTestCaseName;
	private int iTestCaseRow;
	LoginPage lp;
	EntityMaster em;
	PollQuizSetup pqs;
	BaseClass bc;
	
	@DataProvider(name="PollQuizSetupData")
	public String[][] getTestData(){
		String[][] testRecords = getData("PollQuizSetup.xlsx", "PollQuizSetup");
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

	@Test(priority=0,groups="PQSLogin")
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
		
	@Test(priority=1,groups="PQSTargetPage",dependsOnGroups = {"PQSLogin"})
	public void Target_Page() throws Exception{
		pqs = new PollQuizSetup(driver);
		pqs.Test_Open_Target_Page();
	}
		
	@Test(priority=2,groups="PQSAddNewButton",dependsOnGroups = {"PQSTargetPage"})
	public void AddNew_Button(){
		pqs = new PollQuizSetup(driver);
		pqs.Test_AddNew_Button();
	}
	
	@Test(priority=3,dependsOnGroups = {"PQSAddNewButton"},dataProvider="PollQuizSetupData")
	public void Poll_Quiz_Setup_Data_Upload( String SetupForData, String SetupCodeData, String SelectQuestionBankData, String QuestionTypeData, String statusData, String fromDateData, String TodateData, String ApplicableCriteriaData, String Anonymous, String QuestionSectionData) throws Exception {
		bc = new BaseClass(driver);
		pqs = new PollQuizSetup(driver);
				/*  if(Mode.equalsIgnoreCase("n")){
				throw new SkipException("user marked this record as no run");
			}*/
		pqs.Test_AddNewDataUpload(SetupForData, SetupCodeData, SelectQuestionBankData, QuestionTypeData, statusData, fromDateData, TodateData, ApplicableCriteriaData, Anonymous, QuestionSectionData);
			String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, "Record saved Successfully.");
			log.info("Record Added successfully");
			System.out.println("Record Added successfully");
			ExcelUtils.setCellData("Passed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record has been saved successfully", iTestCaseRow, Constant.Col_ActualResult);
			} catch (AssertionError e) {
				bc.ScrollDown();
				WebElement CancelButton = driver.findElement(By.id("ContentPlaceHolder_CancelButton")); 
				bc.click(CancelButton, "Cancel Button");
			getScreenShot("Poll Quiz Setup");
			ExcelUtils.setCellData("Failed", iTestCaseRow, Constant.Col_Result);
			ExcelUtils.setCellData("Record not added successfully", iTestCaseRow, Constant.Col_ActualResult);
			Assert.fail("Expected : [Record Saved Successfully.] But Found : ["+AlertMsg+"]");
		}
	}
	
	

	
}

	
	

