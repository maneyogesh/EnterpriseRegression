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

public class EntityMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Entity Master") WebElement EntityMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Entity Master";
	String PageTitle = "Entity Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(id="ContentPlaceHolder_lblHeader") WebElement AddNewPageHeader;
	@FindBy(id="ContentPlaceHolder_CancelButton") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_txtEntityCode") WebElement EntityCode;
	@FindBy(id="ContentPlaceHolder_txtEntityName") WebElement EntityName;
	@FindBy(id="ContentPlaceHolder_txtDescription") WebElement EntityDescription;
	@FindBy(id="ContentPlaceHolder_drpParentEntity") WebElement ParentEntity;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImageCalendar']") WebElement CalendarButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_nextArrow']") WebElement NextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_prevArrow']") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_title']") WebElement MiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarControl_daysBody']//tr//td[@class='']//div") List<WebElement> AllDates;
	@FindBy(id="ContentPlaceHolder_chkStatus") WebElement ActiveCheckbox;
	@FindBy(id="ContentPlaceHolder_chkCompanyLevel") WebElement CompanyLevel;
	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButton ="Entity Master Add new Button";
	String EntityCodeName ="Entity Code";
	String EntityNameName ="Entity Name";
	String DescriptionName ="Description";
	String ParentEntityName ="Parent Entity";
	String EffectiveDateName ="Effective Date";
	String DateNextName ="Calendar Next";
	String DateBackName ="Calendar Back";
	String DateMiddle ="Calendar Middle";
	String OKName ="OK Button";
	String ActiveName ="Active Checkbox";
	String CompanyLevelName ="Company Level Checkbox";
	String CancelButtonName ="Cancel Button";
		
	public EntityMaster(WebDriver driver) {
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
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, EntityMaster, pageName, PageHeader);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButton, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String EntityCodeData,String EntityNameData, String EntityDescriptionDate, String ParentEntityDate, String Exceldate, String ActiveData, String CompanyLevelData) throws Exception{
		bc = new BaseClass(driver);
	//	WebElement VerifyEntityCode = driver.findElement(By.xpath(".//*[text()='"+EntityCodeData+"']")); 
		bc.fluentWait(AddNewButtonLocator, 1);
		bc.click(AddNewButtonLocator, "Add New Button");
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(EntityCode, EntityCodeData,EntityCodeName);
		bc.SendKeys(EntityName, EntityNameData, EntityNameName);
		bc.SendKeys(EntityDescription, EntityDescriptionDate, DescriptionName);
		bc.selectByVisibleText(ParentEntity, ParentEntityDate, ParentEntityName);
		bc.DateSelection(Exceldate, CalendarButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		if(ActiveData=="Yes"){
			bc.click(ActiveCheckbox, ActiveName);
		}else{}
		if(CompanyLevelData=="Yes"){
			bc.click(CompanyLevel, CompanyLevelName);
		}else{}
		bc.click(OKButton, OKName);
	//	bc.closeAlertAndGetItsText();
		String AlertMsg = bc.closeAlertAndGetItsText();
		System.out.println(">>>>>>>>"+AlertMsg+"<<<<<<<<");
	//bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(AlertMsg, "Record Saved Successfully.");
	//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Entity Master Data added successfully");
		}catch(AssertionError e){
			bc.ScrollDown();
			bc.click(CancelButton, CancelButtonName);
			Reporter.log("Entity Master Data Not Added successfully : page crashed");
			Assert.fail("Expected : 'Record Saved Successfully.' But Found : "+AlertMsg);
		}
		
	}
	

}
