package testConfig;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.util.Properties;

public class TestSetup {


    @BeforeSuite(alwaysRun = true)
    public void testSuiteSetup(ITestContext context)throws Exception{
        //save env variables
        //load properties
        //jenkins or jvm arguments
        //save baseUrl

        Properties properties = new Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir") + "\\" + "src\\main\\config\\driver\\selenium.properties"));
        System.out.println("-----------------------------------------------------------");
        System.out.println( properties.getProperty("test") + " ");
        System.out.println(properties.get("env")+ " ");
        System.out.println(properties.get("env").toString()+ " ");
    }
    @BeforeClass
    protected  void testClassSetup(){
        //path to Json fie dataset
        //default users
        //acounts
        //preferences specific to the class
        //
    }
    @BeforeTest
    protected void testSetip(){
        //drier instance
        //go to default url
        //aplication setup procedure<setup JSONOW>
    }
    @BeforeMethod
    public void testMethodSetup(){

    }

}
