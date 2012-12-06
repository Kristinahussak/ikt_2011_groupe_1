package acquaintance;

/**
 * IAEntityMapper.java
 * @author 3. Semester Projekt, Gruppe 1
 * Anders Kold, Kristina Hussak,
 * Henning Fich & Nicholaj Rasmussen
 * Created on 03-12-2012
 */

public interface IAEntityMapper {
	
	public static int EM_PRIMARY_KEY = 1;
	public static int EM_SECONDARY_KEY = 2;
	public static int EM_AUTO_INCREMENT = 4;
	
	public static String EM_STRING = "String";
	public static String EM_INT = "int";
	public static String EM_DOUBLE = "Double";

	public boolean registerMap();
	
	
	
	public void setSchema(String schema);    
	// DataBase f.eks. "CSS"
	
	public void setTable(String tableName);  
	// Tilknyttet sql tabel f.eks. "items"
	
	public void SetEntity(String entity);    
	// Navn på Entity f.eks. "EItem"
	
	public void SetRelation(String field,String colName,String type,int keys);
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
