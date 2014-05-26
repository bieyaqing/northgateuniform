package controller;

import java.util.ArrayList;
import java.util.Set;

import model.Contact;
import model.ContactDAO;
import model.CustomerImage;
import model.CustomerImageDAO;
import ngsystem.Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */
public class ContactCtl {
	/**
	 * input:{}
	 * */
	public static JSONObject getAllContact(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			ArrayList<Contact> contacts = ContactDAO.getAllContacts();
			
			JSONArray contactJArr = new JSONArray();
			
			for(Contact c: contacts){
				contactJArr.add(c.toJson());
			}
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.CONTACTS, contactJArr);
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	public static JSONObject getContact(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			long id = (long) inputJson.get(Config.ID);
			
			Contact contact = ContactDAO.getContactById(id);
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.PRODUCT, contact.toJson());
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	/**
	 * input:{"name":"","title":"","company":"","industry":"","address":"","phone":"","email":"","requirement":"","images":""}
	 * */
	public static JSONObject createContact(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Config.NAME);
			String title = (String) inputJson.get(Config.TITLE);
			String company = (String) inputJson.get(Config.COMPANY);
			String industry = (String) inputJson.get(Config.INDUSTRY);
			String address = (String) inputJson.get(Config.ADDRESS);
			String phone = (String) inputJson.get(Config.PHONE);
			String email = (String) inputJson.get(Config.EMAIL);
			String requirement = (String) inputJson.get(Config.REQUIREMENT);
			String remark = "";
			
			JSONArray imageJArr = (JSONArray) inputJson.get(Config.IMAGES);
			
			//Contact(String name, String gender, String company, String industry, String address, String phone, String email, String requirement)
			Contact contact = new Contact(name, title, company, industry, address, phone, email, requirement, remark);
			ContactDAO.addContact(contact);
			
			for(Object o: imageJArr){
				String tempUrl = (String) o;
				contact = ContactDAO.getContactById(contact.getId());
				CustomerImage tempCustomerImage = new CustomerImage(contact, tempUrl);
				CustomerImageDAO.addCustomerImage(tempCustomerImage);
			}
			
			contact = ContactDAO.getContactById(contact.getId());
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.CONTACT, contact.toJson());
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	/**
	 * iinput:{"name":"","title":"","company":"","industry":"","address":"","phone":"","email":"","requirement":""}
	 * */
	public static JSONObject editContact(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Contact contact = ContactDAO.getContactById((long) inputJson.get(Config.ID));
			
			if(contact != null){
				if(inputJson.containsKey(Config.NAME)){
					contact.setName((String) inputJson.get(Config.NAME));
				}
				
				if(inputJson.containsKey(Config.STATUS)){
					contact.setStatus((String) inputJson.get(Config.STATUS));
				}
				
				if(inputJson.containsKey(Config.PROFIT)){
					contact.setProfit((double) inputJson.get(Config.PROFIT));
				}
				
				ContactDAO.modifyContact(contact);
				
				returnJson.put(Config.STATUS, Config.SUCCESSCODE);
				returnJson.put(Config.PRODUCT, contact.toJson());
			}else{
				returnJson.put(Config.STATUS, Config.FAILCODE);
				returnJson.put(Config.MESSAGE, "contact not found!");
			}
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
}
