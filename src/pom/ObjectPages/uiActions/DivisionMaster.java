package pom.ObjectPages.uiActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pom.TestBase.BaseClass;
import pom.TestBase.CommonMethods;
import pom.TestBase.LoginPage;
import pom.TestBase.TestBase;
import pom.excelReader.Mapping.ReadExcelMapping;

public class DivisionMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Division Master") WebElement DivisionMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Division Master";
	String PageTitle = "Division Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDivCode']") WebElement DivisionCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDivName']") WebElement DivisionName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSecSearch_UC_txtSearch']") WebElement DivisionSecretary;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtHeadSearch_UC_txtSearch']") WebElement DivisionHead;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMngrSearch_UC_txtSearch']") WebElement DivisionManager;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRemark']") WebElement Remarks;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Division Master Add new Button";
	String DivisionCodeName ="Division code";
	String DivisionNameName ="Division Name";
	String DivisionSecretaryName ="Division Secretary";
	String DivisionHeadName ="Divsion Head";
	String DivisionManagerName ="Division Manager";
	String RemarkName ="Remark";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public DivisionMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, DivisionMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String DivisionCodeData, String DivisionNameData, String DivisionSecretaryData, String DivisionHeaddata, String DivisionManagerData,  String RemarkData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.waitFixedTime(1);
		bc.SendKeys(DivisionCode, DivisionCodeData, DivisionCodeName);
		bc.SendKeys(DivisionName, DivisionNameData, DivisionNameName);
		bc.SendKeysSearchBoxDropdownValue(DivisionManager, DivisionManager,DivisionManagerData, DivisionManagerName,3);
		bc.waitFixedTime(1);
	//	bc.SendKeys(DivisionHead, DivisionSecretaryData, DivisionHeadName);
		bc.SendKeysSearchBoxDropdownValue(DivisionHead, DivisionHead,DivisionHeaddata, DivisionHeadName,3);
	//	bc.SendKeysSearchBoxDropdownValueDirect(DivisionHead, DivisionHeaddata, DivisionHeadName, 3);
		bc.waitFixedTime(1);
	//	bc.SendKeys(DivisionManager, DivisionManagerData, DivisionManagerName);
		bc.SendKeysSearchBoxDropdownValue(DivisionSecretary, DivisionSecretary, DivisionSecretaryData, DivisionSecretaryName,3);
	//	bc.SendKeysSearchBoxDropdownValueDirect(DivisionManager, DivisionManagerData, DivisionManagerName, 3);
		bc.waitFixedTime(1);
		bc.SendKeys(Remarks, RemarkData, RemarkName);
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitFixedTime(3);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Division Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Division Master Data Not Added successfully : page crashed");
		}		
	}
	

}
