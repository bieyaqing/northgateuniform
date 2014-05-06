package model;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import ngsystem.Config;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

/**
 * @author bieyaqing
 * */

public class WelcomeDAO {
	public static ArrayList<Welcome> getAllWelcomes(){
		ArrayList<Welcome> welcomes = new ArrayList<Welcome>();
		DetachedCriteria dc = DetachedCriteria.forClass(Welcome.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for(Object o: list){
			welcomes.add((Welcome)o);
		}
		return welcomes;
	}
	
	public static Welcome getWelcomeById(long id){
		return (Welcome)HibernateUtil.get(Welcome.class, id);
	}
	
	public static void addWelcome(Welcome welcome){
		HibernateUtil.save(welcome);
	}
	
	public static void modifyWelcome(Welcome modifiedWelcome){
		HibernateUtil.update(modifiedWelcome);
	}
	
	public static void deleteWelcome(Welcome welcome){
		HibernateUtil.delete(welcome);
	}
	
	//featured method
	public static Welcome getWelcomeByTitle(String title){
		Welcome welcome = null;
		Welcome tempWelcome = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Welcome.class);
		if(title != null){
			detachedCriteria.add(Restrictions.eq(Config.TITLE, title));
		}
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempWelcome = (Welcome)o;
			if(tempWelcome.getTitle().equals(title)){
				welcome = tempWelcome;
				break;
			}
		}
		return welcome;
	}
}
