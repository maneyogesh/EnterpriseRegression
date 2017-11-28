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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import pom.excelReader.Mapping.ReadExcelMapping;
import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;

public class OnBehalfLeaveApplication extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(id="ImgBtn_HR") WebElement HRMS;
	@FindBy(linkText="Employee Self Service") WebElement ESS;
	@FindBy(linkText="Leave & Attendance") WebElement LeaveandAttendance;  
	@FindBy(linkText="On Behalf Leave Application") WebElement OnBehalfLeaveApplication;
	
	String module = "Human Resource";
	String submoduleName = "ESS";
	String subsectionName = "Leave and Attendance";
	String pageName = "On Behalf Leave Application";
	String PageTitle = "On Behalf Leave Application";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	
	
	public OnBehalfLeaveApplication(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\EnterpriseSetup_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","LeaveAndAttendance");
		lp = new LoginPage(driver);
		lp.Login(map.get(8).get("UserName"), map.get(8).get("Password"), map.get(8).get("CompanyCode"));
	}
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPage(HRMS, module, ESS, submoduleName, LeaveandAttendance, subsectionName, OnBehalfLeaveApplication, pageName, PageTitle);	
	}
	
	public void Test_EditEmployee(String EmpCode){
		bc = new BaseClass(driver);
		WebElement EditEmp = driver.findElement(By.xpath(".//*[text()='"+EmpCode+"']/parent::tr/td/input"));
		EditEmp.click();
		bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.isElementPresentSingleLocator(PageHeader));		
	}
	
}
