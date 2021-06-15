package login;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class Reusable {
	static String sURL = null;
	public static String sToken;
	static String sHost = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	
	public static String readFile(String keyword) throws IOException {
		
		Properties pr = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/file.properties");
		pr.load(fis);
		return pr.getProperty(keyword);
		
	}

}
