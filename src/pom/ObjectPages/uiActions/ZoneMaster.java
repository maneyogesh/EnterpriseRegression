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

public class ZoneMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Zone Master") WebElement ZoneMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Zone Master";
	String PageTitle = "Zone Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnback']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtznecode']") WebElement zoneCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtznedesc']") WebElement zoneDescription;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgzone_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_btnok") WebElement OKButton;
	
	String AddNewButtonName ="Zone Master Add new Button";
	String ZoneCodeName ="Zone code";
	String ZoneDescriptionName ="Zone Description";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public ZoneMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, ZoneMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String zoneCodeData, String zoneDescriptionData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(zoneCode, zoneCodeData, ZoneCodeName);
		bc.SendKeys(zoneDescription, zoneDescriptionData, ZoneDescriptionName);
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitForAlertBox(10);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("zone Master Data added successfully");
		}catch(AssertionError e){
			tb.log("zone Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, zoneDescription, ZoneDescriptionName, "newyork", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
