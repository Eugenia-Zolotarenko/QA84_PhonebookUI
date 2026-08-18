package de.phonebook.core;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static ApplicationManager app =
            new ApplicationManager(System.getProperty(
                    "browser", Browser.CHROME.browserName()));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    //@BeforeMethod
    //@BeforeSuite Browser's window opens one time before test suit (before several tests,
    // not before each test as BeforeMethod)
    //works with  @AfterSuite
    @BeforeSuite  //for BeforeSuit must be  !!static!! ApplicationManager
    public void setUp(){
        app.init();
    }

    //@AfterMethod(enabled = false)
    @AfterSuite(enabled = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void startTest(Method method){
        logger.info("start test {}", method.getName());
    }

    @AfterMethod
    public void stopTest(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: {}", result.getMethod().getMethodName());
        } else {
            logger.error("FAILED: {}. Screenshot -> \n{} ",
                    result.getMethod().getMethodName(),
                    app.getUser().takeScreenshot());
        }
        logger.info("Stop test");
        logger.info("***************************************************");
    }
}
