package pom.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

public class LoginPage {

	public WebDriver driver;
	BaseClass baseclass;
	
	@FindBy(id="Login1_UserName") WebElement UserName;
	@FindBy(id="Login1_Password") WebElement PassWord;
	@FindBy(id="Login1_LoginButton") WebElement LoginButton;  
	@FindBy(id="lblUser_name_header") WebElement MenuButton;
	@FindBy(id="lnkBtn_Logout") WebElement LogOut;
	@FindBy(id="Login1_CompanyCode") WebElement CompanyCode;
	
	@FindBy(xpath=".//*[@id='imgOkBtn']") WebElement OKBButton;
	
	public LoginPage(WebDriver driver) {
		
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	   public void setCompanyCode(String strCompanyCode){
	    	try{
	    		CompanyCode.sendKeys(strCompanyCode);
	    	}
	    	catch(Exception e){
	    		throw new SkipException("Not Found : Company Code ");
	    	}
	    }
	
    public void setUserName(String strUserName){
    	try{
    		UserName.sendKeys(strUserName);
    	}
    	catch(Exception e){
    		throw new SkipException("Not Found : UserName ");
    	}
    }
	        
    public void setPassword(String password){
    	try{
    		PassWord.sendKeys(password);
    	}
    	catch(Exception e){
    		throw new SkipException("Not Found : Password");
    	}
    }
    	
    public void LoginButton(){
    	try{
    		LoginButton.click();
    	}
    	catch(Exception e){
    		throw new SkipException("Not Found : LoginButton");
    	}
    }
        	
    public void MenuButton(){
    	try{
    		MenuButton.click();
    	}catch(Exception e){
    		throw new SkipException("Not Found : Menu Button");
    	}
    }
    	
    public void LogoutButton(){
    	try{
    		LogOut.click();
    	}catch(Exception e){
    		throw new SkipException("Not Found : LogOut Button");
    	}
    }

	public void Login(String strUserName,String password, String strCompanyCode){
		baseclass = new BaseClass(driver);
		this.setUserName(strUserName);
		this.setPassword(password);
		this.setCompanyCode(strCompanyCode);
		this.LoginButton();
		if(baseclass.isElementDisplayed(OKBButton)){
			OKBButton.click();
		}else{System.out.println(">>>>>>>___----------------------------------_____");	}
		//baseclass.AlertAcceptIfPresent();
		System.out.println("clicked on login button");
		//baseclass.AlertAcceptIFPresent();	
		System.out.println("successfully login");
	}
		  
	public void logout() throws InterruptedException{
		this.MenuButton();
		Thread.sleep(1000);
		this.LogoutButton();
	}
	
}
