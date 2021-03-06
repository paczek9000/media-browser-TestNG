package cmsLibraryManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lpaczek on 14.03.2018.
 */
public class DriverFactory {

    public static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());
    public static ThreadLocal<WebDriverThread> driverThreadThreadLocal;

    @BeforeSuite
    public static void instantiateDriverObject() {
        driverThreadThreadLocal = new ThreadLocal<WebDriverThread>() {
            @Override
            protected WebDriverThread initialValue() {
                WebDriverThread webDriverThread = new WebDriverThread();
                webDriverThreadPool.add(webDriverThread);
                return webDriverThread;
            }
        };
    }

    public static WebDriver getDriver() throws Exception {
        return driverThreadThreadLocal.get().getDriver();
    }

    @AfterMethod
    public static void quitDriver() throws Exception {
        getDriver().manage().deleteAllCookies();
    }
    @AfterSuite
    public static void closeDriverObjects(){
        for(WebDriverThread webDriverThread : webDriverThreadPool){
            webDriverThread.quitDriver();
        }
    }
}
