package model;

import java.util.Date;
import java.util.Set;

import ngsystem.Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */

public class Product {
	private long id;
	private String name;
	private double price;
	private String material;
	private String size;
	private Set<ProductImage> images;
	private String category;
	private String description;
	private String remark;
	private String propertyJsonStr;
	private String displayStatus;
	private Date createDate;
	
	public Product(){
		
	}
	
	public Product(String name, double price, String material, String size, String category, String description, String remark, String propertyJsonStr){
		this.name = name;
		this.price = price;
		this.material = material;
		this.size = size;
		this.category = category;
		this.description = description;
		this.remark = remark;
		this.propertyJsonStr = propertyJsonStr;
		this.displayStatus = Config.DISPLAYSHOW;
		try{
			this.createDate = Config.SDF.parse(Config.SDFLOCAL.format(new Date()));
		}catch(Exception e){
			this.createDate = new Date();
			e.printStackTrace();
		}
	}
	
	public Long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public Set<ProductImage> getImages(){
		return this.images;
	}
	
	public void setImages(Set<ProductImage> images){
		this.images = images;
	}
	
	public String getMaterial(){
		return this.material;
	}
	
	public String getSize(){
		return this.size;
	}
	
	public void setMaterial(String material){
		this.material = material;
	}
	
	public void setSize(String size){
		this.size = size;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getRemark(){
		return this.remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getPropertyJsonStr(){
		return this.propertyJsonStr;
	}
	
	public void setPropertyJsonStr(String propertyJsonStr){
		this.propertyJsonStr = propertyJsonStr;
	}
	
	public String getDisplayStatus(){
		return this.displayStatus;
	}
	
	public void setDisplayStatus(String displayStatus){
		this.displayStatus = displayStatus;
	}
	
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public JSONObject toJson(){
		JSONObject productJson = new JSONObject();
		
		productJson.put(Config.ID, this.id);
		productJson.put(Config.NAME, this.name);
		productJson.put(Config.PRICE, this.price);
		productJson.put(Config.SIZE, this.size);
		productJson.put(Config.MATERIAL, this.material);
		
		JSONArray imageJArr = new JSONArray();
		for(ProductImage pi : this.images){
			JSONObject tempImageJObj = new JSONObject();
			tempImageJObj.put(Config.IMAGE, pi.getUrl());
			imageJArr.add(tempImageJObj);
		}
		
		productJson.put(Config.IMAGES, imageJArr);
		productJson.put(Config.CATEGORY, this.category);
		productJson.put(Config.DESCRIPTION, this.description);
		productJson.put(Config.REMARK, this.remark);
		
		JSONObject propertyJson = new JSONObject();
		
		try{
			propertyJson = (JSONObject) Config.JPARSER.parse(this.propertyJsonStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		productJson.put(Config.PROPERTYJSON, propertyJson);
		productJson.put(Config.DISPLAYSTATUS, this.displayStatus);
		productJson.put(Config.CREATEDATE, Config.SDFLOCAL.format(this.createDate));
		
		return productJson;
	}
}
