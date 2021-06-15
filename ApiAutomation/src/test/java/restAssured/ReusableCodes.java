package restAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReusableCodes 	 {
	
	static Response response;
	static String token,id,userid;
	static String id_2, userid_2, accountno, departmentno, pincode, salary;
	
	static ExtentSparkReporter sparkReporter;
    static ExtentTest logger;
    static ExtentReports extentReports;
    
    static {
        String addDate = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        extentReports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("C:\\Users\\jyoth\\eclipse-workspace\\ApiAutomation\\src\\test\\java\\Reports\\report" + addDate + ".html");
    }

    static void login(HashMap<String,Object>loginvalue,String endpoint,String contentType) {
    	response = RestAssured
    			.given()
    			.when().contentType(contentType).body(loginvalue).post(endpoint)
    			.then().extract().response();
    	HashMap<String,Object> maps = response.jsonPath().get("[0]");
    	token = maps.get("token").toString();
    	userid = maps.get("userid").toString();
   	 }
    
    static void getData(String endpoint,String contentType,String typeOfDataRequested) {
    	response = RestAssured
    			   .given().header("token",token).contentType(contentType)
    			   .when().get(endpoint)
    			   .then().extract().response();
    	logger.log(Status.INFO, typeOfDataRequested + " is requested.");
    	
     	
    	HashMap<String,Object> firstdata = response.jsonPath().get("[0]");
    	if(firstdata.get("userid").toString().equals(userid.toString())){
    		id = firstdata.get("id").toString();
    	}
    	else {
    		 System.out.println("user not authorized");
    	}
    	
    	HashMap<String,Object> seconddata = response.jsonPath().get("[1]");	
    	
    	id_2 = seconddata.get("id").toString();
    	userid_2 = seconddata.get("userid").toString();
    	accountno = seconddata.get("accountno").toString();
    	departmentno = seconddata.get("departmentno").toString();
    	pincode = seconddata.get("pincode").toString();
    	salary = seconddata.get("salary").toString();
    }
    
    static void addData(HashMap<String, Object> maps, String endPoint, String contentType) {
    	 response = RestAssured
    			 .given().header("token",token).contentType(contentType).body(maps)
    			 .when().post(endPoint)
    			 .then().extract().response();
       }
    
    static void deleteData(JSONObject jsonObject, String endPoint, String contentType) {
    	response = RestAssured
    			.given().header("token",token).contentType(contentType).body(jsonObject.toString())
    			.when().delete(endPoint)
    			.then().extract().response();
    	    }
    
    static void upDataData(JSONObject jsonObject, String endPoint, String contentType) throws IOException {

        response = RestAssured
                .given().header("token", token).contentType(contentType).body(jsonObject.toString())
                .when().put(endPoint)
                .then().extract().response();
    }
    
    static void logoutData(JSONObject jsonObject, String endPoint, String contentType) {
    	response = RestAssured
                .given().header("token", token).contentType(contentType).body(jsonObject.toString())
                .when().post(endPoint)
                .then().extract().response();
    }
    
    
    static boolean isSuccessful() {
    	int code = response.statusCode();
    	System.out.println(code );
    	String message = response.statusLine();
    	 if (code == 200 || code == 201 || code == 203 || code == 204 || code == 205) {
	            logger.log(Status.PASS, "The test is successful with return code " + code + " and message: " + message);
	            
	            return true;
	            
	        }
	        logger.log(Status.FAIL, "Status code is not matching as expected. It should have been the series on 2xx line but was: " + code);

	        return false;
    }
    static Response getResult(String performedAction) {

        Assert.assertTrue(isSuccessful(), "Test Case is success");
        logger.log(Status.PASS, performedAction + " is successfully executed.");
        logger.log(Status.INFO, getContent());
        logger.log(Status.INFO, getStatusDescription());
        return (Response) response.getBody();
    }

    static String getContent() {
        return response.getBody().asString();
    }

    static String getStatusDescription() {
        return response.getStatusLine();
    }

    static String readFromPropertiesFile(String keyword) throws IOException {
        Properties properties = new Properties();
        FileInputStream fls = new FileInputStream("C:\\Users\\jyoth\\eclipse-workspace\\ApiAutomation\\src\\test\\java\\properties\\file.properties");
        properties.load(fls);                      
        return properties.getProperty(keyword);
    }

    static void CreateTest(String testName) {
        logger = extentReports.createTest(testName);
    }

    static void flushReport() {
        extentReports.flush();
    }

    static void initiateReport() {
        extentReports.attachReporter(sparkReporter);
    }


}
