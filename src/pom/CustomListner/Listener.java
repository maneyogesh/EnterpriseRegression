package pom.CustomListner;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import pom.TestBase.TestBase;

public class Listener extends TestBase implements ITestListener, IInvokedMethodListener {

	WebDriver driver;
   
	public void onFinish(ITestContext arg0) {
		Reporter.log("Test finished : " + arg0.getName());
		
	}

	public void onStart(ITestContext arg0) {
		Reporter.log("Test started :  " + arg0.getName(), true);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	
	}
	//------------------------------------------------------------------------------------------>
	private void printTestResults(ITestResult result) {
		System.out.println(result.getTestName());
////////	//Reporter.log("TestName =: " + result.getTestName(), true);
			Reporter.log("Test Method Resides : " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "PASSED";
			break;
		case ITestResult.FAILURE:
			status = "FAILED";
			break;
		case ITestResult.STARTED:
			status = "STARTED";
			break;
		case ITestResult.SKIP:
			status = "SKIPPED";
		}
		Reporter.log("Test Status : " + status, true);
	}
	
	
	public void onTestFailure(ITestResult result) {
		printTestResults(result);
		/*if(!result.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = result.getName();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/pom/Screenshots/";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				
				FileUtils.copyFile(scrFile, destFile);
				
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
	}


	public void onTestSkipped(ITestResult arg0) {
		Reporter.log("Test Case Name : " + arg0.getMethod().getMethodName());
	printTestResults(arg0);
		
	}

	public void onTestStart(ITestResult arg0) {
		Reporter.log("Test Case Name : " + arg0.getMethod().getMethodName());
		printTestResults(arg0);
	}

	public void onTestSuccess(ITestResult arg0) {
		Reporter.log("Test Case Name : " + arg0.getMethod().getMethodName());
		printTestResults(arg0);
		/*if(arg0.isSuccess()){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			
			String methodName = arg0.getName();

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/pom/Screenshots/";
				File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");
				
				FileUtils.copyFile(scrFile, destFile);
				
				Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
*/
}
	
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Completed Executing : " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Before Executing : " + returnMethodName(arg0.getTestMethod());
//////	//	Reporter.log(textMsg, true);
		
	}
}