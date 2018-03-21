package cmsLibraryManager;

import cmsLibraryManager.config.DriverFactory;
import cmsLibraryManager.pageObjects.DirectoryPage;
import org.testng.annotations.Test;

public class AssetTaggingTestWD extends DriverFactory {

    DirectoryPage mediaBrowserHomePage;

    public AssetTaggingTestWD() throws Exception {

        mediaBrowserHomePage = new DirectoryPage();
    }


    @Test(groups = "assetTagging",
            testName = "Tagging multiple image assets on the UI for Uploads",
            description = "the user should be able to tag the assets on the UI for uploads" +
                    "with tag's length up to 50 characters")
    public void taggingImageAssetsOnUploadUI() throws Exception {
        mediaBrowserHomePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        mediaBrowserHomePage.uploadImage("test1.jpg", "test2.jpeg", "test3.png");
        mediaBrowserHomePage.assignTagsToAssets("testTag50Characters12345!@#$%^&*()_+:\"?><{}[]~`/''", "test123", "test555", "testxxx");
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
/*        mediaBrowserHomePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        mediaBrowserHomePage.uploadImage("Test.jpg");
        mediaBrowserHomePage.addTagsOnTheUplaodUI(Helper.getRandomTagNameOfLength(1));
        mediaBrowserHomePage.clickDoneButton();
        Hashtable selectedItemDetails = mediaBrowserHomePage.selectItem("Test.jpg").getItemDetails();
        Assert.assertThat(
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