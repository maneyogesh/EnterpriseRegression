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

public class ApplicationUsage extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	TestBase tb;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="Application Monitor") WebElement ApplicationMonitor;
	@FindBy(linkText="Application Usage") WebElement ApplicationUsage;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "Organizational Setup";
	String pageName = "Application Status";
	String PageTitle = "Application Status";
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Button1']") WebElement StatusType;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Button2']") WebElement QueryDetails;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Button3']") WebElement MailDetails;
	
	@FindBy(xpath=".//*[@class='page-heading']") WebElement PageHeader;
	
	@FindBy(id="ContentPlaceHolder_lnkAddetails") WebElement StatusTypeAddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkBack']") WebElement BackButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_pnlHead']") WebElement AddNewPageHeader;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtStype']") WebElement TypeDescription;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtStColumn']") WebElement ColumnName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtQuery']") WebElement Query;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_cactive']") WebElement activecheckbox;
	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_CancelButton']") WebElement STCancelButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_dtgStatus_imgEditBtn_0']") WebElement StatusTypeEditButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkAddQuery']") WebElement QueryDetailAddNewButtonLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtActHeading']") WebElement ActivityHeading;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpModule']") WebElement ModuleName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drptbleName']") WebElement TableName;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpDatecols']") WebElement DateColumn;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCondcolumn']") WebElement ConditionalColumn;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_chkActive']") WebElement Active;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgSaveQry']") WebElement QueryDetailsOKButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgCancelQry']") WebElement QueryDetailCancelLocator;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkBackQuery']") WebElement QueryBackButton;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtdisplayName']") WebElement Display;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMailfrm']") WebElement MailForm;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtMailto']") WebElement MailTo;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtCc']") WebElement CC;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtBcc']") WebElement BCC;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_txtSubject']") WebElement Subject;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_AttachFile']") WebElement Attachment;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_btnupload']") WebElement MailDetailsuploadbutton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkSaveM']") WebElement MailSaveButton;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_lnkBackM']") WebElement MailBackButton;
	
		
	String StatusTypeAddNewButtonName ="Status Type Add new Button";
	String STBackButtonName ="Status Type Back Button";
	String STAddNewPageHeaderName ="Status Type Page Header";
	String STDescriptionName = "Status Type Description";
	String STColumnName = "Status Type Column Name";
	String STQueryName = "Status Type Query";
	String STActiveCheckboxName = "Status Type Active";
	String STOKName = "Status Type OK Button";
	String STCancelName = "Status Type Cancel Button";
	String STEditName = "Status Type Edit Button of 1ST record";

	String QDAddNewButtonName ="Query Details Add new Button";
	String QDActivityHeadingName ="Query Detail Activity Heading";
	String QDModuleName ="Query Details Module Name";
	String QDTableName = "Query Details Table Name";
	String QDDateColumnName = "Query Details Date Column";
	String QDConditionalColumnName = "Query Details Conditional Column";
	String QDActiveName = "Query Details Active";
	String QDOKName = "Query Details OK Button";
	String QDCancelName = "Query Details Cancel Button";
	String QDBackButtoname = "Query Details Back Button";
	
	String MAddNewButtonName ="Mail Details Add new Button";
	String MDdISPLAYnAMName ="Mail Detail Display Name";
	String MFormName ="Mail Details Form";
	String MtoName = "Mail Details To";
	String MCCName = "Mail Details CC";
	String MDBCCName = "Mail Details BCC";
	String MSubjectName = "Mail Details Subject";
	String MDAttachmentName = "Mail Details Attachment";
	String MDUploadButtonName = "Mail Details Upload Button";
	String MDSaveButtonButtoname = "Mail Details Save Button";
	String MDBackButtonButtoname = "Mail Details Back Button";
	
	String ExpectRecordEditedMessage = "Record Updated Successfully";
		
	public ApplicationUsage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, ApplicationMonitor, submoduleName, ApplicationUsage, pageName, PageHeader);	
	//	cm.TargetPageMouseHover3levelPage(EnterpriseSetup, moduleName, RegionalSetup, submoduleName, CityMaster, pageName, PageTitle);
	}
	
	public void Test_AddNew_StatusType_Button(){
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.click(StatusType, "Status Type");
		cm.AddNewButton(StatusTypeAddNewButtonLocator, StatusTypeAddNewButtonName, AddNewPageHeader, STCancelButton);
	}
	
	public void Test_AddNew_QueryDetails_Button(){
		cm = new CommonMethods(driver);
		bc = new BaseClass(driver);
		bc.click(QueryDetails, "Query Details");
		cm.AddNewButton(QueryDetailAddNewButtonLocator, QDAddNewButtonName, ActivityHeading, QueryBackButton);
	}
	
	public void Test_AddNew_MailDetails_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(MailDetails, MAddNewButtonName, Display, MailBackButton);
	}
	
	public void Test_AddNewDataUpload(String TypeDescriptionData, String ColumnNameData, String Querydata, String ActiveData) throws Exception{
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.waitFixedTime(1);
		bc.click(StatusType, "Status Type");
		bc.waitForElement(StatusTypeAddNewButtonLocator);
		bc.click(StatusTypeAddNewButtonLocator, StatusTypeAddNewButtonName);
		bc.fluentWait(AddNewPageHeader, 1);
		bc.SendKeys(TypeDescription, TypeDescriptionData, STDescriptionName);
		bc.SendKeys(ColumnName, ColumnNameData, STColumnName);
		bc.SendKeys(Query, Querydata, STQueryName);
		
		if(ActiveData.equalsIgnoreCase("Yes")){
			bc.click(activecheckbox, STActiveCheckboxName);
		}else{}
		
				
		bc.waitForElement(OKButton);
		bc.click(OKButton, STOKName);
		bc.waitForAlertBox(10);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		Assert.assertTrue(bc.AlertBoxPresentORNot(), "Page crashed : Alert Box Not Present");
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record Saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		tb.log("Application Usage Data added successfully");
		}catch(AssertionError e){
			tb.log("Application Usage Data Not Added successfully : page crashed");
		}		
	}
	
	public void Test_EditRecord(){
		cm = new CommonMethods(driver);
		bc.click(StatusType, "Status Type");
		cm.Test_Edit_Record(StatusTypeEditButton, AddNewPageHeader, TypeDescription, STDescriptionName, "test State Type", OKButton, ExpectRecordEditedMessage);
			
	}
	

}
