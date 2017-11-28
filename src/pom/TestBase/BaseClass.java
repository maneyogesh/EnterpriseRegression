package pom.TestBase;


import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.google.common.base.Function;


public class BaseClass {
  
  private boolean acceptNextAlert = true;
  WebDriver driver;
  public static Logger log = Logger.getLogger("devpinoyLogger");
  
  public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
  
  //---------------------------------> Send value ------------------------------>
  public void SendKeys(WebElement locator,String data,String locatorName){
	  System.out.println("Executing input on: "+locatorName);
	  log.info("Executing input on: "+locatorName);
	  this.waitForElement(locator);
	  	 try{
	  		locator.clear();
	  		locator.sendKeys(data);
	  		   System.out.println("Able to input data: "+data+" to field: "+locatorName);
	  	       log.info("Able to input data: "+data+" to field: "+locatorName);
	  	 }catch(Exception e){
	  		   System.out.println("Element not found : "+locatorName);
	  		   log.info("Element not found : "+locatorName);
	  		   throw new SkipException("Not Found Locator : "+locatorName);
	  	 }
  }
  
  
  
  //---------------------------------> Send value ------------------------------>
  public void SendKeysSearchBoxDropdownValue(WebElement SearchTextBoxlocator, WebElement labelLocatorName, String data,String locatorName, int waitTime){
	  System.out.println("Executing input on: "+locatorName);
	  log.info("Executing input on: "+locatorName);
	  this.waitForElement(SearchTextBoxlocator);
	  	 try{
	  		SearchTextBoxlocator.click();
	  		SearchTextBoxlocator.clear();
	  		labelLocatorName.click();
	  		this.waitFixedTime(waitTime);
	  		this.waitForElement(SearchTextBoxlocator);
	  		SearchTextBoxlocator.click();
	  		SearchTextBoxlocator.sendKeys(data);
	  		SearchTextBoxlocator.sendKeys("\uE015");
	  		this.waitFixedTime(1);
	  		SearchTextBoxlocator.sendKeys(Keys.ENTER);
	  		this.waitFixedTime(waitTime);
	  		  System.out.println("Able to input data: "+data+" to field: "+locatorName);
	  	       log.info("Able to input data: "+data+" to field: "+locatorName);
	  	 }catch(Exception e){
	  		   System.out.println("Element not found : "+locatorName);
	  		   log.info("Element not found : "+locatorName);
	  		   throw new SkipException("Not Found Locator : "+locatorName);
	  	 }
  }
  
  
  //---------------------------------> take value from dropdown direct Send value ------------------------------>
  public void SendKeysSearchBoxDropdownValueDirect(WebElement SearchTextBoxlocator,String data,String locatorName, int waitTime){
	  System.out.println("Executing input on: "+locatorName);
	  log.info("Executing input on: "+locatorName);
	  this.waitForElement(SearchTextBoxlocator);
	  	 try{
	  		SearchTextBoxlocator.click();
	  		SearchTextBoxlocator.clear();
	  		
	  		SearchTextBoxlocator.sendKeys(data);
	  		SearchTextBoxlocator.sendKeys("\uE015");
	  		this.waitFixedTime(1);
	  		SearchTextBoxlocator.sendKeys(Keys.ENTER);
	  		this.waitFixedTime(waitTime);
	  		  System.out.println("Able to input data: "+data+" to field: "+locatorName);
	  	       log.info("Able to input data: "+data+" to field: "+locatorName);
	  	 }catch(Exception e){
	  		   System.out.println("Element not found : "+locatorName);
	  		   log.info("Element not found : "+locatorName);
	  		   throw new SkipException("Not Found Locator : "+locatorName);
	  	 }
  }
  
