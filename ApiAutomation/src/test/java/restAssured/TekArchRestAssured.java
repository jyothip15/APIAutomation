package restAssured;

//My program

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class TekArchRestAssured extends ReusableCodes {
	
	@BeforeMethod
	public static void baseURI() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		}
	
	@BeforeTest
	public static void startReport() {
		initiateReport();
	}

	@AfterTest
	public static void publishReport() {
        flushReport();
    }
	
	@Test(dataProvider="fromJson",dataProviderClass=Dataprovidersfile.class)
	public static void TC_001_LoginTest(HashMap<String,Object>loginvalue) throws IOException {
		CreateTest("TC_001 Logging in verification.");
		login(loginvalue,readFromPropertiesFile("login"),readFromPropertiesFile("contentType"));
		getResult("Getting Token");	
	}
	
	@Test(dependsOnMethods = "TC_001_LoginTest", priority = 1)
	public static void TC_002_getData() throws IOException {
        CreateTest("TC 002 Getting Data");
        getData(readFromPropertiesFile("getdata"), readFromPropertiesFile("contentType"), "Employee details");
        getResult("Getting data");
    }
	
   @Test(dependsOnMethods = "TC_001_LoginTest",dataProvider = "addJsonData",dataProviderClass = Dataprovidersfile.class,priority = 2)
   public static void TC_003_adddata(HashMap<String,Object>data ) throws IOException {
	   CreateTest("TC 003 Adding Data");
	   addData(data, readFromPropertiesFile("adddata"),readFromPropertiesFile("contentType"));
	   getResult("Adding data");
   }
	
   @Test(dependsOnMethods = {"TC_001_LoginTest","TC_002_getData"},dataProvider = "dataForUpdate",dataProviderClass = Dataprovidersfile.class,priority = 4)
   public static void  TC_005_updateData(JSONObject jsonObject) throws IOException {
	   CreateTest("TC 005 updating Data");
	   upDataData(jsonObject, readFromPropertiesFile("updatedata"),readFromPropertiesFile("contentType"));
	   getResult("updating data");
  }

  @Test(dependsOnMethods = {"TC_001_LoginTest", "TC_002_getData"},dataProvider = "dataForDelete",dataProviderClass = Dataprovidersfile.class,priority = 3)
  public static void TC_004_deleteData(JSONObject jsonObject) throws IOException {
	  CreateTest("TC 004 deleting Data");
	  deleteData(jsonObject,readFromPropertiesFile("delete"),readFromPropertiesFile("contentType"));
	  getResult("deleting data");
  }
   
 @Test(dependsOnMethods = {"TC_001_LoginTest", "TC_002_getData"},dataProvider =  "dataForLogout",dataProviderClass = Dataprovidersfile.class,priority = 5) 
 public static void TC_006_Logout(JSONObject jsonObject) throws IOException {
	 CreateTest("TC 006 logout");
	 logoutData(jsonObject,readFromPropertiesFile("logout"),readFromPropertiesFile("contentType"));
	 getResult("logout");
 }
}
