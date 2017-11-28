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

public class CurrencyMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Regional Setup") WebElement RegionalSetup;
	@FindBy(linkText="Currency Master") WebElement CurrencyMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Regional Setup";
	String pageName = "Currency Master";
	String PageTitle = "Currency Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCurrName']") WebElement CurrencyName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCurrSym']") WebElement CurrencySybmol;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtSubCName']") WebElement SubCurrencyName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Active;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Currency Master Add new Button";
	String CurrencyNameName ="Currency Name";
	String CurrencySymbolName ="Currency Symbol Name";
	String SubCurrencyNameName ="Sub Currency Name";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public CurrencyMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CurrencyMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String CurrencyNameData, String CurrencySybmolData, String SubCurrencyNameData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		
		bc.SendKeys(CurrencyName, CurrencyNameData, CurrencyNameName);
		bc.SendKeys(CurrencySybmol, CurrencySybmolData, CurrencySymbolName);
		bc.SendKeys(SubCurrencyName, SubCurrencyNameData, SubCurrencyNameName);
		
		if(ActiveData.equalsIgnoreCase("Yes")){
		bc.click(Active, ActiveName);
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Currency Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Currency Master Data Not Added successfully : page crashed");
		}		
	}
	

}
