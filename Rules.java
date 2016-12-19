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
public class Rules {
  
  private int discountQtytoQualify;
  private int discountQtyToBill;
  private double discountPrice;
  private int bundleQuantity;
  private String bundleItemName;
  
  
  public Rules() {
  }
  
  // define first rule 3 for the price of 2
  public Rules(int discountQtytoQualify, int discountQtyToBill, double price) {
    this.discountQtytoQualify = discountQtytoQualify;
    this.discountQtyToBill = discountQtyToBill;
    this.discountPrice = price;
    
  }
  
  public Rules(int bundleQuantity, String bundleItemName ) {
    this.bundleQuantity = bundleQuantity;
    this.bundleItemName = bundleItemName;
  }
  
  public int getDiscountQtytoQualify() {
    return this.discountQtytoQualify;
  }
  
  public int getDiscountQtyToBill() {
    return this.discountQtyToBill;
  }
  
  public double getDiscountPrice() {
    return this.discountPrice;
  }
  
  public String getBundleItemName() {
    return this.bundleItemName;
  }
  
  public int getBundleQuantity() {
    return this.bundleQuantity;
  }
  
}


