package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ngsystem.Config;

/**
 * @author bieyaqing
 * */
public class Contact {
	private long id;
	private String name;
	private String title;
	private String company;
	private String industry;
	private String address;
	private String phone;
	private String email;
	private String requirement;
	private Set<CustomerImage> photos;
	private String status;
	private String remark;
	private Date createDate;
	
	public Contact(){
		
	}
	
	public Contact(String name, String title, String company, String industry, String address, String phone, String email, String requirement, String remark){
		this.name = name;
		this.title = title;
		this.company = company;
		this.industry = industry;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.requirement = requirement;
		this.status = "pending";
		this.remark = remark;
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
	
	public String getName(){
		return this.name;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getCompany(){
		return this.company;
	}
	
	public String getIndustry(){
		return this.industry;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getRequirement(){
		return this.requirement;
	}
	
	public Set<CustomerImage> getPhotos(){
		return this.photos;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public String getRemark(){
		return this.remark;
	}
	
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public void setIndustry(String industry){
		this.industry = industry;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setRequirement(String requirement){
		this.requirement = requirement;
	}
	
	public void setPhotos(Set<CustomerImage> photos){
		this.photos = photos;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	// methods
	public void addPhoto(CustomerImage photo){
		this.photos.add(photo);
	}
	
	public void removePhoto(CustomerImage photo){
		this.photos.remove(photo);
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Config.ID, this.id);
		returnJson.put(Config.NAME, this.name);
		returnJson.put(Config.TITLE, this.title);
		returnJson.put(Config.COMPANY, this.company);
		returnJson.put(Config.INDUSTRY, this.industry);
		returnJson.put(Config.ADDRESS, this.address);
		returnJson.put(Config.PHONE, this.phone);
		returnJson.put(Config.EMAIL, this.email);
		returnJson.put(Config.REQUIREMENT, this.requirement);
		
		JSONArray imageJArr = new JSONArray();
		for(CustomerImage ci : this.photos){
			JSONObject tempImageJObj = new JSONObject();
			tempImageJObj.put(Config.IMAGE, ci.getUrl());
			imageJArr.add(tempImageJObj);
		}
		
		returnJson.put(Config.STATUS, this.status);
		returnJson.put(Config.REMARK, this.remark);
		returnJson.put(Config.IMAGES, imageJArr);
		returnJson.put(Config.CREATEDATE, Config.SDFLOCAL.format(this.createDate));
		
		return returnJson;
	}
	
}
