package cmsLibraryManager.pageObjects;

import cmsLibraryManager.config.DriverFactory;
import lombok.Data;
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

@Data
public class DirectoryPage {
    private String nameOfDirectory = "Media Library /";

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
    @FindBy(how = How.XPATH, using = "//div[@class='uploadAction__controlls--equal']/*/p[@class='rounded-button__value']")
    private WebElement addTagsButton;
    @FindBy(how = How.XPATH, using = "//p[@class='tags-container__no-tags-message']")
    private WebElement noTagsAssignedLabel;
    @FindBy(how = How.XPATH, using = "//p[@class='tag__title--dark']/input")
    private WebElement newTagInputField;

    public DirectoryPage() throws Exception {
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

    public void assignTagsToAssets(String... tags) {
        Helper.waitUntilElementIsDisplayed(DriverFactory.getDriver(), noTagsAssignedLabel);

        for(String aNewTag: tags) {
            clickAddTagButton();
        
            newTagInputField.sendKeys(aNewTag);
            uploadLightBox.click();
        }
    }

    private void clickAddTagButton() {
        addTagsButton.click();
    }

    public void uploadImage(String... fileName) throws Exception {

        clickOnUploadIcon();
        Helper.waitUntilElementIsDisplayed(DriverFactory.getDriver(), uploadLightBox);
        directlyUploadImageFilesToCurrentLocation();
        chooseFileToUpload(fileName);
        Helper.waitUntilElementIsDisplayed(DriverFactory.getDriver(), uploadLightBox);
    }

    private void clickOnUploadIcon() {
        uploadIcon.click();
    }

    private void directlyUploadImageFilesToCurrentLocation() {
        uploadImageToCurrentDirectoryIcon.click();
    }

    private void chooseFileToUpload(String... fileNames) throws InterruptedException {
        Robot robot = null;
        StringSelection filePath;
        for (String fileName : fileNames) {

            filePath = new StringSelection("\"" + Helper.getFilePath(fileName + "\" "));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

            try {
                robot = new Robot();
            } catch (AWTException e) {
                System.err.print(e);
            }
            robot.setAutoDelay(100);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        }

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
