package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentReportUtility implements ITestListener {
	
	public ExtentSparkReporter SparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	public void onStart(ITestContext testContext)
	{
		String timestamp=new SimpleDateFormat("YY.MM.DD.HH.mm.ss").format(new Date());
		
		repname = "Test-report-"+timestamp+".html";
		//SparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\repname"); //Location of report
		
		SparkReporter = new ExtentSparkReporter(".\\reports\\"+repname);
		SparkReporter.config().setDocumentTitle("Opencart Automation Report");
		SparkReporter.config().setReportName("Opencart Functional Testing");
		SparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(SparkReporter);
		
		extent.setSystemInfo("Application","Opencart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("SubModule","Customers");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
	List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
				if(!includeGroups.isEmpty())
				{
					extent.setSystemInfo("Groups",includeGroups.toString());
				}
	}
	

	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		//test.log(Status.FAIL, "Testcase failed cause is = "+result.getThrowable());
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		//Opening the report directly once execution is completed
		
		String pathofextentreport = System.getProperty("user.dir")+"\\reports\\"+repname;
		File extentReport = new File(pathofextentreport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	/*	
		try
		{
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repname);
			
			//Create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
		    email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    email.setHostName("smtp.googleemail.com");
		    email.setSmtpPort(465);
		    email.setAuthenticator(new DefaultAuthenticator("shivanigarg072@gmail.com","Shivani@123"));
		    email.setSSLOnConnect(true);
		    email.setFrom("shivanigarg072@gmail.com");
		    email.setSubject("Test Results");
		    email.setMsg("Please find the attached report...");
		    email.addTo("shivig2606@gmail.com"); //To add more id's
		    email.attach(url, "extent report","please check report");
		    email.send();
		}
		catch(Exception e)
		{
	       e.printStackTrace();
		}*/
	}
}
	
	


