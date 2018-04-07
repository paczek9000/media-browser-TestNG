package main.utils;

import org.testng.annotations.DataProvider;

public class Provider{
        @DataProvider(name = "test1")
        public Object[][] createData1() {
            return new Object[][] {
                    { "Cedric", new Integer(36) },
                    { "Anne", new Integer(37)},
            };
        }
}
