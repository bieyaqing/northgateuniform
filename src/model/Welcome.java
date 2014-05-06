package model;

import java.util.Date;

import ngsystem.Config;

import org.json.simple.JSONObject;

/**
 * @author bieyaqing
 * */
public class Welcome {
	private long id;
	private String title;
	private String text;
	private String tel;
	private String handphone;
	private String fax;
	private String email;
	private String company;
	private String address;
	private long visitAmt;
	private Date createDate;
	
	public Welcome(){}
	
	public Welcome(String title, String text, String tel, String handphone, String fax, String email, String company, String address, long visitAmt){
		this.title = title;
		this.text = text;
		this.tel = tel;
		this.handphone = handphone;
		this.fax = fax;
		this.email = email;
		this.company = company;
		this.address = address;
		this.visitAmt = visitAmt;
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
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getTel(){
		return this.tel;
	}
	
	public void setTel(String tel){
		this.tel = tel;
	}
	
	public String getHandphone(){
		return this.handphone;
	}
	
	public void setHandphone(String handphone){
		this.handphone = handphone;
	}
	
	public String getFax(){
		return this.fax;
	}
	
	public void setFax(String fax){
		this.fax = fax;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getCompany(){
		return this.company;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public long getVisitAmt(){
		return this.visitAmt;
	}
	
	public void setVisitAmt(long visitAmt){
		this.visitAmt = visitAmt;
	}
	
	public Date getCreateDate(){
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	public JSONObject toJson(){
		JSONObject welcomeJson = new JSONObject();
		
		welcomeJson.put(Config.ID, this.id);
		welcomeJson.put(Config.TITLE, this.title);
		welcomeJson.put(Config.TEXT, this.text);
		welcomeJson.put(Config.VISITAMT, this.visitAmt);
		welcomeJson.put(Config.TEL, this.tel);
		welcomeJson.put(Config.HANDPHONE, this.handphone);
		welcomeJson.put(Config.FAX, this.fax);
		welcomeJson.put(Config.EMAIL, this.email);
		welcomeJson.put(Config.COMPANY, this.company);
		welcomeJson.put(Config.ADDRESS, this.address);
		welcomeJson.put(Config.CREATEDATE, Config.SDFLOCAL.format(this.createDate));
		
		return welcomeJson;
	}
}
