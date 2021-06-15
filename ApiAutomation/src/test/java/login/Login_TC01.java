package login;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Login_TC01 extends Reusable{

	
	@Test
	public  void login() throws IOException, Exception {
		String sURI = sHost + Reusable.readFile("login");
		RestAssured.baseURI = sURI;

		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\jyoth\\eclipse-workspace\\ApiAutomation\\src\\test\\java\\Testdata.json");
		//Read JSON file
		Object obj = jsonParser.parse(reader);
		Response res = RestAssured.given().contentType(Reusable.readFile("contentType")).body(obj).post();
		Assert.assertEquals(res.statusCode(), 201);	


		Assert.assertEquals(res.getStatusLine(),"HTTP/1.1 201 Created");
		BaseTest.sToken = res.jsonPath().get("token[0]").toString();	
		BaseTest.userid = res.jsonPath().getString("userid").toString();
		System.out.println(BaseTest.userid + "userid");
		
	 System.out.println(BaseTest.sToken +" login" );
		 
	}
	
	
}
