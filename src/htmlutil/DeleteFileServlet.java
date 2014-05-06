package htmlutil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ngsystem.Config;

/**
 * @author yaqing
 * */
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public DeleteFileServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();
		JSONParser parser = new JSONParser();
		String contextRoot = getServletContext().getRealPath("/");
		
		ArrayList<String> errMsgs = new ArrayList<String>();
		
		try{
			JSONObject inputJson = (JSONObject)parser.parse(request.getParameter(Config.JSON));
			System.out.println("input: " + inputJson.toJSONString());
			
			if(inputJson.containsKey(Config.FILEURL)){
			}else{
				errMsgs.add("No fileUrl Found!");
			}
			
			if(errMsgs.size()==0){
				String fileUrl = (String)inputJson.get(Config.FILEURL);
				
				String fileDir = contextRoot + fileUrl.substring(fileUrl.split("/")[0].length());
				
				File file = new File(fileDir);
				
				if(file.delete()){
					returnJson.put(Config.STATUS, 1);
					returnJson.put(Config.MESSAGE, "deleted!");
				}else{
					returnJson.put(Config.STATUS, 0);
					returnJson.put(Config.MESSAGE, "got problem!");
				}
			}else{
				returnJson.put(Config.STATUS, 0);
				returnJson.put(Config.MESSAGE, "DeleteFileServlet: " + errMsgs.toString());
			}
		}catch(Exception e){
			returnJson.put(Config.STATUS, 0);
			returnJson.put(Config.MESSAGE, "DeleteFileServlet: " + e);
		}
		
		out.println(returnJson.toJSONString());
	}

}
