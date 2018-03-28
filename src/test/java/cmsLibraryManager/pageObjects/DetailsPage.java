package cmsLibraryManager.pageObjects;

import cmsLibraryManager.config.DriverFactory;
import lombok.Data;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Data
public class DetailsPage extends PageFactory{

    @FindBy(how = How.XPATH, using = "//p[@class='details-bar__description--name']")
    private  WebElement fileName;

    @FindBy(how = How.XPATH, using = "//p[@class='details-bar__details--type']")
    private WebElement dataType;

    @FindBy(how = How.XPATH, using = "//p[@class='details-bar__details--creator']")
    private WebElement dataCreator;

    @FindBy(how = How.XPATH, using = "//p[@class='details-bar__details--created']")
    private WebElement dateCreation;

    @FindBy(how = How.XPATH, using = "//div[@class='tag']")
    private List<WebElement> tags;

    public DetailsPage(){
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }
    public WebElement getFileName2(){
        return fileName;
    }

}
