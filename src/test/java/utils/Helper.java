package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Helper {
    public static void waitUntilElementIsDisplayed(WebDriver driver, WebElement element) {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return element.isDisplayed();
            }
        });
    }
    public static String getFilePath(String fileName){
        String filePath = "";
        File file = new File(System.getProperty("user.dir") + File.separatorChar + System.getProperty("imageResources") + File.separatorChar + fileName);

        filePath = file.getAbsolutePath();
        return filePath;
    }

    public static String getRandomTagNameOfLength(int i) {
        String randomTagName = "";
        return randomTagName;
    }
}
