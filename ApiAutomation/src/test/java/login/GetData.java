package login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;


public class GetData extends Reusable{
	@Test
	public void getData() throws IOException, Exception {
		String sURL = sHost + Reusable.readFile("getdata");
		RestAssured.baseURI = sURL;
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("token", BaseTest.sToken );
	
		Response res = RestAssured.given().contentType("application/json").headers(map).get();
	//	System.out.println(res.asPrettyString());
	
		
		Assert.assertEquals(res.statusCode(), 200);	
	//	System.out.println("passed");
	//	System.out.println(res.statusLine());
		
		Assert.assertEquals(res.getStatusLine(),"HTTP/1.1 200 OK");
		System.out.println(BaseTest.sToken +" getData" );
		System.out.println(BaseTest.userid);
		if(BaseTest.userid.equals("[kHacG2IP7Olqmq1CLFho]")) {
		BaseTest.id = res.jsonPath().getString("id[0]").toString();	
		System.out.println(BaseTest.id + "id[0]");
		}
	   System.out.println(res.statusCode()+ "getData");
	}
}