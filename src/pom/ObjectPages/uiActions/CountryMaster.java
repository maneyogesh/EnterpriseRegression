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

public class CountryMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Regional Setup") WebElement RegionalSetup;
	@FindBy(linkText="Country Master") WebElement CountryMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Regional Setup";
	String pageName = "Country Master";
	String PageTitle = "Country Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_txtCountryCode") WebElement CountryCode;
	@FindBy(id="ContentPlaceHolder_txtCountryName") WebElement CountryName;
	@FindBy(id="ContentPlaceHolder_txtTimeDiff") WebElement TimeDifference;
	@FindBy(id="ContentPlaceHolder_ChkActive") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Country Master Add new Button";
	String CountryCodeName ="Country Code";
	String CountryNameName ="Country Name";
	String TimeDifferenceName ="Time Difference";
	String Activecheckboxname ="Active Checkbox";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	
	
	public CountryMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CountryMaster, pageName, PageHeader);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String CountryCodeData, String CountryNameData, String TimeDifferenceData, String statusData) throws Exception{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(CountryCode, CountryCodeData, CountryCodeName);
		bc.SendKeys(CountryName, CountryNameData, CountryNameName);
		bc.SendKeys(TimeDifference, TimeDifferenceData, TimeDifferenceName);
		
		if(statusData=="No"){}
		else	{
			bc.click(Activecheckbox, Activecheckboxname);
			}
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Country Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Country Master Data Not Added successfully : page crashed");
		}		
	}
	

}
