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
public class ShoppingCart {
  public static final String PROMO_CODE = "I<3AMAYSIM"; // just declare it constant for simplicity
  public static final double PROMO_PERCENT = .10; // 10% DISCOUNT 
  private Map<String,Item> map = new ConcurrentHashMap<String,Item>();
  private boolean applyPromo = false;
  
  public ShoppingCart() {
    if (map == null) {
      map = new ConcurrentHashMap<String,Item>();
    }
  }
  
  public boolean add(Item newItem) {
    if (map != null) {
      System.out.println("Added 1 " + newItem.getItemName());
      if (map.containsKey(newItem.getItemId())) {
        
        // update item order
        Item item = (Item)map.get(newItem.getItemId());
        item.setItemQuantity(item.getItemQuantity() + newItem.getItemQuantity());
        item.setItemPrice( newItem.getItemPrice());
        return true;
      }
      // add new item
      map.put(newItem.getItemId(),newItem);
      return false;
    }
    return false; // error map is not initialized
  }
  
  public boolean addFree(Item newItem) {
    if (map != null) {
      //System.out.println("Added 1 " + newItem.getItemName());
      if (map.containsKey(newItem.getItemId())) {
        
        // update item order
        Item item = (Item)map.get(newItem.getItemId());
        item.setItemQuantity(item.getItemQuantity() + newItem.getItemQuantity());
        item.setItemPrice( newItem.getItemPrice());
        return true;
      }
      // add new item
      map.put(newItem.getItemId(),newItem);
      return false;
    }
    return false; // error map is not initialized
  }
  
  public void add(Item item, String promoCode) {
    add(item);
    if (PROMO_CODE.equals(promoCode)) {
      System.out.println("\'"+ promoCode + "\'" + "Promo Applied");
      setApplyPromo(true);
    }
  }
  
  public void setApplyPromo(boolean value) {
    this.applyPromo = value;
  }
  
  public boolean isPromoToBeApplied() {
    return this.applyPromo;
  }
  
  public double total() {
    double totalAmount = 0.0;
    // iterate through all the items and calculate price
    Iterator iterator = map.values().iterator();
    double itemTotal = 0.0;
    while (iterator.hasNext()) {
      Item itm = ((Item)iterator.next());
      int itmQty = itm.getItemQuantity();
      while (itmQty>=1) {
        itemTotal += itm.getItemPrice();
        itmQty--;
      }
      
      totalAmount += itemTotal;
      
      totalAmount -= PricingRules.apply(itm.getItemId(),itm.getItemQuantity(), itm.getItemPrice(), itemTotal);
      
      itemTotal = 0.0;
    }
    // this is to be applied on top of everything
    if (isPromoToBeApplied()) {
      totalAmount = totalAmount - (totalAmount * PROMO_PERCENT);
    }
    
    BigDecimal a = new BigDecimal(totalAmount);
	BigDecimal b = a.setScale(2, RoundingMode.CEILING);
    return b.doubleValue();
    
  }
  
  
  public void items() {
    Iterator iterator = map.values().iterator();
    System.out.println("Cart Items");
    while (iterator.hasNext()) {
      Item itm = ((Item)iterator.next());
      int itemCount = itm.getItemQuantity();
      System.out.println(itemCount + " X " + itm.getItemName());
      
      while (itemCount > 0) {
        Rules rule = BundleRules.getRule(itm.getItemId());
        if (rule != null) {
          // add the free bundle now
          Item bundleItem = new Item("1gb", rule.getBundleItemName(),0.0,rule.getBundleQuantity());
          addFree(bundleItem);
          //System.out.println(itemCount + " X " + rule.getBundleItemName());
        }
        else {
          break; // no bundle rules found
        }
        itemCount--;
      }
      
      
    }
  }
  
  public void empty() {
    map.clear();
  }
  
  
}

