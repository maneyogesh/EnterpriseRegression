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

public class StateMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Regional Setup") WebElement RegionalSetup;
	@FindBy(linkText="State Master") WebElement StateMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Regional Setup";
	String pageName = "State Master";
	String PageTitle = "State Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_UCountryName_UC_txtSearch") WebElement CountrytextBox;
	@FindBy(id="ContentPlaceHolder_txtCountryCode") WebElement StateCode;
	@FindBy(id="ContentPlaceHolder_txtCountryName") WebElement StateName;
	@FindBy(id="ContentPlaceHolder_ChkActive") WebElement Activecheckbox;
	@FindBy(id="ContentPlaceHolder_PTApplicable") WebElement PTApplicable;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="State Master Add new Button";
	String CountryName ="Country";
	String StateCodeName ="State Code Name";
	String StateNameName ="State Name";
	String Activecheckboxname ="Active Checkbox";
	String PTApplicableName ="PT Applicable";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public StateMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, StateMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, StateMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String CountryData, String StateCodeData, String StateNameData, String statusData, String PTApplicableData) throws Exception{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeysSearchBoxDropdownValue(CountrytextBox, CountrytextBox, CountryData, CountryName,3);
		bc.SendKeys(StateCode, StateCodeData, StateCodeName);
		bc.SendKeys(StateName, StateNameData, StateNameName);
		
		if(statusData.equals("Yes")){
			bc.click(Activecheckbox, Activecheckboxname);}
		else{
			}
		if(PTApplicableData.equals("Yes")){
			bc.click(PTApplicable, PTApplicableName);}
		else{
			}
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("State Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("State Master Data Not Added successfully : page crashed");
		}		
	}
	

}
