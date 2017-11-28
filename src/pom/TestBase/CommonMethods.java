package pom.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import pom.utility.Log;

public class CommonMethods {

	public WebDriver driver;
	BaseClass bc;
	TestBase tb;
//	public static final Logger log = Logger.getLogger(CommonMethods.class.getName());
	public CommonMethods(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	// --------------------> Existing Onex product -------------------------->
	public void TargetPage(WebElement modulelocator,String moduleName,WebElement submodulelocator,String submoduleName,WebElement subsectionlocator,String subsectionName,WebElement pageNamelocator, String pageName, String PageTitle){
		bc = new BaseClass(driver);
		bc.click(modulelocator, moduleName);
		bc.moveToElement(submodulelocator, submoduleName);
		bc.moveToElement(subsectionlocator, subsectionName);
		bc.click(pageNamelocator, pageName);
		String a = driver.getTitle();
		try{
			Assert.assertEquals(driver.getTitle(),PageTitle);
			System.out.println("TEST PASSED : Target page open successfully");
			Log.info("target page open successfully");
			Reporter.log("target page is open successfully");
		}
		catch(AssertionError e){
			System.out.println("TEST FAILED : Target page not open : page crashed");
			Log.info("target page not open : page crashed");
			Reporter.log("target page is not open");
			Assert.fail("Expected Value : "+PageTitle+" But Found : "+a);
		}
	}
	
	// --------------------> Existing Onex product Mouse Hover 3 level pages -------------------------->
	
		public void TargetPageMouseHover3levelPage(WebElement modulelocator,String moduleName,WebElement submodulelocator,String submoduleName,WebElement pageNamelocator, String pageName, String PageTitle){
			bc = new BaseClass(driver);
			bc.click(modulelocator, moduleName);
			bc.moveToElement(submodulelocator, submoduleName);
			bc.click(pageNamelocator, pageName);
			String a = driver.getTitle();
			try{
				Assert.assertEquals(driver.getTitle(),PageTitle);
				System.out.println("TEST PASSED : Target page open successfully");
				Log.info("target page open successfully");
				Reporter.log("target page is open successfully");
			}
			catch(AssertionError e){
				System.out.println("TEST FAILED : Target page not open : page crashed");
				Log.info("target page not open : page crashed");
				Reporter.log("target page is not open");
				Assert.fail("Expected Value : "+PageTitle+" But Found : "+a);
			}
		}
	
	//----------------------> NexGen Onex Product ------------------------------->
	public void TargetPageClick(WebElement modulelocator,String moduleName,WebElement submodulelocator,String submoduleName,WebElement subsectionlocator,String subsectionName,WebElement pageNamelocator, String pageName){
		bc = new BaseClass(driver);
		bc.click(modulelocator, moduleName);
		bc.click(submodulelocator, submoduleName);
		bc.click(subsectionlocator, subsectionName);
		bc.click(pageNamelocator, pageName);
	}
	
	//---------------------> target page for Enterprise setup (3 level pages path)------------------------------------>
	public void TargetPageEnterpriseSetup(WebElement modulelocator,String moduleName,WebElement submodulelocator,String submoduleName,WebElement pageNamelocator, String pageName, WebElement PageHeader){
		bc = new BaseClass(driver);
		tb = new TestBase();
		bc.click(modulelocator, moduleName);
	//	bc.waitFixedTime(1);
		bc.click(submodulelocator, submoduleName);
	//	bc.waitFixedTime(1);
		bc.click(pageNamelocator, pageName);
		bc.waitFixedTime(3);
		bc.waitForElementSeconds(PageHeader, 10);
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(PageHeader));
			tb.log("Target page is open successfully");

		}
		catch(AssertionError e){
			tb.log("TEST FAILED : Target page not open : page crashed");
			Assert.fail("Expected Value : ["+pageName+" - Page Header] But Found : [Page Crashed]");
		}
	}
	
	//-----------------------> Add New Button -------------------------------------->
	public void AddNewButton(WebElement AddNewButtonLocator, String locatorName, WebElement AddNewPageNameHeader, WebElement CancelButton){
		bc = new BaseClass(driver);
		bc.click(AddNewButtonLocator, locatorName);
		bc.waitFixedTime(1);
		try{
			Assert.assertTrue(bc.isElementPresentSingleLocator(AddNewPageNameHeader));
			bc.waitFixedTime(1);
			bc.click(CancelButton, "Back Button");
			System.out.println("TEST PASSED : Target page open successfully");
			Log.info("Add New Button is working properly");
			Reporter.log("Add New Button is working properly");
		}
		catch(AssertionError e){
			System.out.println("TEST FAILED : Target page not open : page crashed");
			Log.info("Target page not open : page crashed");
			Reporter.log("Getting Page crashed on the click of add New Button");
			Assert.fail("Expected Value : "+locatorName+" Page Header, But Found : Page Crashed");
		}
		
	}
	
	public void Test_Search_Button(WebElement dropdown_id, String Dropdowndata,String DropdownName, WebElement searchbox_id, String SearchData,WebElement searchbutton_id,String Title) throws Exception {
		bc = new BaseClass(driver);
		bc.selectByVisibleText(dropdown_id, Dropdowndata, DropdownName);
		bc.SendKeys(searchbox_id, SearchData, "Search Box");
		bc.click(searchbutton_id, "Search Button");
		bc.AlertAcceptIfPresent();
		bc.waitFor(1);
		try {
			Assert.assertTrue(bc.isElementPresentXpathLocator(SearchData));
			System.out.println("search value is Present");
			System.out.println("TEST PASSED : Functionality of search button is working properly");
			Log.info("search functionality is working properly");
		}
		catch(Exception e) {
			System.out.println("TEST FAILED : search functionality is not working :: record not found");
			Log.info("search functionality is not working :: record not found");
			Assert.fail("Not Found Search value : ["+SearchData+"]");
		}
	}
	
	public void Test_Edit_Record(WebElement EditButton,WebElement editpageHeader, WebElement EditSection, String EditSectionName, String EditedData, WebElement OKButton, String ExpectedStatus){
		bc = new BaseClass(driver);
		tb = new TestBase();
		
		bc.waitForElement(EditButton);
		bc.click(EditButton, "1st Record Edit Button");
		bc.waitForElement(editpageHeader);
		Assert.assertTrue(bc.isElementPresentSingleLocator(editpageHeader),"Page Crashed : Not Found Edit Form");
		bc.waitFixedTime(2);
		bc.click(EditSection, EditSectionName);
		bc.Clear(EditSection, EditSectionName);
		bc.SendKeys(EditSection, EditedData, EditSectionName);
		bc.click(OKButton, "OK Button");
		bc.waitForAlertBox(20);
		String AlertMsg = bc.closeAlertAndGetItsText();
		 try {
			 Assert.assertEquals(AlertMsg, ExpectedStatus);
			 tb.log("Record Added Successfully");
		 }catch(AssertionError e){
			 tb.log("Record Not Added");
			 Assert.fail("expected : ["+ExpectedStatus+"] but Found : ["+AlertMsg+"]");
		 }
		
	}
}
