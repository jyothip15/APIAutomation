package restAssured;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import io.restassured.path.json.JsonPath;

public class Dataprovidersfile extends ReusableCodes {
	static JsonPath path = new JsonPath(new File("C:\\Users\\jyoth\\eclipse-workspace\\ApiAutomation\\src\\test\\java\\properties\\data.json"));
    static JSONObject jsonObject;
    
    
    @DataProvider(name = "fromJson")
    public static Object[] readFromJson() {
   	    List<HashMap<String,Object>> userNamePassword = path.getList("login");
    	Object[] unamePwd = userNamePassword.toArray();
   		return unamePwd;
  	
    }
    
    @DataProvider(name = "addJsonData")  
    public static Object[] addData() {
       	List<HashMap<String,Object>> jsondata = path.getList("data");
    	Object[] dataFromJson = jsondata.toArray();
		return dataFromJson;
        }
    
    @DataProvider(name = "dataForDelete")
    public static Object[] dataForDelete() {
    	jsonObject = new JSONObject();
    	jsonObject.accumulate("id", id);
    	jsonObject.accumulate("userid",userid);
    	Object[] delData = new Object[][]{{jsonObject}};
		return delData;
        }
    
    @DataProvider(name = "dataForUpdate")
       
    public static Object[] dataForUpDate() {
    	jsonObject = new JSONObject();
    	jsonObject.accumulate("accountno", accountno);
        jsonObject.accumulate("departmentno", departmentno);
        jsonObject.accumulate("id", id_2);
        jsonObject.accumulate("pincode", pincode);
        jsonObject.accumulate("salary", salary);
        jsonObject.accumulate("userid", userid_2);
    	Object[] updateData = new Object[][] {{jsonObject}};
    	return updateData;
    	
    }
    
    @DataProvider (name = "dataForLogout")
    public static Object[] dataForLogout() {
    	jsonObject = new JSONObject();
    	jsonObject.accumulate("token", token);
    	jsonObject.accumulate("userid", userid);
    	Object[] logoutData = new Object[][] {{jsonObject}};
   		return logoutData;
    }

}
