package servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ngsystem.Config;

/**
 * @author bieyaqing
 */
@WebServlet("/InitialSystemServlet")
public class InitialSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@PostConstruct 
	public void initialize(){
		systemSetting();
	}
	
    public InitialSystemServlet() {
        super();
    }
    
    @Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
    
    @Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	systemSetting();
	}
    
    protected void systemSetting(){
    	Config.SETTIMEZONE();
    	System.out.println(Config.SDF.getTimeZone());
    }
    
}
