package model;

import java.util.Date;

import ngsystem.Config;

import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */
public class CustomerImage {
	private long id;
	private Contact contact;
	private String url;
	private Date createDate;
	
	public CustomerImage(){};
	
	public CustomerImage(Contact contact, String url){
		this.contact = contact;
		this.url = url;
		try{
			this.createDate = Config.SDF.parse(Config.SDFLOCAL.format(new Date()));
		}catch(Exception e){
			this.createDate = new Date();
			e.printStackTrace();
		}
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Contact getContact(){
		return this.contact;
	}
	
	public void setContact(Contact contact){
		this.contact = contact;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public JSONObject toJson(){
		JSONObject productImageJson = new JSONObject();
		
		productImageJson.put(Config.ID, this.id);
		productImageJson.put(Config.CONTACTID, this.contact.getId());
		productImageJson.put(Config.URL, this.url);
		productImageJson.put(Config.CREATEDATE, Config.SDFLOCAL.format(this.createDate));
		
		return productImageJson;
	}
}
