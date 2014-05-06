package model;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class CustomerImageDAO {
	public static ArrayList<CustomerImage> getAllCustomerImages(){
		ArrayList<CustomerImage> customerImages = new ArrayList<CustomerImage>();
		DetachedCriteria dc = DetachedCriteria.forClass(CustomerImage.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for(Object o: list){
			customerImages.add((CustomerImage)o);
		}
		return customerImages;
	}
	
	public static CustomerImage getCustomerImageById(long id){
		return (CustomerImage)HibernateUtil.get(CustomerImage.class, id);
	}
	
	public static void addCustomerImage(CustomerImage customerImage){
		HibernateUtil.save(customerImage);
	}
	
	public static void modifyCustomerImage(CustomerImage modifiedCustomerImage){
		HibernateUtil.update(modifiedCustomerImage);
	}
	
	public static void deleteCustomerImage(CustomerImage customerImage){
		HibernateUtil.delete(customerImage);
	}
	
	//featured method
}
