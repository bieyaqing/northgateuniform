package model;

import java.util.Date;

import ngsystem.Config;

import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */

public class ProductImage {
	private long id;
	private Product product;
	private String url;
	private Date createDate;
	
	public ProductImage(){};
	
	public ProductImage(Product product, String url){
		this.product = product;
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
	
	public Product getProduct(){
		return this.product;
	}
	
	public void setProduct(Product product){
		this.product = product;
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
		productImageJson.put(Config.PRODUCTID, this.product.getId());
		productImageJson.put(Config.URL, this.url);
		productImageJson.put(Config.CREATEDATE, Config.SDFLOCAL.format(this.createDate));
		
		return productImageJson;
	}
}
