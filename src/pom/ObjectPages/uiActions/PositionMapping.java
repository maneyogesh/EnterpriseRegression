package pom.ObjectPages.uiActions;

import org.openqa.selenium.By;
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

public class PositionMapping extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Position Mapping") WebElement PositionMapping;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Position Mapping";
	String PageTitle = "Position Mapping";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_lnkBAddNewH") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDesignation']") WebElement Position;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Drpcheckdepartment_boundingbox']") WebElement Department;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_DtgDesigMappingDetails_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_lnkSaveH") WebElement OKButton;
	
	String AddNewButtonName ="Position Mapping Add new Button";
	String PositionName ="Position";
	String DepartmentName ="Department";
	
	String ActivecheckboxName = "Active checkbox";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully.";
		
	public PositionMapping(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, PositionMapping, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String Positiondata, String DepartmentValueData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.selectByVisibleText(Position, Positiondata, PositionName);
		bc.click(Department, DepartmentName);
		WebElement DepartmentValue = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder_Drpcheckdepartment']/tbody/tr/td/label[text()='"+DepartmentValueData+"']"));
		bc.click(DepartmentValue, DepartmentValueData);
		bc.click(Department, DepartmentName);
		
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
		tb.log("Position Mapping Data added successfully");
		}catch(AssertionError e){
			tb.log("Position Mapping Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		bc = new BaseClass(driver);
		bc.click(EditButton, "Edit Button");
		bc.waitForElement(Position);
		bc.click(OKButton, OKButtonName);
		bc.waitForAlertBox(10);
		String alert = bc.closeAlertAndGetItsText();
		Assert.assertEquals(alert, ExpectRecordEditedMessage);
	}
	

}
