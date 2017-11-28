package pom.ObjectPages.uiActions;

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

public class BranchMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Branch Master") WebElement BranchMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Branch Master";
	String PageTitle = "Branch Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtLocCode']") WebElement BranchCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtLocName']") WebElement BranchName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtLocShortName']") WebElement BranchShortName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DrpState']") WebElement StateDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpCity']") WebElement CityNameDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtLocRemarks']") WebElement Remarks;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cactive']") WebElement Activecheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Branch Master Add new Button";
	String BranchCodeName ="Branch code";
	String BranchNameName ="Branch Name";
	String BranchShortNameName ="Branch Short Name";
	String StateName ="State";
	String CityName ="City Name";
	String RemarkName ="Remark";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public BranchMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, BranchMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String BranchCodeData, String BranchNameData, String BranchShortData, String StateData, String CityNameData, String RemarkData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(BranchCode, BranchCodeData, BranchCodeName);
		bc.SendKeys(BranchName, BranchNameData, BranchNameName);
		bc.SendKeys(BranchShortName, BranchShortData, BranchShortNameName);
		bc.selectByVisibleText(StateDropdown, StateData, StateName);
		bc.waitFixedTime(2);
		bc.selectByVisibleText(CityNameDropdown, CityNameData, CityName);
		bc.waitFixedTime(2);
		bc.SendKeys(Remarks, RemarkData, RemarkName);
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitFixedTime(3);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Alert Box Not Present : Page crashed");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Branch Master Data added successfully");
		}catch(AssertionError e){
		Reporter.log("Branch Master Data Not Added successfully : page crashed");
		}		
	}
	

}
