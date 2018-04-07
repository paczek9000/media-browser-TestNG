package main.pageObjects;


import lombok.Data;
import main.config.DriverFactory;
import main.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;


@Data
public class DirectoryPage {
    private String nameOfDirectory = "Media Library /";
    private DetailsPage detailsPage;

    @FindBy(how = How.XPATH, using = "//p[@class='details-bar__description--name']")
    private WebElement fileName;

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

    @FindBy(how = How.XPATH, using = "//div[@class='uploadAction__list']")
    private WebElement uploadedFilesList;

    @FindBy(how = How.XPATH, using = "//div[@class='list-view']")
    private WebElement gridViewAllItems;

    @FindBy(how = How.XPATH, using = "//div[@class='uploadAction__controlls']/button/span[text()='Done']")
    private WebElement doneButton;

    /**
     * Default contructor for DirectoryPage with a default breadcrumb "Media Library /"
     *
     * @throws Exception
     */
    public DirectoryPage() throws Exception {
    }

    /**
     * A methods that creates a custom DirectoryPage and adds its name to the breadcrumbs
     *
     * @param nameOfDirectory the name of the new directory page
     * @return returns a new directory with a custom name and breadcrumbs
     * @throws Exception
     */
    public DirectoryPage withName(String nameOfDirectory) throws Exception {
        if (this.nameOfDirectory.equals("Media Library /")) {
            this.nameOfDirectory += " " + nameOfDirectory + " ";
        } else {
            this.nameOfDirectory += " / " + nameOfDirectory + " ";
        }
        return this;
    }

    /**
     * The method initialize elements of the DirectoryPage and navigates to its URL
     *
     * @param driver the current Webdriver
     * @param url    the URL of the directory page
     * @return the new Directory page to which the user has been navigated
     */
    public DirectoryPage goTo(WebDriver driver, String url) {
        PageFactory.initElements(driver, this);
        driver.get(url);
        return this;
    }

    /**
     * The method allows user to change layout to the listView and vice versa
     */
    public void changeBetweenViews() {
        listView.click();
    }

    /**
     * The methiod returns a number of tags assigned to the selected item
     *
     * @return an integer showing how many tags has been assigned to the selected item
     */
    public int getNumberOfTagsAssigned() {
        detailsPage = new DetailsPage();
        return detailsPage.getTags().size();
    }

    /**
     * The methods selects an item on the grid, so it's details should be displayed
     *
     * @param itemNameToSelect the name of the item to be selected
     * @return the DirectoryPage
     */
    public DirectoryPage selectItem(String itemNameToSelect) {

        List<WebElement> filesListInDirectory = gridViewAllItems.findElements(By.xpath(".//div[@class='list-view__item']"));
        long stopTime = System.nanoTime();
        List<WebElement> searchingElement = null;

        Actions actions = new Actions(DriverFactory.getDriver());
        for (WebElement element : filesListInDirectory) {
            searchingElement = element.findElements(By.xpath(".//div/div/div/p[text()='" + itemNameToSelect + "']"));
            if (!searchingElement.isEmpty()) {
                Helper.scrollToElement(DriverFactory.getDriver(), searchingElement.get(0));
                actions.moveToElement(searchingElement.get(0)).moveByOffset(200, 0).click().build().perform();
            }
        }
        return this;

    }

    /**
     * The method retrives selected item details
     *
     * @return returns details of the selected item as a key, value pairs
     */
    public HashMap getItemDetails() {
        detailsPage = new DetailsPage();
        HashMap itemDetails = new HashMap();
        itemDetails.put("fileName", detailsPage.getFileName().getText());
        itemDetails.put("dataType", detailsPage.getDataType().getText().substring(detailsPage.getDataType().getText().lastIndexOf(": ") + 2));
        itemDetails.put("creator", detailsPage.getDataCreator().getText().substring(detailsPage.getDataCreator().getText().lastIndexOf(": ") + 2));
        itemDetails.put("date", detailsPage.getDateCreation().getText().substring(detailsPage.getDateCreation().getText().lastIndexOf(": ") + 2));
        List<WebElement> tags = detailsPage.getTags();

        for (int i = 0; i < tags.size(); i++) {
            itemDetails.put("tagNo" + (i + 1), tags.get(i).getAttribute("textContent").substring(0,
                    tags.get(i).getAttribute("textContent").length() - 1));
        }
        return itemDetails;
    }

    /**
     * The moethods assigns tags to the assets on the UploadUI
     *
     * @param tags list of tags to be assigned
     * @throws Exception
     */
    public void assignTagsToAssets(String... tags) throws Exception {
        Helper.waitFor(DriverFactory.getDriver(), noTagsAssignedLabel,15);
        Actions actions = new Actions(DriverFactory.getDriver());
        for (String aNewTag : tags) {
            clickAddTagButton();
            actions.moveToElement(newTagInputField).sendKeys(aNewTag).moveToElement(uploadedFilesList).click().build().perform();

        }
        clickDoneButton();
    }

    /**
     * The methods click on the Add tags button on the UI
     */
    private void clickAddTagButton() {
        addTagsButton.click();
    }

    /**
     * The methods waits until Done button is displayed and then clicks on it
     */
    public void clickDoneButton() {
        Helper.waitFor(DriverFactory.getDriver(), doneButton, 15);
        doneButton.click();
    }

    /**
     * The method clicks upload Icon, chooses an option to upload files to current location, and uploads
     * files passed as arguments
     *
     * @param fileName List of file names to be uploaded from a default location which is located
     *                 under /imageResources/
     * @throws Exception
     */
    public void uploadImages(String... fileName) throws Exception {
        clickOnUploadIcon();
        Helper.waitFor(DriverFactory.getDriver(), uploadLightBox, 15);
        directlyUploadImageFilesToCurrentLocation();
        chooseFileToUpload(fileName);
        Helper.waitFor(DriverFactory.getDriver(), uploadLightBox, 15);

    }

    /**
     * The method clicks on upload icon
     */
    private void clickOnUploadIcon() {
        uploadIcon.click();
    }

    /**
     * The method chooses to upload images to current location instead of extraction assets from archives
     */
    private void directlyUploadImageFilesToCurrentLocation() {
        uploadImageToCurrentDirectoryIcon.click();
    }

    /**
     * The method pastes files locations to the file chooser and then by pressesing enter confirms which files
     * are going to be uploaded
     *
     * @param fileNames File names, from which the absolutePaths of the file/s is going to be send to fileChooser
     * @throws InterruptedException
     */
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
            robot.setAutoDelay(300);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
        }

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
