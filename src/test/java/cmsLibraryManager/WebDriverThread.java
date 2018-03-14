package cmsLibraryManager;

import cmsLibraryManager.config.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverThread {

    public static DriverType defaultDriverType = DriverType.FIREFOX;
    public static DriverType selectedDriverType;

    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final String browser = System.getProperty("browser").toUpperCase();

    private WebDriver webdriver;

    public WebDriver getDriver() throws Exception {
        if (null == webdriver) {
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities capabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(capabilities);
        }
        return webdriver;

    }

    private void instantiateWebDriver(DesiredCapabilities capabilities) {
        System.out.println("Current Operating System:               " + operatingSystem);
        System.out.println("Current Architecture:                   " + systemArchitecture);
        System.out.println("Current Browser Selection:              " + selectedDriverType);
        System.out.println(" ");
        webdriver = selectedDriverType.getWebDriverObject(capabilities);
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println(" Unknown Driver type, defaulting to ' " + defaultDriverType + "' ...");
        } catch (NullPointerException ignored) {
            System.err.println(" Unknown Driver type, defaulting to ' " + defaultDriverType + "' ...");
        }
        return driverType;
    }

    public void quitDriver() {
        if (null != webdriver) {
            webdriver.quit();
            webdriver = null;
        }
    }
}

