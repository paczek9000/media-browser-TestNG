package main.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Helper {
    public static void waitFor(WebDriver driver, WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
        }
    public static void waitFor(WebDriver driver, By by, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)));
    }
    public static String getFilePath(String fileName){
        String filePath = "";
        // Comment one if started from maven / or locally
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
