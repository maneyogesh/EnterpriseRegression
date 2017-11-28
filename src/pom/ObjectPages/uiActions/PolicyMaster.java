package pom.ObjectPages.uiActions;

import java.util.List;

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

public class PolicyMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Policy Master") WebElement PolicyMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Policy Master";
	String PageTitle = "Policy Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_lnkBAddNewH") WebElement AddNewButtonLocator;
	@FindBy(id="ContentPlaceHolder_pnlHead") WebElement AddNewPageHeader;
	@FindBy(id="ContentPlaceHolder_CancelButton") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_txtPolicyName") WebElement PolicyName;
	@FindBy(id="ContentPlaceHolder_txtPolDescription") WebElement PolicyDescription;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_imgFrom']") WebElement CalendarButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalfromDt_nextArrow']") WebElement NextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalfromDt_prevArrow']") WebElement PrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalfromDt_title']") WebElement MiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalfromDt_monthsBody']//descendant::tr/td/div") List<WebElement> AllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalfromDt_daysBody']//tr//td[@class='']//div") List<WebElement> AllDates;
	@FindBy(id="ContentPlaceHolder_chkPolActive") WebElement ActiveCheckbox;
	@FindBy(id="ContentPlaceHolder_FUPPOL") WebElement BrowseButton;
	
	@FindBy(id="ContentPlaceHolder_lnkSaveH") WebElement OKButton;
	
	String AddNewButton ="Policy Master Add new Button";
	String PolicyNameName ="Policy Name";
	String PolicyDescriptionName ="Policy Description";
	String BrowseButtonName ="PDF Upload";
	String EffectiveDateName ="Effective Date";
	String DateNextName ="Calendar Next";
	String DateBackName ="Calendar Back";
	String DateMiddle ="Calendar Middle";
	String OKName ="OK Button";
	String ActiveName ="Active Checkbox";
	String CancelButtonName ="Cancel Button";
	
	
	public PolicyMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, PolicyMaster, pageName, PageHeader);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButton, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String PolicyNameData,String PolicyDescriptionData, String ExcelDate, String EXEFilePath, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
	//	WebElement VerifyEntityCode = driver.findElement(By.xpath(".//*[text()='"+EntityCodeData+"']")); 
		bc.fluentWait(AddNewButtonLocator, 1);
		bc.click(AddNewButtonLocator, "Add New Button");
		bc.fluentWait(AddNewPageHeader, 1);
		bc.waitForElement(PolicyName);
		bc.SendKeys(PolicyName, PolicyNameData, PolicyNameName);
		bc.SendKeys(PolicyDescription, PolicyDescriptionData, PolicyDescriptionName);
		bc.waitFixedTime(1);
		bc.DateSelection(ExcelDate, CalendarButton, NextCalender, MiddleCalender, PrevCalender, AllMonth, AllDates);
		bc.click(BrowseButton, BrowseButtonName);
		////////////////
		Runtime.getRuntime().exec(EXEFilePath);
		////////////////
		if(ActiveData=="Yes"){
			bc.click(ActiveCheckbox, ActiveName);
		}else{}
		bc.waitFixedTime(1);
		bc.click(OKButton, OKName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(">>>>>>>>>>"+bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Policy Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Policy Master Data Not Added successfully : page crashed");
		}		
	}
	

}
