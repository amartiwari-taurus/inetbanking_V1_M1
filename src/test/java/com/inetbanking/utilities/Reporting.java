package com.inetbanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testcases.BaseClass;

public class Reporting extends TestListenerAdapter
{
	
	    public ExtentSparkReporter sparkReporter;   // ✅ changed
	    public ExtentReports extent;
	    public ExtentTest logger;

	    public void onStart(ITestContext testContext)
	    {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        String repName = "Test-Report-" + timeStamp + ".html";

	        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + repName);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);

	        extent.setSystemInfo("Host name", "localhost");
	        extent.setSystemInfo("Environment", "QA");
	        extent.setSystemInfo("User", "Taurus");

	        sparkReporter.config().setDocumentTitle("InetBanking Test Project");
	        sparkReporter.config().setReportName("Functional Test Automation Report");
	        sparkReporter.config().setTheme(Theme.DARK);
	    }

	    public void onTestSuccess(ITestResult tr)
	    {
	        logger = extent.createTest(tr.getName());
	        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	    }

	   /* public void onTestFailure(ITestResult tr)
	    {
	        logger = extent.createTest(tr.getName());
	        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

	        String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

	        File f = new File(screenshotPath);

	        if (f.exists())
	        {
	            logger.addScreenCaptureFromPath(screenshotPath);
	            logger.fail("Screenshot attached");
	        }
	    }*/
		public void onTestFailure(ITestResult tr)
		{
			logger=extent.createTest(tr.getName()); // create new entry in th report
			logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
			
			String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
			
			File f = new File(screenshotPath); 
			
			if(f.exists())
			{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			}
			
		}
	    public void onTestSkipped(ITestResult tr)
	    {
	        logger = extent.createTest(tr.getName());
	        logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	    }

	    public void onFinish(ITestContext testContext)
	    {
	        extent.flush();
	    }


	}

