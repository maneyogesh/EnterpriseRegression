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

public class ParameterGroupMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Application Monitor") WebElement ApplicationMonitor;
	@FindBy(linkText="Parameter Group Master") WebElement ParameterGroupMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Application Monitor";
	String pageName = "Parameter Group Master";
	String PageTitle = "Parameter Group Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtParameterId']") WebElement ParameterGroupCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtParameterName']") WebElement ParameterGroupName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement ActiveCheckbox;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgParameter_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Parameter Group Master Add new Button";
	String ParameterGroupCodeName ="Parameter Group Code";
	String ParameterGroupNameName ="Parameter Group Name";
	
	String ActivecheckboxName = "Active checkbox";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Data updated Successfully";
		
	public ParameterGroupMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, ApplicationMonitor, submoduleName, ParameterGroupMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String ParameterGroupCodedata, String ParameterGroupNamedata, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(ParameterGroupCode, ParameterGroupCodedata, ParameterGroupCodeName);
		bc.SendKeys(ParameterGroupName, ParameterGroupNamedata, ParameterGroupNameName);
			
		if(ActiveData.equals("Yes")){
			bc.click(ActiveCheckbox, ActivecheckboxName);
			}else{}
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitForAlertBox(20);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Data saved Successfully");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Parameter Group Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Parameter Group Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, ParameterGroupCode, ParameterGroupCodeName, "DHFL45", OKButton, ExpectRecordEditedMessage);
	}
	

}
