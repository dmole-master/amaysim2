//*******************************************************************
// NOTE: 
//*******************************************************************

import java.lang.Math; // headers MUST be above the first class
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;


// one class needs to have a main() method
public class Item implements java.io.Serializable{
  private String itemId;
  private String itemName;
  private double itemPrice;
  private int itemQuantity;
  
  public Item() {
  }
  
  public Item(String itemId, String itemName, double itemPrice, int itemQuantity) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
  }
  
  public void setItem(String itemId, String itemName, double itemPrice, int itemQuantity) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
  }
  
  
  public String getItemId() {
    return this.itemId;
  }
  
  public int getItemQuantity() {
    return this.itemQuantity;
  }
  
  public double getItemPrice() {
    return this.itemPrice;
  }
  
  public String getItemName() {
    return this.itemName;
  }
  
  // this can also be used to calculate accumulation of orders
  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }
  
  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }
  
}


