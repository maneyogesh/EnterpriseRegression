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

public class BankMaster extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Organizational Setup") WebElement OrganizationalSetup;
	@FindBy(linkText="Bank Master") WebElement 	BankMaster;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Bank Master";
	String PageTitle = "Bank Master";
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement CancelButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBankCode']") WebElement BankCode;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBankName']") WebElement BankName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBankSName']") WebElement BankShortName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBranchName']") WebElement BranchName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBranchadd']") WebElement BranchAddress;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBrTelNo']") WebElement TelNo;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBrFaxNo']") WebElement FaxNo;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtBrEmail']") WebElement BranchEmail;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Txtremarks']") WebElement BranchRemark;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_TxtAccNoDigit']") WebElement AccountNoDigit;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkactive']") WebElement Activecheckbox;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgBank_imgEditButton_0']") WebElement EditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;

	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButtonName ="Bank Master Add new Button";
	String BankCodeName ="Bank code";
	String BankNameName ="Bank Name";
	String BankShortNameName ="Bank Short Name";
	String BranchNameName ="Branch Name";
	String BranchAddressName ="Branch Address";
	String TelNoName ="Tell No";
	String FaxNoName ="Fax No";
	String BranchEmailName ="Branch Email";
	String BranchRemarkName ="Branch Remark";
	String AccountNoDigitName ="Account No Digit";
	
	String ActiveName ="Active checkbox";
	
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	String ExpectRecordEditedMessage = "Record Updated Successfully.";
		
	public BankMaster(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, OrganizationalSetup, submoduleName, BankMaster, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButtonName, AddNewPageHeader, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String BankCodeData, String BankNameData, String BankShortNameData, String BranchNamedata, String BranchAddressData, String TelNoData, String FaxNoData, String BranchEmailData, String BranchRemarkData, String AccountNoDigitData, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(AddNewButtonLocator, AddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(BankCode, BankCodeData, BankCodeName);
		bc.SendKeys(BankName, BankNameData, BankNameName);
		bc.SendKeys(BankShortName, BankShortNameData, BankShortNameName);
		bc.SendKeys(BranchName, BranchNamedata, BranchNameName);
		bc.SendKeys(BranchAddress, BranchAddressData, BranchAddressName);
		bc.SendKeys(TelNo, TelNoData, TelNoName);
		bc.SendKeys(FaxNo, FaxNoData, FaxNoName);
		bc.SendKeys(BranchEmail, BranchEmailData, BranchEmailName);
		bc.SendKeys(BranchRemark, BranchRemarkData, BranchRemarkName);
		bc.SendKeys(AccountNoDigit, AccountNoDigitData, AccountNoDigitName);
		
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
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Bank Master Data added successfully");
		}catch(AssertionError e){
			tb.log("Bank Master Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		cm.Test_Edit_Record(EditButton, AddNewPageHeader, BankShortName, BankShortNameName, "ACC", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
