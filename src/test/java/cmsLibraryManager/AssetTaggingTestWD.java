package cmsLibraryManager;

import cmsLibraryManager.config.DriverFactory;
import cmsLibraryManager.pageObjects.DirectoryPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.Helper;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import static org.hamcrest.CoreMatchers.is;

public class AssetTaggingTestWD extends DriverFactory {

    WebDriver driver;
    DirectoryPage homePage;

    public AssetTaggingTestWD()throws Exception{
        try {
            driver = getDriver();
        } catch (Exception e) {
            System.out.println("Unable to initialize the driver " + e.getMessage());
        }
        homePage = new DirectoryPage();
    }


    @Test(  groups = "assetTagging",
            testName = "Tagging one image asset with only one character on the UI on Uploads",
            description = "the user should be able to tag the asset on the UI for uploads, even with ONE character and even if the user is uploading only one asset")
    public void taggingImageAssetWithOneCharacterOnUploadUI() throws Exception{
        homePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        homePage.uploadSingleImage("Test.jpg");
        homePage.addTagsOnTheUplaodUI(Helper.getRandomTagNameOfLength(1));
        homePage.clickDoneButton();
        Hashtable selectedItemDetails = homePage.selectItem("Test.jpg").getItemDetails();
        Assert.assertThat(
                selectedItemDetails.get("creation date"), is(LocalTime.now()));



    }

    public static void main(String[] args) {
        System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.format(DateTimeFormatter.ofPattern("MM d YYYY")));
    }
}
