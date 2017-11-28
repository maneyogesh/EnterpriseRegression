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

public class DepartmentMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Department Master") WebElement DepartmentMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Department Master";
	String PageTitle = "Department Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDepartmentCode']") WebElement DepartmentCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDepartmentName']") WebElement DepartmentName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDepartmentShortName']") WebElement DepartmentShortName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_UCSearch1_UC_txtSearch']") WebElement DepartmentHead;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_UCSearch2_UC_txtSearch']") WebElement DepartmentSecretary;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtDepartmentRemarks']") WebElement DepartmentRemarks;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDivision']") WebElement Division;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DrpCostCenter']") WebElement CostCenter;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgDepartment_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Department Master Add new Button";
	String DepartmentCodeName ="Department code";
	String DepartmentNameName ="Department Name";
	String DepartmentShortNameName ="Department Short Name";
	String DepartmentHeadName ="Department Head";
	String DepartmentSecretaryName ="Department Scretary";
	String DepartmentRemarkName ="Remark";
	String DivisionName ="Division";
	String CostCenterName ="Cost Center";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public DepartmentMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, DepartmentMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String DepartmentCodeData, String DepartmentNameData, String DepartmentShortNameData, String DepartmentHeadData, String DepartmentSecretarydata,  String DepartmentRemarksData, String DivisionData, String CostCenterData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(DepartmentCode, DepartmentCodeData, DepartmentCodeName);
		bc.SendKeys(DepartmentName, DepartmentNameData, DepartmentNameName);
		bc.SendKeys(DepartmentShortName, DepartmentShortNameData, DepartmentShortNameName);
		bc.SendKeysSearchBoxDropdownValue(DepartmentHead, DepartmentHead, DepartmentHeadData, DepartmentHeadName,3);
		bc.waitFixedTime(2);
		bc.SendKeysSearchBoxDropdownValue(DepartmentSecretary, DepartmentSecretary, DepartmentSecretarydata, DepartmentSecretaryName,3);
		bc.waitFixedTime(2);
		bc.SendKeys(DepartmentRemarks, DepartmentRemarksData, DepartmentRemarkName);
		bc.selectByVisibleText(Division, DivisionData, DivisionName);
		bc.waitFixedTime(1);
		bc.selectByVisibleText(CostCenter, CostCenterData, CostCenterName);
		bc.waitFixedTime(1);
		if(ActiveData.equals("Yes")){
		bc.click(Activecheckbox, ActiveName);
		
		}else{}
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitFixedTime(2);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Department Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Department Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, DepartmentCode, DepartmentCodeName, "458", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