  //---------------------------------------------------> Click --------------------------------------->
  public void click(WebElement locator,String locatorName){
	  System.out.println("Executing click on: "+locatorName);
	  log.info("Executing click on: "+locatorName);
	  	 try{
	  		 	this.waitForElement(locator);
	  		 	locator.click();
	  		   log.info("Able to click on "+locatorName);
	  	 }catch(Exception e){
	  		   System.out.println("Element not found: "+locatorName);
	  		   log.info("Element not found: "+locatorName);
	  		   throw new SkipException("Not Found Element : "+locatorName);
	  	 }
	    }
  
  
  //---------------------------------------------------> Clear --------------------------------------->
  public void Clear(WebElement locator,String locatorName){
	  System.out.println("Executing click on: "+locatorName);
	  log.info("Executing click on: "+locatorName);
	  	 try{
	  		 	locator.clear();
	  		   log.info("Able to click on "+locatorName);
	  	 }catch(Exception e){
	  		   System.out.println("Element not found: "+locatorName);
	  		   log.info("Element not found: "+locatorName);
	  		   throw new SkipException("Not Found Element : "+locatorName);
	  	 }
	    }
  
  // Common methods for element present
  public boolean isElementPresent(List<WebElement> el) {
	System.out.println("Executing isElementPresent");
	log.debug("Executing isElementPresent");
    try {
      List<WebElement> l = (el);
      if(l.size()>0){
    	  System.out.println("Element Present: "+el);
    	  log.debug("Element Present: "+el);
      return true;
      }
    } catch (NoSuchElementException e) {
     System.out.println("Element not found: "+el);	
     log.debug("Element not found: "+el);
      return false;
    }
    return false;
  }
  
 //----------------- is element present single locator value --------------------------->
  
