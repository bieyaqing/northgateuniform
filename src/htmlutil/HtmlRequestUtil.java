package htmlutil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * @author Bie Yaqing
 *
 */

public class HtmlRequestUtil {
	//General HTML request
	public static String executeRequest(String targetURL, String method, JSONObject proportyJson, String requestString){
		JSONParser parser = new JSONParser();
		
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(method);
			
			Set proportyKeys = proportyJson.keySet();
			for(Object o : proportyKeys){
				String key = (String)o;
				String value = (String)proportyJson.get(key);
				
				connection.setRequestProperty(key, value);
			}
			
			connection.setUseCaches(false);
			
			if(requestString==null||requestString.length()==0){
				// Do nothing
			}else{
				// Send request
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				//System.out.println(requestString);
				wr.writeBytes(requestString);
				wr.flush();
				wr.close();
			}
			
			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append("\r");
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
