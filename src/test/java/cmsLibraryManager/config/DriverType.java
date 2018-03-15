package cmsLibraryManager.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by lpaczek on 14.03.2018.
 */
public enum DriverType implements DriverSetup {
    FIREFOX {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
            return desiredCapabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            FirefoxOptions options = new FirefoxOptions(desiredCapabilities);
            return new FirefoxDriver(options);
        }
    },
    CHROME {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability("chrome.switches", Arrays.asList("--no-defauls-browser-check"));
            HashMap<String, String> chromePreferences = new HashMap<String, String>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            desiredCapabilities.setCapability("chrome.prefs", chromePreferences);

            return desiredCapabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            ChromeOptions options = new ChromeOptions();
            return new ChromeDriver(options.merge(desiredCapabilities));
        }
    },
    IE {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
            capabilities.setCapability("requireWindowFocus", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            InternetExplorerOptions options = new InternetExplorerOptions(desiredCapabilities);
            return new InternetExplorerDriver(options);
        }
    },
    SAFARI {
        public DesiredCapabilities getDesiredCapabilities() {
            DesiredCapabilities capabilities = DesiredCapabilities.safari();
            capabilities.setCapability("safari.cleanSession", true);
            return capabilities;
        }

        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            SafariOptions options = new SafariOptions(desiredCapabilities);
            return new SafariDriver(options);
        }

    },
    EDGE {
            public DesiredCapabilities getDesiredCapabilities() {
                DesiredCapabilities capabilities = DesiredCapabilities.edge();
                return capabilities;
            }

        public WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities) {
            EdgeOptions options = new EdgeOptions();
            return new EdgeDriver(options.merge(desiredCapabilities));
        }
    }


}
