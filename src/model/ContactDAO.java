package model;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import ngsystem.Config;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ContactDAO {
	public static ArrayList<Contact> getAllContacts(){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		DetachedCriteria dc = DetachedCriteria.forClass(Contact.class);
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(dc);
		for(Object o: list){
			contacts.add((Contact)o);
		}
		return contacts;
	}
	
	public static Contact getContactById(long id){
		return (Contact)HibernateUtil.get(Contact.class, id);
	}
	
	public static void addContact(Contact contact){
		HibernateUtil.save(contact);
	}
	
	public static void modifyContact(Contact modifiedContact){
		HibernateUtil.update(modifiedContact);
	}
	
	public static void deleteContact(Contact contact){
		HibernateUtil.delete(contact);
	}
	
	//featured method
	public static Contact getContactByName(String name){
		Contact contact = null;
		Contact tempContact = null;
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Contact.class);
		if(name != null){
			detachedCriteria.add(Restrictions.eq(Config.NAME, name));
		}
		List<Object> list = HibernateUtil.detachedCriteriaReturnList(detachedCriteria);
		
		for(Object o : list){
			tempContact = (Contact)o;
			if(tempContact.getName().equals(name)){
				contact = tempContact;
				break;
			}
		}
		return contact;
	}
}
