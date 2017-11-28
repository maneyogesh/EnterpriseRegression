package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;
import pom.utility.Log;

public class UploadMasterRevised extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Upload Master Revised") WebElement UploadMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Upload Master";
	String PageTitle = "Upload Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_drpSearchList") WebElement Searchdropdown;
	@FindBy(id="ContentPlaceHolder_txtSearchText") WebElement SearchTextBox;
	@FindBy(id="ContentPlaceHolder_cmdSearch") WebElement SearchButton;
	
	@FindBy(id="ContentPlaceHolder_btnCommit1") WebElement CommitButton;
	@FindBy(id="ContentPlaceHolder_btnRollback1") WebElement RollBackbutton;
	@FindBy(id="ContentPlaceHolder_Imagecolse1") WebElement Cancelbutton;
		
	String SearchDropdownName ="Search Dropdown";
	String SearchBoxName ="Search TextBox";
	String SearchButtonName ="Search Button";
	String UploadTypeName ="Upload Type";
	String UploadFileName ="Upload File";
	String UploadButtonName ="Upload Button";
	String CommitButtonName ="Commit Button";
	String RollBackButtonName ="RollBack Button";
	String CancelButtonName ="Cancel Button";
	String VerifyUploadButtonName ="Verify Upload Section";
		
	public UploadMasterRevised(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\EnterpriseSetup_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","EnterpriseSetup");
		lp = new LoginPage(driver);
		lp.Login(map.get(0).get("UserName"), map.get(0).get("Password"), map.get(0).get("CompanyCode"));
	}
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, UploadMaster, pageName, SearchButton);
		//cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, UploadMaster, pageName, PageTitle);
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(Searchdropdown, dropdown_Value, SearchDropdownName, SearchTextBox, SearchData, SearchButton, PageTitle);
	}
	
	public void Test_data_upload(String UploadFileNameData,String UploadTypeText, String EXEFilePath) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		WebElement UploadType = driver.findElement(By.xpath(".//*[text()='"+UploadFileNameData+"']/parent::td/parent::tr/td/select"));
		WebElement UploadFile = driver.findElement(By.xpath(".//*[text()='"+UploadFileNameData+"']/parent::td/parent::tr/td/input[@type='file']"));
		WebElement UploadButton = driver.findElement(By.xpath(".//*[text()='"+UploadFileNameData+"']/parent::td/parent::tr/td/input[@value='Upload']"));
		
		bc.waitForElement(UploadType);
		bc.selectByVisibleText(UploadType, UploadTypeText, UploadTypeName);
		bc.waitForElement(UploadFile);
		bc.click(UploadFile, UploadFileName);
		////////////////
		Runtime.getRuntime().exec(EXEFilePath);
		////////////////
		bc.waitFixedTime(1);
		bc.click(UploadButton, UploadButtonName);
		bc.waitForAlertBox(300);
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		//bc.waitFixedTime(3);
	//	String AlertMsg = bc.closeAlertAndGetItsText();
		if(bc.AlertBoxText().equalsIgnoreCase("Record Uploaded Successfully .")){
			bc.AlertAcceptIfPresent();
			System.out.println("Data upload Successfully");
			Log.info("Data upload Successfully");
			Reporter.log("Data Upload Successfully");
			bc.waitFixedTime(1);
			//---------> commit data ------------>
			WebElement VerifyUploadButton = driver.findElement(By.xpath(".//*[text()='"+UploadFileNameData+"']/parent::td/parent::tr/td/a[@title='Click here to Verify']"));
			bc.waitForElement(VerifyUploadButton);
			bc.click(VerifyUploadButton, VerifyUploadButtonName);
			bc.waitForElement(CommitButton);
			bc.click(CommitButton, CommitButtonName);
		//	String AlertMsg2 = bc.closeAlertAndGetItsText();
			if(bc.AlertBoxText().equalsIgnoreCase("Records committed successfully.")){
				System.out.println("Data Commited Successfully");
				Log.info("Data Commited Successfully");
				Reporter.log("Data Commited Successfully");
				}
			else if(bc.AlertBoxText().equalsIgnoreCase("Zero record Commited .")){
				bc.AlertAcceptIfPresent();
				WebElement VerifyUploadButton1 = driver.findElement(By.xpath(".//*[text()='"+UploadFileNameData+"']/parent::td/parent::tr/td/a[@title='Click here to Verify']"));
				bc.click(VerifyUploadButton1, VerifyUploadButtonName);
				bc.waitForElement(RollBackbutton);
				bc.click(RollBackbutton, RollBackButtonName);
			}
				else {
					tb.log("data neither committed nor rollback");
				}
		
		}
		else{
			tb.log("Invalid File Format");
			
		}
			
	}	
	

}