  public boolean isElementPresentXpathLocator(String text) {
	    try {
	    	driver.findElement(By.xpath(".//*[text()='"+text+"']")).isDisplayed();
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
  
 //----------------- is element present single locator value --------------------------->
  
  public boolean isElementPresentSingleLocator(WebElement Locator) {
	    try {
	    	Locator.isDisplayed();
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
  
  
  // common method for element displayed
  protected boolean isElementDisplayed(WebElement el) {
	System.out.println("Executing isElementDisplayed");
	log.debug("Executing isElementDisplayed");
	    try {
	      if(el.isDisplayed())
	      return true;
	      
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	    return false;
	  }
  
 
//----------------> element is selected or not ------------------------------>
  protected void isElementSelected(WebElement LocatorValue,String ElementName,String Page){
	  System.out.println("Executing Select on: "+ElementName+" On Page:"+Page);
	  log.info("Executing Select on: "+ElementName);
	  try{
		    boolean val=LocatorValue.isSelected();
			if(val==true){
				System.out.println(ElementName+": is selected.");
	             log.info(ElementName+": is selected.");
	         	}
			else
			{
				System.out.println(ElementName+": is not selected.");
	             log.info(ElementName+": is not selected.");
	            
			}
				
		}catch(Exception e){
			  System.out.println("Not found: "+ElementName);
			  log.info("Not found: "+ElementName);
			 throw new SkipException("Element is not found : "+ ElementName);
			
		}
  }
  
  
  //------> Get Alert and Get Alert box Message ------------------------------------------->
  
  public String closeAlertAndGetItsText() {
	  System.out.println("Executing closeAlertAndGetItsText");
	  log.info("Executing closeAlertAndGetItsText");
        try {
             Alert alert = driver.switchTo().alert();
             String alertText = alert.getText();
                    if (acceptNextAlert) {
                        alert.accept();
                     } else {
                      alert.dismiss();
                      }
               return alertText;
            } finally {
          acceptNextAlert = true;
        }
     }
  
 //------>  Get Alert box Message ------------------------------------------->
  
  public String AlertBoxText() {
	  System.out.println("Executing GetAlertBoxText");
	  log.info("Executing GetAlertBoxText");
        try {
             Alert alert = driver.switchTo().alert();
             String alertText = alert.getText();
                   
               return alertText;
            } finally {
          acceptNextAlert = true;
        }
     }
  
 //------>  Get Alert box present or not ------------------------------------------->
  
  public boolean AlertBoxPresentORNot() {
	  System.out.println("Executing GetAlertBox");
	  log.info("Executing GetAlertBox");
       try{ 
	  driver.switchTo().alert();
      return true;
       }
      catch (Exception e) {
	        return false;
	    }
     }
  
  // -------------------> wait command for 30 seconds ------------------------------->
  public void waitForElement(WebElement locator){
   	 System.out.println("Executing waitForElement "+locator);
   	 log.info("Executing waitForElement "+locator);
    
	  WebDriverWait wait = new WebDriverWait(driver,30);
	
   	 try{
   		 wait.until(ExpectedConditions.visibilityOf(locator));
   		
   		 }catch(Exception e){
   		 e.printStackTrace();
   		
   	 }
    }
  
  // -------------------> wait Alert command for 300 seconds ------------------------------->
  public void waitForAlertBox(int Seconds){
   	
	  WebDriverWait wait = new WebDriverWait(driver,Seconds);
	
   	 try{
   		 wait.until(ExpectedConditions.alertIsPresent());
   		
   		 }catch(Exception e){
   		 e.printStackTrace();
   		
   	 }
    }
  
  
  // -------------------> wait command for 500 seconds ------------------------------->
  protected void waitForElementToDisappear(WebElement locator){
	   	 System.out.println("Executing waitForElementToDisappear "+locator);
	   	 log.info("Executing waitForElementToDisappear "+locator);
	   	
		  WebDriverWait wait = new WebDriverWait(driver,500);
	   	 try{
	   		 wait.until(ExpectedConditions.visibilityOf(locator));
	   		
	   		 }catch(Exception e){
	   		 e.printStackTrace();
	   		
	   	 }
	    }
  
  //----------------> wait command for Userdefine wait time ---------------------------------->
  protected void waitForElementSeconds(WebElement locator,int sec){
	   	 System.out.println("Executing wait For Element : "+locator);
	   	 log.info("Executing wait For Element : "+locator);
	   	
		  WebDriverWait wait = new WebDriverWait(driver,sec);
	   	 try{
	   		 wait.until(ExpectedConditions.visibilityOf(locator));
	   		
	   		 }catch(Exception e){
	   		 e.printStackTrace();
	   		
	   	 }
	    }
  
  // -----------------> fixed wait time in seconds --------------------------->
  public void waitFixedTime(long t){
	  log.info("Executing wait Fixed Time");
  	 try{
  		 Thread.sleep(t*1000);
  	 }catch(Throwable e){
  		 System.out.println("Error in wait");
  		 log.info("Error in wait");
  		   	 }
    }
  
  
   //---------------------------------------> Verify the text of an element --------------------------------------->
  protected boolean verifyText(WebElement locator,String locatorName,String expectedText){
	  
	  System.out.println("Executing verifyText() on Element:"+locatorName);
	  log.info("Executing verifyText on Element:"+locatorName);
	  try{
			String actualText= locator.getText().replace("\n", "");
		    System.out.println(actualText);
		    if(actualText.equals("")){
		    	actualText= locator.getAttribute("value");
		    }		   
		            if(expectedText.trim().equals(actualText.trim())){
			             System.out.println("Actual text matching Expected value");
			             log.info("Actual text matching Expected value");
			             return true;
		            }
		              else
		                  {
			                 System.out.println("Actual text not matching Expected value");
			                 log.info("Actual text not matching Expected value");
			                 return false;
		                  }
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
			return false;
		}
  }
  
//------------------------------------> Verify if error exists -------------------------------->
  
  protected boolean ifErrorExists(WebElement locator){
	  log.info("Executing ifErrorExists");
	  try{
		            if(locator.getAttribute("style").contains("block")){
			             return true;
		            }
		              else
		                  {
		                     return false;
		                  }
		}catch(Exception e){
			  log.info("Not found : "+locator);
			return false;
		}
  }
  
  
//-------------------------------------------> Verify the text of an element --------------------------------------------------->
  protected boolean verifyErrorText(WebElement errorID,WebElement locator,String expectedText,String locatorName){
	  log.info("Executing verifyText() on Element:"+locatorName);
	  if(ifErrorExists(errorID)){
	  try{
		    String actualText= locator.getText();
		   
		            if(expectedText.trim().equals(actualText.trim())){
			             System.out.println("Actual text matching Expected value");
			             log.info("Actual text matching Expected value");
			             return true;
		            }
		              else
		                  {
			                 System.out.println("Actual Error text not matching Expected value");
			                 log.info("Actual Error text not matching Expected value");
			                 return false;
		                  }
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
			throw new SkipException("Not Found Element : "+locatorName);
		}
	  }
			return false;
		
  }
  
  //-----------------------------> verify list text -------------------------------------->
  protected void verifyListText(WebElement locator,String expectedListText,String locatorName){
	  System.out.println("Executing verifyText() on Element:"+locatorName);
	  log.info("Executing verifyText() on Element:"+locatorName);
	  try{
			
		  Select s=new Select(locator);
		  String actualText=s.getFirstSelectedOption().getText();
		   
		            if(expectedListText.trim().equals(actualText.trim())){
			             System.out.println("Actual text matching Expected value");
			             log.info("Actual text matching Expected value");
			            }
		              else
		                  {
			                 System.out.println("Actual text not matching Expected value");
			                 log.info("Actual text not matching Expected value");
			          
		                  }
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
			  throw new SkipException("Not Found Element : "+locatorName);
			
		}
  }
  
//-----------------------verify list content------------------------------->
  protected void verifyTextList(List<WebElement> locator,String expectedText,String locatorName){
	 System.out.println("Executing verifyText() on Element:"+locatorName); 
	 log.info("Executing verifyText() on Element:"+locatorName);
	 String[] expectedTextVal=expectedText.split(",");
	  try{
		  List<WebElement> wElement = locator;
		  for (int i = 0; i < expectedTextVal.length; i++) {
			  String actualText = wElement.get(i).getText();
			  //System.out.println(actualText);
			  if(expectedTextVal[i].trim().equals(actualText.trim())){
		             System.out.println("Actual text matching Expected value");
		             log.info("Actual text matching Expected value");
		             }
	            else
	            {
		                 System.out.println("Actual text not matching Expected value");
		                 log.info("Actual text not matching Expected value");
		         }	 
		  	}		                
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
			  throw new SkipException("Not Found Element : "+locatorName);
		}
  }
  
  
  //--------------------------Verify the default selection of a select box-------------------------------------------->
  protected void verifyDefaultSelection(WebElement locator,String expectedText,String locatorName){
	  log.info("Executing verifyDefaultSelection() on Element:"+locatorName);
	  try{
		    Select s = new Select(locator);
			String actualText=s.getFirstSelectedOption().getText();
			
		            if(expectedText.trim().equalsIgnoreCase(actualText.trim())){
			             System.out.println("Default selection matched expected for selectbox: "+locatorName);
			             log.info("Default selection matched expected for selectbox: "+locatorName);
			               }
		              else
		                  {
			                 System.out.println("Default selection not matched expected for selectbox: "+locatorName);
			                 log.info("Default selection not matched expected for selectbox: "+locatorName);
			               
		                  }
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
		
			throw new SkipException("Not found: "+locatorName);
		}
  }
  
//--------------------------------get dropdown selected text---------------------------------------->
  protected String getListText(WebElement locator,String locatorName){
	  String text = null;
	  log.info("Executing verifyText() on Element:"+locatorName);
	  try{
			//WebElement element1=driver.findElement(xpath);
		  Select s=new Select(locator);
		   text=s.getFirstSelectedOption().getText();
		   System.out.println("Text for "+locatorName+"is :"+text);
		   log.info("Text for "+locatorName+"is :"+text);
		   //Reports.addToReport("Selected: "+Constants.monthsValue(index-1)+ " from selectbox: "+element+"<>SELECT-PASS<>"+Page,PASS);
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			}
	  return text;
}
  //////////////////////////////////////////////////////////////////////////////////
//----------------------------> Select dropdown value-------------------------------------->
  public void selectByVisibleText(WebElement locator,String text,String locatorName){
	  System.out.println("Executing Select on: "+locatorName);
	  log.info("Executing Select on: "+locatorName);
	 if(!text.equals("")){
	  try{
		   boolean isSelected=false;
		    Select s = new Select(locator);
		    
			try{
		  s.selectByVisibleText(text);
		
			log.info("Selected: "+text+ " from selectbox: "+locatorName);
			isSelected=true;
			}catch(Exception e){
				log.info("No able to select: "+text+ " from selectbox: "+locatorName);
			    isSelected=false;
			}
			if(!isSelected){
			try{
			    s.selectByValue(text);
				log.info("Selected: "+text);
				isSelected=true;
				}catch(Exception e){
					log.info("Not able to select-"+text);
				    isSelected=false;
				}
			}
			if(!isSelected){
				try{
				s.selectByIndex(1);
								}catch(Exception e){
						}
				}
			
		    
		    }catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
			  throw new SkipException("Not Found Locator : "+locatorName);
			}
	 }
	 else{
	 }
  }

//--------------------------------Select Element By Index--------------------------------------------->
  protected void selectByIndex(WebElement locator,int index,String LocatorName){
	  System.out.println("Executing selectByIndex on: "+LocatorName);
	  log.info("Executing selectByIndex on: "+LocatorName);
	  try{
		    Select s = new Select(locator);
			s.selectByIndex(index);
	
		}catch(Exception e){
			  System.out.println("Not found: "+LocatorName);
			  log.info("Not found: "+LocatorName);
		  }
  }
  
//---------------------------- get number ---------------------------------------->
  protected String getNumber(String string) {    
      int startIndex=0;
      int endIndex=0;
      
          for (int i = 0; i < string.length(); i++) {
              char ch = string.charAt(i);
              if (Character.isDigit(ch)) {
              	startIndex=i;
                  break;
              }
             }

          for (int j = startIndex; j < string.length(); j++) {
              char ch = string.charAt(j);
              if (Character.isDigit(ch)) {
              	endIndex=j;
              }
             }
          return string.substring(startIndex,endIndex+1);
        }

//Return integer in form of string from format xx.y to xx
  public String getInteger(String string) {    
      int endIndex=0;
      
          for (int i = 0; i < string.length(); i++) {
              char ch = string.charAt(i);
              if (ch=='.') {
            	  endIndex=i;
                  break;
              }
             }
          return string.substring(0,endIndex);
        }
  
  //------------------------------> To escape Dialog ------------------------------------->
  public void perform_escape() {
      Actions action = new Actions(driver);
  	  action.sendKeys(Keys.ESCAPE).build().perform();
      }
  
  //------------------------------Select item from a list--------------------------------->
  protected void selectItem(List<WebElement> locator, String text, String locatorName){		
	  System.out.println("Executing Select on: "+locatorName);
	  log.info("Executing Select on: "+locatorName);
	  try{			   
			List<WebElement> wElement = locator;
			for (WebElement webElement : wElement) {
				String val=webElement.getText().trim();
				if(val.equals(""))
					val=webElement.getAttribute("value").trim();
				if (val.equalsIgnoreCase(text.trim())) {
					webElement.click();
					break;
					
				}
			}
		}catch(Exception e){
			  System.out.println("Not found: "+locatorName);
			  log.info("Not found: "+locatorName);
				throw new SkipException("not found : "+locatorName);
		}
}
  //----------------------------Move to element---------------------------
  public void moveToElement(WebElement locator,String locatorName){
	
	  try{
		  Actions actions = new Actions(driver);
		  actions.moveToElement(locator).build().perform();
		//  actions.clickAndHold().build().perform();
		  System.out.println("Executing Move to Element on Element:"+locatorName);
		  log.info("Executing Move to Element on Element:"+locatorName);
	  }catch(Exception e){
		  System.out.println("Not found: "+locatorName);
		  log.info("Not found: "+locatorName);
		throw new SkipException("Not Found : "+locatorName);
	  }
  }
  
//Mouse over using javascript
  public void mouseHoverJScript(By el) {
		try {
			WebElement mainMenu=driver.findElement(el);
			boolean val=driver.findElement(el).isDisplayed();
					if(val)
					{
				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,mainMenu);
				waitFixedTime(3);
					}
					else
					{
						System.out.println("element not present");
					}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace()); 
		}
	}
  

  public void scrollByCordinates(int x, int y)throws Exception{
      try{
             ((JavascriptExecutor)driver).executeScript("window.scrollBy("+x+","+y+")");
      }catch(Exception e){
             System.out.println("Unable to scroll");
             log.info("Unable to scroll");
      }
   }
  
  
  //-------------------> scroll Down java script ---------------------------->

  public void ScrollDown() throws Exception {
  
      JavascriptExecutor jse = (JavascriptExecutor) driver;
      jse.executeScript("window.scrollBy(0,750)", "");
  }
  
//------------------------> Explicite Wait command ----------------------------------->
	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	public void clickWhenReady(WebElement locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	public WebElement fluentWait(final WebElement locator, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return locator;
			}
		});

		return element;
	}

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	/*public void DateSelection(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		  js.executeScript("document.getElementById('ContentPlaceHolder_txtLeaveFromDt').value='2017-08-21'");
	}*/
	
	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}
  
	public void switchToDefaultContent(){
		driver.switchTo().defaultContent();
		log("switched to the default Content");
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
	public boolean isAlertPresent() {
	    try {
	        driver.switchTo().alert().accept();
	        return true;
	    } 
	    catch (Exception e) {
	        return false;
	    }
	}
	
	 public boolean NoAlertPresentException() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }
	
	public void AlertAcceptIfPresent(){
		if(isAlertPresent()){
		}else{}
	}
	
	/// -----------> date selection ------------------------>
			//--------------------------------------------------------------------------->
			
			public void DateSelection(String Exceldate,WebElement calender_xpath,WebElement next_id,WebElement middleButton_id,WebElement prev_id,List<WebElement> AllmonthValue_xpath,List<WebElement> allDateValue_xpath) throws InterruptedException
			{
				   //button to open calendar
			String dateTime = Exceldate;
			//String dateTime = "9/10/2017";
			WebElement selectDate = calender_xpath;
			selectDate.click();
			//button to move next in calendar
			WebElement nextLink = next_id;
			//button to click in center of calendar header
			WebElement midLink = middleButton_id;
			//button to move previous month in calendar
			WebElement previousLink = prev_id; 
		    //Split the date time to get only the date part
		    String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
		    //get the year difference between current year and year to set in calander
		    int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2])- Calendar.getInstance().get(Calendar.YEAR);
		    midLink.click();
		    if(yearDiff!=0){
	        //if you have to move next year
			        if(yearDiff>0){
			            for(int i=0;i< yearDiff;i++){
			                System.out.println("Year Diff->"+i);
			                nextLink.click();
			            }
			        }
			        //if you have to move previous year
			        else if(yearDiff<0){
			            for(int i=0;i< (yearDiff*(-1));i++){
			                System.out.println("Year Diff->"+i);
			                previousLink.click();
			            }
			        }
			    }
		    	this.waitFor(1);
		      	//Get all months from calendar to select correct one
			    List<WebElement> list_AllMonthToBook = AllmonthValue_xpath;
			    list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1])-1).click();
			    this.waitFor(1);
			    //get all dates from calendar to select correct one
			    List<WebElement> list_AllDateToBook = allDateValue_xpath;
			    list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0])-1).click();
   			}
	}
