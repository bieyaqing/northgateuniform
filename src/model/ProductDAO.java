package model;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import ngsystem.Config;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ProductDAO {
	public static ArrayList<Product> getAllProducts(){
		ArrayList<Product> products = new ArrayList<Product>();
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for(Object o: list){
			products.add((Product)o);
		}
		return products;
	}
	
	public static Product getProductById(long id){
		return (Product)HibernateUtil.get(Product.class, id);
	}
	
	public static void addProduct(Product product){
		HibernateUtil.save(product);
	}
	
	public static void modifyProduct(Product modifiedProduct){
		HibernateUtil.update(modifiedProduct);
	}
	
	public static void deleteProduct(Product product){
		HibernateUtil.delete(product);
	}
	
	//featured method
	public static Product getProductByName(String name){
		Product product = null;
		Product tempProduct = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		if(name != null){
			detachedCriteria.add(Restrictions.eq(Config.NAME, name));
		}
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempProduct = (Product)o;
			if(tempProduct.getName().equals(name)){
				product = tempProduct;
				break;
			}
		}
		return product;
	}
}
