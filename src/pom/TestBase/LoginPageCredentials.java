package pom.TestBase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pom.excelReader.Mapping.ReadExcelMapping;

public class LoginPageCredentials extends TestBase{
	
	public WebDriver driver;
	BaseClass bc;
	LoginPage lp;
	CommonMethods cm;
	ReadExcelMapping REM = new ReadExcelMapping();
		
	public LoginPageCredentials(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void Test_Login() throws IOException{
		List<Map<String, String>> map = REM.ReadExcel("D:\\SVN\\QA-Testing-Doc\\Quality Assurance\\AutomationTestScript\\EnterpriseSetup_Regression\\src\\pom\\Excel\\TestCases\\LoginDetails.xls","EnterpriseSetup");
		lp = new LoginPage(driver);
		lp.Login(map.get(0).get("UserName"), map.get(0).get("Password"), map.get(0).get("CompanyCode"));
	}
	
}