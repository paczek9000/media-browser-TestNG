package main.config.driver;

import io.appium.java_client.AppiumDriver;
import
        io.appium.java_client.MobileElement;
import main.config.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Map;

import static org.openqa.selenium.remote.BrowserType.CHROME;

/**
 * Created by lpaczek on 14.03.2018.
 */
public class DriverFactory {


    ;
    private static DriverFactory instance = null;
    private String browserHandle = null;
    private static final int IMPLICIT_TIMEOUT = 0;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<AppiumDriver<MobileElement>> mobileDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();
    private ThreadLocal<String> sessionBrowser = new ThreadLocal<String>();
    private ThreadLocal<String> sessionPlatform = new ThreadLocal<String>();
    private ThreadLocal<String> sessionVersion = new ThreadLocal<String>();
    private String getEnv = null;

    private DriverFactory() {
    }

    @SuppressWarnings("varargs")
    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    @SafeVarargs
    public final void setDriver(String browser,
                                String environment,
                                String platform,
                                Map<String, Object>... optPreferences) throws Exception {
        DesiredCapabilities caps = null;
        String localHub = "";
        DriverType driverType = null;
        String getPlatform = null;
        FirefoxOptions options = new FirefoxOptions();

        switch (browser) {
            case "FIREFOX": {

                driverType = DriverType.FIREFOX;
                if (optPreferences.length > 0) {
                    processFirefoxProfile(options, optPreferences);
                } else {
                    caps = driverType.getDefaultCapabilities();
                    options.merge(caps);
                }
                webDriver.set(new FirefoxDriver(options));
                break;
            }
            case CHROME: {
                break;
            }
        }


    }

    public void setDriver(WebDriver driver) {
        webDriver.set(driver);
        sessionId.set(((RemoteWebDriver) webDriver.get()).getSessionId().toString());
        sessionBrowser.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getBrowserName());
        sessionPlatform.set(((RemoteWebDriver) webDriver.get()).getCapabilities().getPlatform().toString());
        // driver.setBrowserHandle(getDriver().getWindowHandle());
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        sessionId.set(mobileDriver.get().getSessionId().toString());
        sessionBrowser.set(mobileDriver.get().getCapabilities().getBrowserName());
        sessionPlatform.set(mobileDriver.get().getCapabilities().getPlatform().toString());
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public AppiumDriver<MobileElement> getDriver(boolean mobile) {
        return mobileDriver.get();
    }

    public WebDriver getCurrentDriver() {
            if (getInstance().getSessionBrowser().contains("iphone") ||
                getInstance().getSessionBrowser().contains("ipad") ||
                getInstance().getSessionBrowser().contains("android")) {
            return getInstance().getDriver(true);
            } else {
            return getInstance().getDriver();
        }
    }

 /*   public void closeDriver() {
    }{
        getCurrentDriver().close();
        webDriver = null;
    }*/
    public void quitDriver(){
        getCurrentDriver().quit();
    }
    public void clearAllCokies() {
        getCurrentDriver().manage().deleteAllCookies();
    }

    /**
     * getSessionId method gets the browser or mobile id * of the active session * * @return String
     */
    public String getSessionId() {
        return sessionId.get();
    }

    /**
     * getSessionBrowser method gets the browser or mobile type * of the active session * * @return String
     */
    public String getSessionBrowser() {
        return sessionBrowser.get();
    }

    /**
     * getSessionVersion method gets the browser or mobile version * of the active session * * @return String
     */
    public String getSessionVersion() {
        return sessionVersion.get();
    }

    /**
     * getSessionPlatform method gets the browser or mobile platform * of the active session * * @return String
     */
    public String getSessionPlatform() {
        return sessionPlatform.get();
    }

    public void processFirefoxProfile(FirefoxOptions options, Map<String, Object>[] preferences) {
        DesiredCapabilities cap = new DesiredCapabilities();
        for (int i = 0; i < preferences.length; i++) {
            Object[] keys = preferences[i].keySet().toArray();
            Object[] values = preferences[i].values().toArray();

            for (int j = 0; j < preferences.length; j++) {
                if (values[j] instanceof Integer) {
            cap.setCapability(
                    keys[j].toString(), (int) values[j]);
                } else if (values[j] instanceof String) {
                    cap.setCapability(keys[j].toString(), values[j].toString());
                } else if (values[j] instanceof Boolean) {
                    cap.setCapability(keys[j].toString(), (boolean) values[j]);
                }
            }
            options.merge(cap);
        }
    }
}