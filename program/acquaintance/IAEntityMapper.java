package acquaintance;

/**
 * IAEntityMapper.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface IAEntityMapper {
	
	public static int    EM_NO_KEY = 0;
	public static int    EM_PRIMARY_KEY = 1;
	public static int    EM_FOREIGN_KEY = 1;
	public static String EM_SECONDARY_KEY = "foreign key ";
	public static String EM_AUTO_INCREMENT = "auto_increment ";
	public static String EM_NOT_NULL = "not null ";
	
	public static String EM_STRING = "String ";
	public static String EM_INT = "int ";
	public static String EM_DOUBLE = "Double ";

	public boolean registerMap();
	
	
	
	public void setSchema(String schema);    
	// DataBase f.eks. "CSS"
	
	public void setTable(String tableName);  
	// Tilknyttet sql tabel f.eks. "items"
	
	public void setEntity(String entity);    
	// Navn på Entity f.eks. "EItem"
	
	public void setRelation(String field,String colName,String type,String attributes,int keys);
	// Sætter en relation mellem et felt i en class og en kolonne i en sql tabel 
 	// f.eks. EItem : "id" , "barcode" og "int" og EM_PRIMARY_KEY
	
	/* MBroker vil via FDBBroker levere en instans af en mapper der så kan sættes f.eks
	 * 
	 * 
	 * IAEntityMapper eItemMapper =  MBroker.getEntityMapper();
	 * 
	 *   eItemMapper.setSchema("CSS");
	 * 			    .setTable("items");
	 * 				.setRelation("id","barcode","int",IAEntityMapper.EM_PRIMARY_KEY);
	 * 
	 * 
	 * 
	 */
	

	
}
