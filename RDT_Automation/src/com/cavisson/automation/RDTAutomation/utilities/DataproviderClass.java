package com.cavisson.automation.RDTAutomation.utilities;

import org.testng.annotations.DataProvider;
public class DataproviderClass {
        @DataProvider(name="androidDeviceInfoProvider")
        public static Object[][] getDataFromDataprovider1(){
            return new Object[][] {
                { "samsung 8", "India" , "demo" },
                { "samsang 9", "UK" , "demo1" }
             
            };  
       }
        
        @DataProvider(name="iosDeviceInfoProvider")
        public static Object[][] getDataFromDataprovider2(){
            return new Object[][] {
                { "ios 11", "India" , "demo" },
                { "ios 10", "UK" , "demo1" }
             
            };  
       } 
}