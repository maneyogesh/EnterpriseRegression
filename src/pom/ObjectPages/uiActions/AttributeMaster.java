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

public class AttributeMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Attribute Master") WebElement AttributeMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Attribute Master";
	String PageTitle = "Attribute Master";
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TreeView1t4']") WebElement TestingOption;
	@FindBy(xpath=".//*[@class='attr-page-heading']/span[text()='SUB-ATTRIBUTE MASTER']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdSubattribute") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnCANCELsub']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpAttribute']") WebElement Attribute;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtsubattb']") WebElement SubAttributeCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtsubattb_desc']") WebElement SubAttributeDescription;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtsubattb_shortdesc']") WebElement SubAttributeshortDescription;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtsap_code']") WebElement SapCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtgeneralled']") WebElement GeneralLedgerCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtgen_leddesc']") WebElement GeneralLedgerDescription;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgSub_Attribute_imgEditButton12_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead1']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_btnOKsub") WebElement OKButton;
	
	String AddNewButtonName ="Attribute Master Add new Button";
	String AttributeName ="Attribute";
	String SubAttributeCodeName ="Sub Attribute Code";
	String SubAttributeDescriptionName ="Sub Attribute Description";
	String SubAttributeShortDescriptionName ="Sub Attribute Short Description";
	String SapCodeName ="Sap Code";
	String GeneralLedgerCodeName ="General Ledger Code";
	String GeneralLedgerDescriptionName ="General Ledger Description";
	String TestingNAME="Testing";

	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public AttributeMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, AttributeMaster, pageName, TestingOption);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.click(TestingOption, TestingNAME);
		bc.waitForElement(AddNewButtonLocator);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String AttributeData, String SubAttributeCodeData, String SubAttributeDescriptiondata, String SubAttributeshortDescriptiondata, String SapCodedata, String GeneralLedgerCodedata, String GeneralLedgerDescriptiondata) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(2);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(Attribute, 3);
		bc.waitFixedTime(2);
		bc.selectByVisibleText(Attribute, AttributeData, AttributeName);
		bc.SendKeys(SubAttributeCode, SubAttributeCodeData, SubAttributeCodeName);
		bc.SendKeys(SubAttributeDescription, SubAttributeDescriptiondata, SubAttributeDescriptionName);
		bc.SendKeys(SubAttributeshortDescription, SubAttributeshortDescriptiondata, SubAttributeShortDescriptionName);
		bc.SendKeys(SapCode, SapCodedata, SapCodeName);
		bc.SendKeys(GeneralLedgerCode, GeneralLedgerCodedata, GeneralLedgerCodeName);
		bc.SendKeys(GeneralLedgerDescription, GeneralLedgerDescriptiondata, GeneralLedgerDescriptionName);
		
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		bc.waitForAlertBox(10);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Attribute Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Attribute Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, SubAttributeshortDescription, SubAttributeShortDescriptionName, "Test Data Attributes", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
