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

public class CostCenterMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Cost Center Master") WebElement CostCenterMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Cost Center Master";
	String PageTitle = "Cost Center Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCostCenterCode']") WebElement CostCenterCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCostCenterName']") WebElement CostCenterName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_UCSearch1_UC_txtSearch']") WebElement CostCenterHead;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCostCenterRemarks']") WebElement CostCenterRemark;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lblCostCntrHead']") WebElement costCenterHeadLabel;	
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgCostCenter_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="cost center Master Add new Button";
	String CostCenterCodeName ="Cost Center Code";
	String CostCenterNameName ="Cost Center Name";
	String CostCenterHeadName ="Cost Center Head";
	String CostCenterRemarkName ="cost center remark";
	
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public CostCenterMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, CostCenterMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String CostCenterCodeData, String CostCenterNameData, String costCenterHeadLabelData, String CostCenterRemarkData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(CostCenterCode, CostCenterCodeData, CostCenterCodeName);
		bc.SendKeys(CostCenterName, CostCenterNameData, CostCenterNameName);
		bc.SendKeysSearchBoxDropdownValue(CostCenterHead, costCenterHeadLabel, costCenterHeadLabelData, CostCenterHeadName,10);
		bc.SendKeys(CostCenterRemark, CostCenterRemarkData, CostCenterRemarkName);
		
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		}else{}
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
	//	bc.waitFixedTime(2);
		bc.waitForAlertBox(20);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("cost center Master Data added successfully");
		}catch(AssertionError e){
			tb.log("cost center Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, CostCenterCode, CostCenterCodeName, "cs0058", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
