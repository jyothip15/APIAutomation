package login;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class logout extends Reusable {

	@Test
	public  void logout() throws IOException {
		String sURI = sHost + Reusable.readFile("logout");
		RestAssured.baseURI = sURI;
		Map<String,String> map = new HashMap<String,String>();
		map.put("token", BaseTest.sToken );
		Response res = RestAssured.given().contentType(Reusable.readFile("contentType")).headers(map).post();
				
		System.out.println(res.statusCode()+" logout");
	}
}
