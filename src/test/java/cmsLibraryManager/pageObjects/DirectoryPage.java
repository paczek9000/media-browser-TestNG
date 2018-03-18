package cmsLibraryManager.pageObjects;

import cmsLibraryManager.config.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import sun.security.util.PendingException;
import utils.Helper;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

public class DirectoryPage {
    private String nameOfDirectory = "Media Library /";

    public DirectoryPage() {
    }

    public DirectoryPage withName(String nameOfDirectory) throws Exception {
        if (this.nameOfDirectory.equals("Media Library /")) {
            this.nameOfDirectory += " " + nameOfDirectory + " ";
        } else {
            this.nameOfDirectory += " / " + nameOfDirectory + " ";
        }
        return this;
    }

    public DirectoryPage goTo(WebDriver driver, String url) {
        PageFactory.initElements(driver, this);
        driver.get(url);
        return this;
    }

    public void listView() {
        listView.click();
    }

    public void uploadSingleImage(String fileName) throws Exception {
        clickOnUploadIcon();
        Helper.waitUntilElementIsDisplayed(DriverFactory.getDriver(), uploadLightBox);
        directlyUploadImageFilesToCurrentLocation();
        chooseFileToUpload(fileName);
        Helper.waitUntilElementIsDisplayed(DriverFactory.getDriver(), uploadLightBox);
    }

    private void directlyUploadImageFilesToCurrentLocation() {
        uploadImageToCurrentDirectoryIcon.click();
    }

    private void chooseFileToUpload(String fileName) {

        StringSelection filePath = new StringSelection(Helper.getFilePath(fileName));
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.print(e);
        }
        robot.setAutoDelay(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void clickOnUploadIcon() {
        uploadIcon.click();
    }

    @FindBy(how = How.XPATH, using = "//*[@class='breadCrumbs__root']")
    private WebElement breadCrumbsRoot;

    @FindBy(how = How.XPATH, using = "//div[@class='library-manager__container--advanced']/*[@class='MuiSvgIcon-root-1 library-manager__container--upload']")
    private WebElement uploadIcon;

    @FindBy(how = How.XPATH, using = "//div[@class='uploadFolder__inner-container']")
    private WebElement uploadImageToCurrentDirectoryIcon;

    @FindBy(how = How.XPATH, using = "//div[@class='upload-modal__content']")
    private WebElement uploadLightBox;

    @FindBy(how = How.XPATH, using = "//div[@class='library-manager__container--rightmost']/*[@class='MuiSvgIcon-root-1 library-manager__container--view']")
    private WebElement listView;

    public DirectoryPage selectItem(String s) {
        throw new PendingException();
    }

    public void addTagsOnTheUplaodUI(String randomTagNameOfLength) {
        throw new PendingException();
    }

    public void clickDoneButton() {
        throw new PendingException();
    }

    public Hashtable getItemDetails() {
        Hashtable itemDetails = new Hashtable();
        return itemDetails;
    }
}
