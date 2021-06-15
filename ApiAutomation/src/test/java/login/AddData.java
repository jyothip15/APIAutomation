package login;
//old
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddData extends Reusable{
	
	@Test
	public static void addData() throws Exception {
	sURL = sHost + Reusable.readFile("adddata");
	RestAssured.baseURI = sURL;
//	Login_TC01.login();
	
	Map<String,String> map = new HashMap<String,String>();
	map.put("token", BaseTest.sToken );
	JsonObject userCreds = new JsonObject();
	userCreds.addProperty("accountno", "TA-1119999");
	userCreds.addProperty("departmentno","1" );
	userCreds.addProperty("salary","1" );
	userCreds.addProperty("pincode","111111" );
	Response res = RestAssured.given().contentType("application/json").headers(map).body(userCreds).post();
	
	System.out.println(res.statusCode() + "addData");
	System.out.println(BaseTest.sToken +" adddata" );
	
}
}