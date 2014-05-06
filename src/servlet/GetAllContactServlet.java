package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ngsystem.Config;

import org.json.simple.JSONObject;

import controller.ContactCtl;
import controller.ProductCtl;

/**
 * @author bieyaqing
 */
public class GetAllContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();

		ArrayList<String> errMsgs = new ArrayList<String>();

		try {
			String inputJsonStr = request.getParameter(Config.JSON);
			JSONObject inputJson = (JSONObject) Config.JPARSER.parse(inputJsonStr);
			System.out.println(Config.SHOWINPUT + inputJson.toJSONString());

			if (errMsgs.size() == 0) {
				returnJson = ContactCtl.getAllContact(inputJson);
			} else {
				returnJson.put(Config.STATUS, Config.FAILCODE);
				returnJson.put(Config.MESSAGE, errMsgs);
			}
		} catch (Exception e) {
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}

		out.println(returnJson.toJSONString());
	}

}
