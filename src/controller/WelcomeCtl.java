package controller;

import java.util.Date;

import model.Welcome;
import model.WelcomeDAO;
import ngsystem.Config;

import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */

public class WelcomeCtl {
	public static JSONObject createWelcome(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			String title = (String) inputJson.get(Config.TITLE);
			String text = (String) inputJson.get(Config.TEXT);
			String tel = (String) inputJson.get(Config.TEL);
			String handphone = (String) inputJson.get(Config.HANDPHONE);
			String fax = (String) inputJson.get(Config.FAX);
			String email = (String) inputJson.get(Config.EMAIL);
			String company = (String) inputJson.get(Config.COMPANY);
			String address = (String) inputJson.get(Config.ADDRESS);
			long visitAmt = 0;
			
			Welcome welcome = new Welcome(title, text, tel, handphone, fax, email, company, address, visitAmt);
			
			WelcomeDAO.addWelcome(welcome);
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.WELCOME, welcome.toJson());
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	public static JSONObject editWelcome(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			long id = (Long) inputJson.get(Config.ID);
			Welcome welcome = WelcomeDAO.getWelcomeById(id);
			if(welcome == null){
				try{
					welcome = WelcomeDAO.getAllWelcomes().get(0);
				}catch(Exception e){
					e.printStackTrace();
					welcome = null;
				}
			}
			if(welcome != null){
				if(inputJson.containsKey(Config.TITLE)){
					welcome.setTitle((String) inputJson.get(Config.TITLE));
				}
				
				if(inputJson.containsKey(Config.TEXT)){
					welcome.setText((String) inputJson.get(Config.TEXT));
				}
				
				if(inputJson.containsKey(Config.TEL)){
					welcome.setTel((String) inputJson.get(Config.TEL));
				}
				
				if(inputJson.containsKey(Config.HANDPHONE)){
					welcome.setHandphone((String) inputJson.get(Config.HANDPHONE));
				}
				
				if(inputJson.containsKey(Config.FAX)){
					welcome.setFax((String) inputJson.get(Config.FAX));
				}
				
				if(inputJson.containsKey(Config.EMAIL)){
					welcome.setEmail((String) inputJson.get(Config.EMAIL));
				}
				
				if(inputJson.containsKey(Config.COMPANY)){
					welcome.setCompany((String) inputJson.get(Config.COMPANY));
				}
				
				if(inputJson.containsKey(Config.ADDRESS)){
					welcome.setAddress((String) inputJson.get(Config.ADDRESS));
				}
				
				if(inputJson.containsKey(Config.VISITAMT)){
					welcome.setVisitAmt((long) inputJson.get(Config.VISITAMT));
				}
				
				WelcomeDAO.modifyWelcome(welcome);
				
				returnJson.put(Config.STATUS, Config.SUCCESSCODE);
				returnJson.put(Config.WELCOME, welcome.toJson());
			}else{
				returnJson.put(Config.STATUS, Config.FAILCODE);
				returnJson.put(Config.MESSAGE, "welcome object cannot found");
				createWelcome(inputJson);
			}
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	public static JSONObject getWelcome(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		try{
			long id = (long) inputJson.get(Config.ID);
			
			Welcome welcome = WelcomeDAO.getWelcomeById(id);
			
			if(welcome == null){
				try{
					welcome = WelcomeDAO.getAllWelcomes().get(0);
				}catch(Exception e){
					e.printStackTrace();
					welcome = null;
				}
			}
			
			if(welcome != null){
				returnJson.put(Config.STATUS, Config.SUCCESSCODE);
				returnJson.put(Config.WELCOME, welcome.toJson());
			}else{
				returnJson.put(Config.STATUS, Config.FAILCODE);
				returnJson.put(Config.WELCOME, "welcome object cannot found");
			}
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
}
