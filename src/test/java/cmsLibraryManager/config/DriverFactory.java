package cmsLibraryManager.config;

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

    private static List<WebDriverThread> webDriverThreadPool =
            Collections.synchronizedList(new ArrayList<WebDriverThread>());
    private static ThreadLocal<WebDriverThread> driverThreadThreadLocal;
    private static String baseUrl;

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

    public static WebDriver getDriver() {
        try {
            return driverThreadThreadLocal.get().getDriver();
        } catch (Exception driverInitException) {
            System.err.print("Exception while creating a driver instance  " + "\n " + driverInitException.getMessage());
            return null;
        }
    }


    public static String getEnvironmentUrl() {
        baseUrl = WebDriverThread.getEnvironmentUrl();
        return baseUrl;
    }

    @AfterMethod
    public static void quitDriver() throws Exception {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public static void closeDriverObjects() {
        for (WebDriverThread webDriverThread : webDriverThreadPool) {
            webDriverThread.quitDriver();
        }
    }

}
