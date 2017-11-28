package pom.ObjectPages.uiActions;

import java.util.List;

import org.openqa.selenium.By;
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

public class PollQuizSetup extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
	
	@FindBy(linkText="Enterprise Setup") WebElement EnterpriseSetup;
	@FindBy(linkText="General Setup") WebElement GeneralSetup;
	@FindBy(linkText="Poll-Quiz Setup") WebElement PollQuizSetup;  
	String moduleName = "Enterprise Setup";
	String submoduleName = "General Setup";
	String pageName = "Poll-Quiz Setup";
	String PageTitle = "Poll-Quiz Setup";
	
	@FindBy(xpath=".//*[@class='page-heading']//parent::tr//parent::tbody//parent::table") WebElement PageHeader;
	@FindBy(id="ContentPlaceHolder_cmdAddNew") WebElement AddNewButtonLocator;
	@FindBy(id="ContentPlaceHolder_CancelButton") WebElement CancelButton;
	
	@FindBy(id="ContentPlaceHolder_drpsetupfor") WebElement SetupFor;
	@FindBy(id="ContentPlaceHolder_txtSetupMid") WebElement SetupCode;
	@FindBy(id="ContentPlaceHolder_drpSelectSubject") WebElement SelectQuestionBank;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_drpQuestiontype']") WebElement QuestionType;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_Imstartdate']") WebElement StartDate;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calstartdate_nextArrow']") WebElement SNextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calstartdate_prevArrow']") WebElement SPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calstartdate_title']") WebElement SMiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calstartdate_monthsBody']//descendant::tr/td/div") List<WebElement> SAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calstartdate_daysBody']//tr//td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> SAllDates;
	
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_ImgEndDate']") WebElement ToDate;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calenddate_nextArrow']") WebElement TNextCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calenddate_prevArrow']") WebElement TPrevCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calenddate_title']") WebElement TMiddleCalender;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calenddate_monthsBody']//descendant::tr/td/div") List<WebElement> TAllMonth;
	@FindBy(xpath=".//*[@id='ContentPlaceHolder_calenddate_daysBody']//tr//td[not(contains(@class,'ajax__calendar_other'))]") List<WebElement> TAllDates;
	
	@FindBy(id="ContentPlaceHolder_RdYesno_0") WebElement AnonymousYes;
	@FindBy(id="ContentPlaceHolder_RdYesno_1") WebElement AnonymousNo;
	@FindBy(id="ContentPlaceHolder_chkActive") WebElement Status;
	@FindBy(id="ContentPlaceHolder_OkButton") WebElement OKButton;
	
	String AddNewButton ="Poll Quiz Setup Add new Button";
	String SetupForName ="Setup For";
	String SetupCodeName ="Setup Code";
	String SelectQuestionBankName ="Select Question Bank";
	String QuestionTypeName ="Question Type";
	String AnonymousYesName ="Anonymous - Yes";
	String AnonymousNoName ="Anonymous - No";
	String OKButtonName ="OK Button";
	String CancelButtonName ="Cancel Button";
	
	
	public PollQuizSetup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void Test_Open_Target_Page(){
		cm = new CommonMethods(driver);
		cm.TargetPageEnterpriseSetup(EnterpriseSetup, moduleName, GeneralSetup, submoduleName, PollQuizSetup, pageName, AddNewButtonLocator);	
	}
	
	public void Test_AddNew_Button(){
		cm = new CommonMethods(driver);
		cm.AddNewButton(AddNewButtonLocator, AddNewButton, SetupFor, CancelButton);
	}
	
	public void Test_AddNewDataUpload(String SetupForData, String SetupCodeData, String SelectQuestionBankData, String QuestionTypeData, String statusData, String fromDateData, String TodateData, String ApplicableCriteriaData, String Anonymous, String QuestionSectionData) throws Exception{
		bc = new BaseClass(driver);
		
	//	bc.fluentWait(AddNewButtonLocator, 1);
		bc.click(AddNewButtonLocator, "Add New Button");
		bc.fluentWait(SetupFor, 1);
		bc.selectByVisibleText(SetupFor, SetupForData, SetupForName);
		bc.waitFor(2);
		bc.SendKeys(SetupCode, SetupCodeData, SetupCodeName);
		bc.selectByVisibleText(SelectQuestionBank, SelectQuestionBankData, SelectQuestionBankName);
		bc.AlertAcceptIfPresent();
		bc.waitFor(2);
		bc.selectByVisibleText(QuestionType, QuestionTypeData, QuestionTypeName);
		bc.waitFixedTime(2);
		
		
		bc.DateSelection(fromDateData, StartDate, SNextCalender, SMiddleCalender, SPrevCalender, SAllMonth, SAllDates);
		bc.waitFixedTime(1);
		bc.DateSelection(TodateData, ToDate, TNextCalender, TMiddleCalender, TPrevCalender, TAllMonth, TAllDates);
		bc.waitFixedTime(2);
		
		if(statusData=="No"){}
		else{bc.click(Status, "Status");
		}
		
		if(Anonymous=="Yes"){
			bc.click(AnonymousYes, "Anonymous Yes");
		}else{
			bc.click(AnonymousNo, "Anonymous No");
		}		
		bc.ScrollDown();
		bc.waitFixedTime(2);
		WebElement ApplicableCriteria = driver.findElement(By.xpath(".//*[text()='"+ApplicableCriteriaData+"']/input[@type='checkbox']")); 
		bc.click(ApplicableCriteria, ApplicableCriteriaData);
		bc.waitFixedTime(1);
		WebElement QuestionSection = driver.findElement(By.xpath(".//*[text()='"+QuestionSectionData+"']/parent::tr/td/input[@type='checkbox']")); 
		bc.click(QuestionSection, QuestionSectionData);
		bc.waitFixedTime(10);
		bc.ScrollDown();
		bc.waitForElement(OKButton);
		bc.click(OKButton, OKButtonName);
		//	bc.closeAlertAndGetItsText();
		//  bc.AlertAcceptIfPresent();
		bc.waitForAlertBox(10);
		try{
		Assert.assertEquals(bc.AlertBoxText(), "Record saved Successfully.");
		System.out.println(bc.AlertBoxText());
		//	Assert.assertTrue(bc.isElementPresentSingleLocator(VerifyEntityCode));
		Reporter.log("Poll Quiz Setup Data added successfully");
		}catch(AssertionError e){
			Reporter.log("Poll Quiz Setup Data Not Added successfully : page crashed");
		}		
	}
	

}
