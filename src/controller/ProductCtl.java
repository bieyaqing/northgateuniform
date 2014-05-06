package controller;

import java.util.ArrayList;
import java.util.Set;

import model.Product;
import model.ProductDAO;
import model.ProductImage;
import model.ProductImageDAO;
import ngsystem.Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */
public class ProductCtl {
	/**
	 * input:{}
	 * */
	public static JSONObject getAllProduct(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			ArrayList<Product> products = ProductDAO.getAllProducts();
			
			JSONArray productJArr = new JSONArray();
			
			for(Product p: products){
				if(p.getDisplayStatus().equals(Config.DISPLAYSHOW)){
					productJArr.add(p.toJson());
				}
			}
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.PRODUCTS, productJArr);
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	public static JSONObject getProduct(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			long id = (long) inputJson.get(Config.ID);
			
			Product product = ProductDAO.getProductById(id);
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.PRODUCT, product.toJson());
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	/**
	 * input:{"name":"","image":"","price":"","size":"","material":"","category":"","description":"","propertyJsonStr":""}
	 * */
	public static JSONObject createProduct(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			String name = (String) inputJson.get(Config.NAME);
			String image = (String) inputJson.get(Config.IMAGE);
			double price = 0;
			
			try{
				price = (double) inputJson.get(Config.PRICE);
			}catch(Exception e){
				e.printStackTrace();
				try{
					price = (long) inputJson.get(Config.PRICE);
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			
			String size = (String) inputJson.get(Config.SIZE);
			String material = (String) inputJson.get(Config.MATERIAL);
			String category = (String) inputJson.get(Config.CATEGORY);
			String description = (String) inputJson.get(Config.DESCRIPTION);
			String remark = "";
			
			JSONObject propertyJson = new JSONObject();
			String propertyJsonStr = propertyJson.toJSONString();
			
			Product product = new Product(name, price, material, size, category, description, remark, propertyJsonStr);
			ProductDAO.addProduct(product);
			ProductImage productImage = new ProductImage(product, image);
			ProductImageDAO.addProductImage(productImage);
			
			Product returnProduct = ProductDAO.getProductById(product.getId());
			
			returnJson.put(Config.STATUS, Config.SUCCESSCODE);
			returnJson.put(Config.PRODUCT, returnProduct.toJson());
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
	
	/**
	 * input:{"id":"", "name":"","price":"","size":"","material":"","category":"","description":"","propertyJsonStr":"","displayStatus":""}
	 * */
	public static JSONObject editProduct(JSONObject inputJson){
		JSONObject returnJson = new JSONObject();
		
		try{
			Product product = ProductDAO.getProductById((long) inputJson.get(Config.ID));
			
			if(product != null){
				if(inputJson.containsKey(Config.NAME)){
					product.setName((String) inputJson.get(Config.NAME));
				}
				
				if(inputJson.containsKey(Config.PRICE)){
					double price = 0;
					try{
						price = (long) inputJson.get(Config.PRICE);
					}catch(Exception e){
						e.printStackTrace();
						try{
							price = (double) inputJson.get(Config.PRICE);
						}catch(Exception e2){
							e2.printStackTrace();
						}
					}
					product.setPrice(price);
				}
				
				if(inputJson.containsKey(Config.SIZE)){
					product.setSize((String) inputJson.get(Config.SIZE));
				}
				
				if(inputJson.containsKey(Config.MATERIAL)){
					product.setMaterial((String) inputJson.get(Config.MATERIAL));
				}
				
				if(inputJson.containsKey(Config.CATEGORY)){
					product.setCategory((String) inputJson.get(Config.CATEGORY));
				}
				
				if(inputJson.containsKey(Config.DESCRIPTION)){
					product.setDescription((String) inputJson.get(Config.DESCRIPTION));
				}
				
				if(inputJson.containsKey(Config.PROPERTYJSONSTR)){
					product.setPropertyJsonStr((String) inputJson.get(Config.PROPERTYJSONSTR));
				}
				
				if(inputJson.containsKey(Config.DISPLAYSTATUS)){
					product.setDisplayStatus((String) inputJson.get(Config.DISPLAYSTATUS));
				}
				
				if(inputJson.containsKey(Config.IMAGE)){
					ProductImage image = null;
					Set<ProductImage> images = product.getImages();
					
					for(ProductImage pi: images){
						image = pi;
					}
					
					image.setUrl((String) inputJson.get(Config.IMAGE));
					ProductImageDAO.modifyProductImage(image);
				}
				
				ProductDAO.modifyProduct(product);
				
				returnJson.put(Config.STATUS, Config.SUCCESSCODE);
				returnJson.put(Config.PRODUCT, product.toJson());
			}else{
				returnJson.put(Config.STATUS, Config.FAILCODE);
				returnJson.put(Config.MESSAGE, "product not found!");
			}
		}catch(Exception e){
			returnJson.put(Config.STATUS, Config.FAILCODE);
			returnJson.put(Config.MESSAGE, e);
			e.printStackTrace();
		}
		
		return returnJson;
	}
}
