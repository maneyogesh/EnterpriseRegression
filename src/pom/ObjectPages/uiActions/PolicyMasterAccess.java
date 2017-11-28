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

public class PolicyMasterAccess extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Policy Master Access") WebElement PolicyMasterAccess;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Policy Master Access";
	String PageTitle = "Policy Master Access";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(id="ContentPlaceHolder_pnlHead") WebElement AddNewPageHeader;
	@FindBy(id="ContentPlaceHolder_CancelButton") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_drpPolicy") WebElement PolicyNameDropdown;
	@FindBy(id="ContentPlaceHolder_chkAll") WebElement BranchCheckbox;
	@FindBy(id="ContentPlaceHolder_chkLAll") WebElement LevelCheckbox;
	@FindBy(id="ContentPlaceHolder_chkSAll") WebElement DivisionCheckbox;
	@FindBy(id="ContentPlaceHolder_chkBAll") WebElement DepartmentCheckbox;
	@FindBy(id="ContentPlaceHolder_chkDAll") WebElement PositionCheckbox;
	
	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButton ="Policy Master Access Add new Button";
	String PolicyNameDropdownName ="Policy Name";
	String BranchcheckboxName ="Branch";
	String LevelcheckboxName ="Level";
	String DivisioncheckboxName ="Division";
	String DepartmentcheckboxName ="Department";
	String PositioncheckboxName ="Position";
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	
	
	public PolicyMasterAccess(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, PolicyMasterAccess, pageName, PageHeader);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButton, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String PolicyNameData) throws Exception{
		bc = new BaseClass(driver);
	//	WebElement VerifyEntityCode = driver.findElement(By.xpath(".//*[text()='"+EntityCodeData+"']")); 
		bc.fluentWait(AddNewButtonLocator, 1);
		bc.click(AddNewButtonLocator, "Add New Button");
		bc.fluentWait(AddNewPageHeader, 1);
		bc.selectByVisibleText(PolicyNameDropdown, PolicyNameData, PolicyNameDropdownName);
		bc.waitFor(1);
		bc.click(BranchCheckbox, BranchcheckboxName);
		bc.waitFor(1);
		bc.click(LevelCheckbox, LevelcheckboxName);
		bc.waitFor(1);
		bc.click(DivisionCheckbox, DivisioncheckboxName);
		bc.waitFor(1);
		bc.click(DepartmentCheckbox, DepartmentcheckboxName);
		bc.waitFor(1);
		bc.click(PositionCheckbox, PositioncheckboxName);
		bc.waitFor(1);		
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		try{
			Assert.assertTrue(bc.AlertBoxPresentORNot(), "Alert Box Not Present : page crashed");
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Policy Master Access Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Policy Master Access Data Not Added successfully : page crashed");
			Assert.fail("Record Not saved successfully OR Page Crashed : Alert Box Not Present");
		}		
	}
	

}
