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

public class CurrencyRateMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Regional Setup") WebElement RegionalSetup;
	@FindBy(linkText="Currency Rate Master") WebElement CurrencyRateMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Regional Setup";
	String pageName = "Currency Rate Master";
	String PageTitle = "Currency Rate Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpbasecurr']") WebElement BaseCurrencyDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpCurrency']") WebElement CurrencyDropdown;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_imgFrom']") WebElement StartDate;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_nextArrow']") WebElement SNextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_prevArrow']") WebElement SPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_title']") WebElement SMiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_monthsBody']//descendant::tr/td/div") List<WebElement> SAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CalendarExtender1_daysBody']//tr//td[@class='']//div") List<WebElement> SAllDates;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_imgToDt']") WebElement ToDate;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calendarButtonExtender_nextArrow']") WebElement TNextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calendarButtonExtender_prevArrow']") WebElement TPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calendarButtonExtender_title']") WebElement TMiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calendarButtonExtender_monthsBody']//descendant::tr/td/div") List<WebElement> TAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calendarButtonExtender_daysBody']//tr//td[@class='']//div") List<WebElement> TAllDates;
		
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRate']") WebElement CurrencyRate;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Currency Rate Master Add new Button";
	String BaseCurrencyName ="Base Currency";
	String CurrencyName ="Currency";
	String CurrencyRateName ="Currency Rate";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
		
	public CurrencyRateMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CurrencyRateMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String BaseCurrencyDropdownData, String CurrencyDropdownData, String FromDateData, String ToDateData, String CurrencyRateData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.waitFixedTime(1);
		bc.selectByVisibleText(BaseCurrencyDropdown, BaseCurrencyDropdownData, BaseCurrencyName);
		bc.waitFixedTime(1);
		bc.selectByVisibleText(CurrencyDropdown, CurrencyDropdownData, CurrencyName);
	
		bc.DateSelection(FromDateData, StartDate, SNextCalender, SMiddleCalender, SPrevCalender, SAllMonth, SAllDates);
		bc.DateSelection(ToDateData, ToDate, TNextCalender, TMiddleCalender, TPrevCalender, TAllMonth, TAllDates);
		bc.SendKeys(CurrencyRate, CurrencyRateData, CurrencyRateName);
		bc.waitFixedTime(1);
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Currency Rate Master Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Currency Rate Master Data Not Added successfully : page crashed");
		}		
	}
	

}
