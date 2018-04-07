package main.config;

import main.config.enums.DriverType;
import main.config.enums.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverThread {

    private static DriverType defaultDriverType = DriverType.FIREFOX;
    private static DriverType selectedDriverType;
    private static Environment environment = determineEnvironment();
    private WebDriver webdriver;


    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    //commented if not runned from maven
     //private final String browser = System.getProperty("browser").toUpperCase();
    private final String browser = "firefox";

    private static Environment determineEnvironment() {
        Environment environment = Environment.DEV;
        try {
            environment = Environment.valueOf("env");
        }catch (IllegalArgumentException ignored){
            System.err.print("Unknown Environment, defaulting to : " + environment);
        }catch (NullPointerException ignored){
            System.err.print("No Environment set, defaulting to  : " + environment);
        }
        return environment;
    }
    public static String getEnvironmentUrl(){
        return environment.getEnvironmentUrl();
    }


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
        System.out.println("Current Enviroment:                     " + environment);
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
            System.err.println(" No Driver type set, defaulting to ' " + defaultDriverType + "' ...");
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

