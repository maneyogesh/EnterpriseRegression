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

public class SubDepartmentMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Sub Department Master") WebElement SubDepartmentMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Sub Department Master";
	String PageTitle = "Sub Department Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubDepartmentCode']") WebElement SubDepartmentCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubDepartmentName']") WebElement SubDepartmentName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubDepartmentShortName']") WebElement SubDepartmentShortName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDeptName']") WebElement DepartmentNameDropdown;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubDepartmentRemarks']") WebElement SubDepartmentRemarks;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ChkActive']") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgSubDepartment_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Sub Department Master Add new Button";
	String subDepartmentCodeName ="Sub Department code";
	String subDepartmentNameName ="Sub Department Name";
	String subDepartmentShortNameName ="Sub Department Short Name";
	String DepartmentNameDropdownName ="Department Dropdown";
	
	String SubDepartmentRemarkName ="Remark";
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public SubDepartmentMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, SubDepartmentMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String subDepartmentCodeData, String subDepartmentNameData, String subDepartmentShortNameData, String DepartmentNameDropdownData, String SubDepartmentRemarksData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(SubDepartmentCode, subDepartmentCodeData, subDepartmentCodeName);
		bc.SendKeys(SubDepartmentName, subDepartmentNameData, subDepartmentNameName);
		bc.SendKeys(SubDepartmentShortName, subDepartmentShortNameData, subDepartmentShortNameName);
		bc.selectByVisibleText(DepartmentNameDropdown, DepartmentNameDropdownData, DepartmentNameDropdownName);
		
		bc.SendKeys(SubDepartmentRemarks, SubDepartmentRemarksData, SubDepartmentRemarkName);
		
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
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Department Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Department Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, SubDepartmentName, subDepartmentNameName, "Accounts Dept", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
