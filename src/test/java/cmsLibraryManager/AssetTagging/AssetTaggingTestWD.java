package cmsLibraryManager.AssetTagging;

import main.config.DriverFactory;
import main.pageObjects.DirectoryPage;
import main.utils.AssetPOI;
import main.utils.JSONDataProvider;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AssetTaggingTestWD extends DriverFactory {
    public static String dataFile = "";
    public static String testCaseName = "AssetTagging.json";
    DirectoryPage mediaBrowserHomePage;
    public static Set<String> testingDataToBeCleaned;
    private final String DATA_FILE = "AssetTagging.json";

    public AssetTaggingTestWD() throws Exception {
        mediaBrowserHomePage = new DirectoryPage();
        testingDataToBeCleaned = new ConcurrentSkipListSet<>();
        JSONDataProvider.dataFile = DATA_FILE;
    }

    @Test(dataProvider="test1", description = "test", testName = "sdsd")
    public void tc001_taggingAssetsOnUploadUI( String rowID, String description, JSONObject object ) throws Exception {

        AssetPOI assetPOI = new AssetPOI(object);

        System.out.println(
                "Name of the asset : " + assetPOI.getName() +
                        "\nType of the  asset : " + assetPOI.getType() +
                        "\nPath of the asset : " + assetPOI.getPath() +"\n\n\n\n"
                        + rowID + "\n " +description
        );


        String[] testingTags = {
                "testTag50Characters12345!@#$%^&*()_+:\"?><{}[]~`/''",
                "test123",
                "test555",
                "testxxx"};

        String[] testingFilesToUpload = {
                "test1.jpg",
                "test2.jpeg",
                "test3.png"};
        testingDataToBeCleaned.addAll(Arrays.asList(testingTags));
        testingDataToBeCleaned.addAll(Arrays.asList(testingFilesToUpload));

        mediaBrowserHomePage.goTo(DriverFactory.getDriver(), "https://media-browser.dev-allsaints.com");
        // commented if started from maven
        // mediaBrowserHomePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        mediaBrowserHomePage.changeBetweenViews();
        mediaBrowserHomePage.uploadImages(testingFilesToUpload);
        mediaBrowserHomePage.assignTagsToAssets(testingTags);
        HashMap detailsOfSelectedItem = null;

        for (String fileUploaded : testingFilesToUpload) {
            detailsOfSelectedItem = mediaBrowserHomePage.selectItem(fileUploaded).getItemDetails();
            Assert.assertThat("First tag does not match ", detailsOfSelectedItem.get("tagNo1"), is(equalTo(testingTags[0])));
            Assert.assertThat("Second tag does not match ", detailsOfSelectedItem.get("tagNo2"), is(equalTo(testingTags[1])));
            Assert.assertThat("Third tag does not match ", detailsOfSelectedItem.get("tagNo3"), is(equalTo(testingTags[2])));
            Assert.assertThat("Fourth tag does not match ", detailsOfSelectedItem.get("tagNo4"), is(equalTo(testingTags[3])));

        }
    }

    @Test(groups = "assetTagging",
            testName = "Adding a 51-characters tag to assets on the UI for Uploads",
            description = "the user should be not able to define a tag with 51 characters")
    public void taggingImageAssetsWith51Characters() throws Exception {
        String testingTagWithNotAllowedNumberOfCharacters = "testTag50Characters12345!@#$%^&*()_+:\"?><{}[]~`/''1";
        String testingFilesToUpload = "test1.jpg";

        testingDataToBeCleaned.add(testingTagWithNotAllowedNumberOfCharacters);
        testingDataToBeCleaned.add(testingFilesToUpload);
        mediaBrowserHomePage.goTo(DriverFactory.getDriver(), "https://media-browser.dev-allsaints.com");
        // commented if started from maven
        // mediaBrowserHomePage.goTo(DriverFactory.getDriver(), DriverFactory.getEnvironmentUrl());
        mediaBrowserHomePage.changeBetweenViews();
        mediaBrowserHomePage.uploadImages(testingFilesToUpload);
        mediaBrowserHomePage.assignTagsToAssets(testingTagWithNotAllowedNumberOfCharacters);
        HashMap selectedItemDetails = mediaBrowserHomePage.selectItem(testingFilesToUpload).getItemDetails();
        Assert.assertThat("First tag does not match ", selectedItemDetails.get("tagNo1"), is(equalTo(testingTagWithNotAllowedNumberOfCharacters)));


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
        mediaBrowserHomePage.uploadImages("Test.jpg");
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


    @DataProvider(name = "test1")
    public static Object[][] fetchData(Method method) throws Exception {
        Object rowID, description;
        Object[][] result;
        testCaseName = method.getName();
        List<JSONObject> testDataList = new ArrayList<JSONObject>();
        JSONArray testData = (JSONArray) extractData_JSON("C:\\Users\\lpaczek\\Documents\\Projects\\media-browser-TestNG\\src\\test\\java\\cmsLibraryManager\\AssetTagging\\AssetTagging.json").get(method.getName());

        for ( int i = 0; i < testData.size(); i++ ) {
            testDataList.add((JSONObject) testData.get(i));
        }
/*
        // include Filter
        if ( System.getProperty("includePattern") != null ) {
            String include = System.getProperty("includePattern");
            List<JSONObject> newList = new ArrayList<JSONObject>();
            List<String> tests = Arrays.asList(include.split(",", -1));

            for ( String getTest : tests ) {
                for ( int i = 0; i < testDataList.size(); i++ ) {
                    if ( testDataList.get(i).toString().contains(getTest) ) {
                        newList.add(testDataList.get(i));
                    }
                }
            }

            // reassign testRows after filtering tests
            testDataList = newList;
        }*/

/*        // exclude Filter
        if ( System.getProperty("excludePattern") != null ) {
            String exclude =System.getProperty("excludePattern");
            List<String> tests = Arrays.asList(exclude.split(",", -1));

            for ( String getTest : tests ) {
                // start at end of list and work backwards so index stays in sync
                for ( int i = testDataList.size() - 1 ; i >= 0; i-- ) {
                    if ( testDataList.get(i).toString().contains(getTest) ) {
                        testDataList.remove(testDataList.get(i));
                    }
                }
            }
        }*/

        // create object for dataprovider to return
        try {
            result = new Object[testDataList.size()][testDataList.get(0).size()];

            for ( int i = 0; i < testDataList.size(); i++ ) {
                rowID = testDataList.get(i).get("rowID");
                description = testDataList.get(i).get("description");
                result[i] = new Object[] {  rowID, description, testDataList.get(i) };
            }
        }

        catch(IndexOutOfBoundsException ie) {
            result = new Object[0][0];
        }

        return result;
    }

    /**
     * extractData_JSON method to get JSON data from file
     *
     * @param file
     * @return JSONObject
     * @throws Exception
     */
    public static JSONObject extractData_JSON(String file) throws Exception {
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();

        return (JSONObject) jsonParser.parse(reader);
    }
}