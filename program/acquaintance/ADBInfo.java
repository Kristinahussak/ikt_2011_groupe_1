package acquaintance;

/**
 * ADBInfo.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public class ADBInfo{
	
	protected String driver ="com.mysql.jdbc.Driver"; // default
	protected String url = "jdbc:mysql://localhost:3306/"; // default
	protected String user = "root";      // HF default
	protected String password = "root";  // HF default
	
	public ADBInfo(){};
	
	public ADBInfo(String driver,String url,String user,String password){
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	};
	
	public String getDriver(){return driver;}
	public String getUrl(){return url;}
	public String getUser(){return user;}
	public String getPassword(){return password;}
	
	public void setDriver(String driver){this.driver = driver;}
	public void setUrl(String url){this.url = url;}
	public void setUser(String user){this.user = user;}
	public void setPassword(String password){this.password = password;}
	

}
