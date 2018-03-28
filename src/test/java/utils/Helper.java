package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Helper {
    public static void waitUntilElementIsDisplayed(WebDriver driver, WebElement element) {
        (new WebDriverWait(driver, 16)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return element.isDisplayed();
            }
        });
    }
    public static String getFilePath(String fileName){
        String filePath = "";
        //File file = new File(System.getProperty("user.dir") + File.separatorChar + System.getProperty("imageResources") + File.separatorChar + fileName);
        File file = new File(System.getProperty("user.dir") + File.separatorChar + "imageResources" + File.separatorChar + fileName);

        filePath = file.getAbsolutePath();
        return filePath;
    }

    public static String getRandomTagNameOfLength(int i) {
        String randomTagName = "";
        return randomTagName;
    }
    public static void scrollToElement(WebDriver driver, WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
