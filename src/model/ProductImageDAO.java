package model;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * @author bieyaqing
 * */

public class ProductImageDAO {
	public static ArrayList<ProductImage> getAllProductImages(){
		ArrayList<ProductImage> productImages = new ArrayList<ProductImage>();
		DetachedCriteria dc = DetachedCriteria.forClass(ProductImage.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for(Object o: list){
			productImages.add((ProductImage)o);
		}
		return productImages;
	}
	
	public static ProductImage getProductImageById(long id){
		return (ProductImage)HibernateUtil.get(ProductImage.class, id);
	}
	
	public static void addProductImage(ProductImage productImage){
		HibernateUtil.save(productImage);
	}
	
	public static void modifyProductImage(ProductImage modifiedProductImage){
		HibernateUtil.update(modifiedProductImage);
	}
	
	public static void deleteProductImage(ProductImage productImage){
		HibernateUtil.delete(productImage);
	}
}
