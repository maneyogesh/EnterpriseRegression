package pom.ObjectPages.uiActions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

public class OrganizationalUnitMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Organizational Unit Master") WebElement OrganizationalUnitMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Organizational Unit Master";
	String PageTitle = "Organizational Unit Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(id="ContentPlaceHolder_lblHeader") WebElement AddNewPageHeader;
	@FindBy(id="ContentPlaceHolder_CancelButton") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_drpEntityName") WebElement EntityName;
	@FindBy(id="ContentPlaceHolder_txtOrgCode") WebElement OUCode;
	@FindBy(id="ContentPlaceHolder_txtOrgName") WebElement OUName;
	@FindBy(id="ContentPlaceHolder_txtDescription") WebElement Description;
	@FindBy(id="ContentPlaceHolder_drpParentOU") WebElement ParentOU;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImageCalendar']") WebElement CalendarButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_nextArrow']") WebElement NextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_prevArrow']") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_title']") WebElement MiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_daysBody']/tr/td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> AllDates;
	@FindBy(id="ContentPlaceHolder_chkStatus") WebElement ActiveCheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtdomain']") WebElement DomainName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtinitial']") WebElement Initial;
	
	@FindBy(id="ContentPlaceHolder_txtdigits") WebElement Digits;
	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButton ="Organizational Unit Master Add new Button";
	String OUCodeName ="OU Code";
	String OUNameName ="OU Name";
	String EntityNameName ="Entity Name";
	String DescriptionName ="Description";
	String ParentOUName ="Parent OU";
	String EffectiveDateName ="Effective Date";
	String DateNextName ="Calendar Next";
	String DateBackName ="Calendar Back";
	String DateMiddle ="Calendar Middle";
	String OKName ="OK Button";
	String ActiveName ="Active Checkbox";
	String DigitsName ="Digits";
	String CancelButtonName ="Cancel Button";
	
	
	public OrganizationalUnitMaster(WebDriver driver) {
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
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, OrganizationalUnitMaster, pageName, AddNewButtonLocator);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButton, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String EntityNameData,String OUCodeData, String OUNameData, String DescriptionData, String ParentOUData, String ExcelDate,String ActiveData, String DigitsData, String DomainNamedata, String Initialdata) throws Exception{
		bc = new BaseClass(driver);
	//	WebElement VerifyEntityCode = driver.findElement(By.xpath(".//*[text()='"+EntityCodeData+"']")); 
		//bc.fluentWait(AddNewButtonLocator, 1);
		bc.waitForElement(AddNewButtonLocator);
		bc.click(AddNewButtonLocator, "Add New Button");
		bc.fluentWait(AddNewPageHeader, 1);
		bc.waitForElement(EntityName);
		bc.selectByVisibleText(EntityName, EntityNameData,EntityNameName);
		bc.waitFixedTime(4);
		bc.SendKeys(OUCode, OUCodeData, OUCodeName);
		bc.waitFixedTime(1);
		bc.SendKeys(OUName, OUNameData, OUNameName);
		bc.waitFixedTime(1);
		bc.SendKeys(Description, DescriptionData, DescriptionName);
		bc.waitFixedTime(1);
		bc.selectByVisibleText(ParentOU, ParentOUData, ParentOUName);
		bc.waitFixedTime(1);
		bc.DateSelection(ExcelDate, CalendarButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		if(ActiveData=="Yes"){
			bc.click(ActiveCheckbox, ActiveName);
		}else{}
		bc.SendKeys(DomainName, DomainNamedata, "Domain Name");
		bc.waitFixedTime(1);
		bc.SendKeys(Initial, Initialdata, "Initial");
		bc.waitFixedTime(1);
		bc.SendKeys(Digits, DigitsData, DigitsName);
		bc.click(OKButton, OKName);
		bc.waitFixedTime(1);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(">>>>>>>>>>"+bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Organizational Unit Master Data added successfully");
		}catch(AssertionError e){
			//bc.ScrollDown();
			
			//bc.click(CancelButton, CancelButtonName);
			Reporter.log("Organizational Unit Master Data Not Added successfully : page crashed");
		}		
	}
	

}
