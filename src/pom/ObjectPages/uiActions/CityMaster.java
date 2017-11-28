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

public class CityMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Regional Setup") WebElement RegionalSetup;
	@FindBy(linkText="City Master") WebElement CityMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Regional Setup";
	String pageName = "City Master";
	String PageTitle = "City Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpCountry']") WebElement CountryDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpstate']") WebElement StateDropdowm;
	@FindBy(id="ContentPlaceHolder_txtCityCode") WebElement CityCode;
	@FindBy(id="ContentPlaceHolder_txtCityName") WebElement CityName;
	@FindBy(id="ContentPlaceHolder_ChkActive") WebElement ActiveCheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="City Master Add new Button";
	String CountryName ="Country";
	String StateName ="State";
	String CityCodeName ="City Code Name";
	String CityNameName ="City Name Name";
	String Activecheckboxname ="Active Checkbox";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public CityMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String CountryData, String StateData, String CityCodeData, String CityNameData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.selectByVisibleText(CountryDropdown, CountryData, CountryName);
		//System.out.println(CountryData);
		bc.waitFixedTime(2);
		bc.selectByVisibleText(StateDropdowm, StateData, StateName);
		bc.waitFixedTime(1);
		bc.SendKeys(CityCode, CityCodeData, CityCodeName);
		bc.SendKeys(CityName, CityNameData, CityNameName);
		
		if(ActiveData.equals("Yes")){
			bc.click(ActiveCheckbox, Activecheckboxname);}
		else{
			}
				
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitFixedTime(1);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.AlertBoxPresentORNot(), "Alert Box Not Present : page crashed");
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("City Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("City Master Data Not Added successfully : page crashed");
			Assert.fail("Record Not saved successfully OR Page Crashed : Alert Box Not Present");
		}		
	}
	

}
