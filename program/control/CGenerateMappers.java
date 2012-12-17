package control;


import java.util.Map;
import entity.EFacade;
import acquaintance.*;
import mediator.*;

public class CGenerateMappers 
{
	MBroker broker;
	IAEntityMapper mapper;
	public CGenerateMappers(MBroker broker)
	{
		this.broker = broker;			
	}
	
	public void mapItemType()
	{		
		mapper= broker.getEntityMapper();
		mapper.setEntity(EFacade.getInstance().getItemTypeCanonical());
		mapper.setSchema("CSSDatabase");
		mapper.setTable("Itemtypes");
		mapper.setRelation("name", "ItemtypeName", "String", "", mapper.EM_NO_KEY);
		mapper.setRelation("barcode", "Barcode", "String", "", mapper.EM_NO_KEY);
		mapper.setRelation("price", "Price", "double", "", mapper.EM_NO_KEY);
		mapper.registerMap();
	}
	
	public void mapItem()
	{	
		mapper= broker.getEntityMapper();
		mapper.setEntity(EFacade.getInstance().getItemCanonical());
		mapper.setSchema("CSSDatabase");
		mapper.setTable("Items");
		mapper.setRelation("stockPosition", "StockPosition", "int", "", mapper.EM_NO_KEY);
		mapper.setRelation("typeOID", "Itemtype", "int", "", mapper.EM_NO_KEY);
		mapper.setRelation("orderOID", "OrderID", "int", "", mapper.EM_NO_KEY);
		mapper.registerMap();
	}
	
	
	public void mapOrder()
	{	
		mapper= broker.getEntityMapper();
		mapper.setEntity(EFacade.getInstance().getOrderCanonical());
		mapper.setSchema("CSSDatabase");
		mapper.setTable("Orders");
		mapper.setRelation("storeInfo", "Store", "String", "", mapper.EM_NO_KEY);
		mapper.setRelation("receivedDate", "ReceivalDate", "String", "", mapper.EM_NO_KEY);
		mapper.setRelation("shippingDate", "ShippingDate", "String", "", mapper.EM_NO_KEY);
		mapper.registerMap();
	}
	
	
	
	

}
