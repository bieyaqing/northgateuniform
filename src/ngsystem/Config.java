package ngsystem;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.json.simple.parser.JSONParser;

/**
 * @author bieyaqing
 * */

public class Config {
	public static final JSONParser JPARSER = new JSONParser();
	
	public static final SimpleDateFormat SDFLOCAL = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final TimeZone SGZONE = TimeZone.getTimeZone("Singapore");
	public static final void SETTIMEZONE(){
		SDF.setTimeZone(SGZONE);
	}
	
	public static final String JSON = "json";
	public static final String SHOWINPUT = "Input: ";
	
	public static final String STATUS = "status";
	public static final String MESSAGE = "message";
	public static final long SUCCESSCODE = 1;
	public static final long FAILCODE = 0;
	
	public static final String DISPLAYSHOW = "s";
	public static final String DISPLAYHIDE = "h";
	
	public static final String WELCOME = "welcome";
	public static final String PRODUCT = "product";
	public static final String PRODUCTIMAGE = "productImage";
	public static final String DISPLAYSTATUS = "displayStatus";
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String IMAGES = "images";
	public static final String MATERIAL = "material";
	public static final String SIZE = "size";
	public static final String IMAGE = "image";
	public static final String DESCRIPTION = "description";
	public static final String CATEGORY = "category";
	public static final String REMARK = "remark";
	public static final String PROPERTYJSONSTR = "propertyJsonStr";
	public static final String PROPERTYJSON = "propertyJson";
	public static final String CREATEDATE = "createDate";
	public static final String PRODUCTID = "productId";
	public static final String URL = "url";
	public static final String TITLE = "title";
	public static final String TEXT = "text";
	public static final String TEL = "tel";
	public static final String HANDPHONE = "handphone";
	public static final String FAX = "fax";
	public static final String EMAIL = "email";
	public static final String COMPANY = "company";
	public static final String ADDRESS = "address";
	public static final String VISITAMT = "visitAmt";
	public static final String ADMINAUTH = "adminAuth";
	
	public static final String PRODUCTS = "products";
	
	public static final String FILE = "file";
	public static final String FILEURL = "fileUrl";
	public static final String UPLOADDIRECTORY = "uploadDirectory";
	public static final String FOLDERNAME = "folderName";
	public static final String FILETYPE = "fileType";
	public static final String FILEDIR = "fileDir";
	
	public static final String GENDER = "gender";
	public static final String REQUIREMENT = "requirement";
	public static final String INDUSTRY = "industry";
	public static final String PHONE = "phone";
	
	public static final String CONTACT = "contact";
	public static final String CONTACTS = "contacts";
	public static final String CONTACTID = "contactId";
	public static final String PROFIT = "profit";
	
	
}
