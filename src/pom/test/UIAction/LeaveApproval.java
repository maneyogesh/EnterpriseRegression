package pom.test.UIAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import pom.excelReader.Mapping.ReadExcelMapping;
import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;

public class LeaveApproval extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(id="ImgBtn_HR") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="Leave Approval") WebElement LeaveApproval;
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "Leave Approval";
	String PageTitle = "Leave Approval";
	
	@FindBy(id="ContentPlaceHolder_drpSearchList") WebElement searchdropdown;
	@FindBy(id="ContentPlaceHolder_txtSearchText") WebElement SearchTextBox;
	@FindBy(id="ContentPlaceHolder_cmdSearch") WebElement SearchButton;
	@FindBy(id="ContentPlaceHolder_dgApprover_ChkApproval_0") WebElement Checkbox1st;
	@FindBy(id="ContentPlaceHolder_dgApprover_ddlStatus_0") WebElement StatusDropdown;
	@FindBy(id="ContentPlaceHolder_btnApproved") WebElement SubmitButton;
	@FindBy(id="ContentPlaceHolder_dgApprover_txtRemarks_0") WebElement RemarkBox;
	
	String SearchDropdown ="Leave Approval Dropdown";
		
	public LeaveApproval(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login_1stlevelApproval() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\EnterpriseSetup_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(1).get("UserName"), map.get(1).get("Password"), map.get(1).get("CompanyCode"));
	}
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, LeaveApproval, pageName, PageTitle);	
	}
	
	public void Test_Search_Record(String dropdown_Value, String SearchData) throws Exception {
		cm = new CommonMethods(driver);
		cm.Test_Search_Button(searchdropdown, dropdown_Value, SearchDropdown, SearchTextBox, SearchData, SearchButton, PageTitle);
	}
	
	public void Test_LeaveApproveReject(String dropdown_Value, String SearchData,String Date, String StatusData) throws Exception{
		bc = new BaseClass(driver);
		this.Test_Search_Record(dropdown_Value, SearchData);
		bc.waitFixedTime(1);
		try{
		WebElement checkboxwithdate = driver.findElement(By.xpath(".//*[text()='"+Date+"']/parent::td/parent::tr/td/input[@type='checkbox']"));
		
		checkboxwithdate.click();
		Reporter.log("open leave approval page -> search record with leave status -> click on "+Date+"check box");
		}catch(Exception e){
			throw new SkipException(Date+"checkbox is not found");
		}
		bc.waitFixedTime(1);
		try{
			WebElement StatusDropdownwithdate = driver.findElement(By.xpath(".//*[text()='"+Date+"']/parent::td/parent::tr/td/select"));
			Select s = new Select(StatusDropdownwithdate);
			s.selectByVisibleText(StatusData);
			Reporter.log("select status");
		}catch(Exception e){
			throw new SkipException(Date+"dropdown is not found");
		}
		bc.selectByVisibleText(StatusDropdown, StatusData, "Status");
		
		
		try{
			Assert.assertEquals(StatusData, "Reject By Reporting Manager");
			try{
				WebElement remarkBoxwithDate = driver.findElement(By.xpath(".//*[text()='"+Date+"']/parent::td/parent::tr/td/textarea"));
				remarkBoxwithDate.sendKeys("Test Data");
				Reporter.log("Enter Remark : Test Data");
				}catch(Exception e){
					throw new SkipException(Date+"Remark box is not found");
				}
			bc.ScrollDown();
			bc.click(SubmitButton, "Submit");
			Reporter.log("clicked on submit button");
		}catch(AssertionError e){
			bc.ScrollDown();
			bc.click(SubmitButton, "Submit");
			Reporter.log("clicked on submit button");
		}
				
	}
}
