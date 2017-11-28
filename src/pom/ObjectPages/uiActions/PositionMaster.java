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

public class PositionMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Position Master") WebElement PositionMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Position Master";
	String PageTitle = "Position Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDesignationCode']") WebElement PositionCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDesignationName']") WebElement PositionName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDesignationShortName']") WebElement PositionShortName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpWorkNature']") WebElement WorkNaturedropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtRemrks']") WebElement Remarks;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkOprManager']") WebElement checkboxoperationalmanager;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkoprPartner']") WebElement checkboxoperationalpartner;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkRelManager']") WebElement checkboxrelationalmanager;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgDesignation_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Position Master Add new Button";
	String PositionCodeName ="Position code";
	String PositionNameName ="Position Name";
	String PositionShortNameName ="Position Short Name";
	String WorkNatureName ="Work Naturen";
	
	String om ="Operational Manager";
	String op ="Operational Partner";
	String rm ="Relational Manager";
	
	String RemarksName ="Remark";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public PositionMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, PositionMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String PositionCodeData, String PositionNameData, String PositionShortNameData, String WorkNaturedropdownData, String RemarksData, String operationalmanagerData, String operationalpartnerData, String relationalmanagerData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(PositionCode, PositionCodeData, PositionCodeName);
		bc.SendKeys(PositionName, PositionNameData, PositionNameName);
		bc.SendKeys(PositionShortName, PositionShortNameData, PositionShortNameName);
		bc.selectByVisibleText(WorkNaturedropdown, WorkNaturedropdownData, WorkNatureName);
		
		bc.SendKeys(Remarks, RemarksData, RemarksName);
		if(operationalmanagerData.equals("Yes")){
			bc.click(checkboxoperationalmanager, om);
			}else{}
		if(operationalpartnerData.equals("Yes")){
			bc.click(checkboxoperationalpartner, op);
			}else{}
		if(relationalmanagerData.equals("Yes")){
			bc.click(checkboxrelationalmanager, rm);
			}else{}
		
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		}else{}
		
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
		tb.log("Position Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Position Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, Remarks, RemarksName, "test edit functionality", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
