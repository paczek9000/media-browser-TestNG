package cmsLibraryManager;

import cmsLibraryManager.config.DriverFactory;
import cmsLibraryManager.pageObjects.DirectoryPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utils.Helper;

import java.util.Hashtable;

public class AssetTaggingTestWD extends DriverFactory {

    WebDriver driver;
    DirectoryPage homePage;

    public AssetTaggingTestWD() throws Exception {
        try {
            driver = getDriver();
        } catch (Exception e) {
            System.out.println("Unable to initialize the driver " + e.getMessage());
        }
        homePage = new DirectoryPage();
    }


    @Test(groups = "assetTagging",
            testName = "Tagging multiple image assets on the UI for Uploads",
            description = "the user should be able to tag the assets on the UI for uploads" +
                    "with tag's length up to 50 characters")
    public void taggingImageAssetsOnUploadUI() throws Exception {

        homePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
    }

    @Test(groups = "assetTagging",
            testName = "Adding a 51-characters tag to assets on the UI for Uploads",
            description = "the user should be not able to define a tag with 51 characters")
    public void taggingImageAssetsWith51Characters() throws Exception {


    }
    @Test(groups = "assetTagging",
            testName = "Adding a 0-character tag on the UI for Uploads",
            description = "the user should not be able to define a tag with 0 characters")
    public void taggingImageAssetsWithNoneCharactersOnTheUploadUI() throws Exception {


    }

    @Test(groups = "assetTagging",
            testName = "Adding a one-letter tag to the asset on the UI for Uploads",
            description = "the user should be able to tag the asset on the UI for uploads, " +
                    "even with ONE character and even if the user is uploading only one asset")
    public void taggingImageAssetWithOneCharacterOnUploadUI() throws Exception {
        homePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        homePage.uploadSingleImage("Test.jpg");
        homePage.addTagsOnTheUplaodUI(Helper.getRandomTagNameOfLength(1));
        homePage.clickDoneButton();
        Hashtable selectedItemDetails = homePage.selectItem("Test.jpg").getItemDetails();
       /* Assert.assertThat(
                selectedItemDetails.get("creation date"), is(LocalTime.now()));*/

    }
    @Test(groups = "assetTagging",
            testName = "Tagging and deleting a tag on the UI for Uploads",
            description = "the user should be able to add a tag and delete it on the UI for Uploads" +
                    "so the asset is not going to have this tag")
    public void taggingAndDeletingATagOnTheUploadUI() throws Exception {

    }
    //ListView scenarios
    @Test(groups = "assetTagging",
            testName = "Tagging and deleting a tag on the ListView",
            description = "the user should be able to add a tag and delete it on the ListView" +
                    "so the asset is not going to have this tag")
    public void taggingAndDeletingATagOnTheListView() throws Exception {

    }
    @Test(groups = "assetTagging",
            testName = "Tagging multiple image assets on the ListView",
            description = "the user should be able to tag the assets on the ListView")
    public void taggingImageAssetsOnListView() throws Exception {

    }

    @Test(groups = "assetTagging",
            testName = "Adding a 51-characters tag to the asset on the ListView",
            description = "the user should not be able to define a tag with 51 characters")
    public void taggingImageAssetsWith51CharactersOnListView() throws Exception {

    }
    @Test(groups = "assetTagging",
            testName = "Adding a 0-character tag on the ListView",
            description = "the user should not be able to define a tag with 0 characters")
    public void taggingImageAssetsWithNoneCharactersOnTheListView() throws Exception {

    }

    @Test(groups = "assetTagging",
            testName = "Tagging one image asset with only one character on the ListVIew",
            description = "the user should be able to tag the asset on the ListView, even with ONE character")
    public void taggingImageAssetWithOneCharacterOnListView() throws Exception {

    }



}