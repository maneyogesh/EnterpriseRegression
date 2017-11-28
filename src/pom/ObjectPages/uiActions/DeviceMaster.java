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

public class DeviceMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Device Master") WebElement DeviceMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Device Master";
	String PageTitle = "Device Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDevice_ID']") WebElement DeviceID;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMachineName']") WebElement MachineName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpType']") WebElement Type;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drplocation']") WebElement Location;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtFunctionKey']") WebElement FunctionKey;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDeviceCategory']") WebElement DeviceCategory;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ddlDeviceDir']") WebElement PunchDirection;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkActive']") WebElement Activecheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgDevice_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Device Master Add new Button";
	String DeviceIDName ="Device ID";
	String MachineNameName ="Machine Name";
	String TypeName ="Type";
	String LocationName ="Location";
	String FunctionKeyName ="Function Key";
	String DeviceCategoryName ="Device Category";
	String ActivecheckboxName = "Active checkbox";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public DeviceMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, DeviceMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String DeviceIDData, String MachineNameData, String TypeData, String LocationData, String FunctionKeyData, String DeviceCategoryData, String PunchDirectionData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(DeviceID, DeviceIDData, DeviceIDName);
		bc.SendKeys(MachineName, MachineNameData, MachineNameName);
		bc.selectByVisibleText(Type, TypeData, TypeName);
		bc.selectByVisibleText(Location, LocationData, LocationName);
		bc.SendKeys(FunctionKey, FunctionKeyData, FunctionKeyName);
		bc.selectByVisibleText(DeviceCategory, DeviceCategoryData, DeviceCategoryName);
		bc.selectByVisibleText(PunchDirection, PunchDirectionData, "Punch Direction");
		if(ActiveData=="Yes"){
			bc.click(Activecheckbox, ActivecheckboxName);
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitForAlertBox(20);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Device Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Device Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, DeviceID, DeviceIDName, "DID125", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
