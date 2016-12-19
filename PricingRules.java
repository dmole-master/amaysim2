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
public class PricingRules {
  private static Map<String,Rules> rulesMap = new HashMap<String,Rules>();
  
  public PricingRules() {
    if (rulesMap == null) {
      rulesMap = new HashMap<String,Rules>();
    }
  }
  
  static public Map<String,Rules> getRulesMap() {
    return rulesMap;
  }
  
  static public void add(String itemId, Rules rule) {
    rulesMap.put(itemId, rule);
    
  }
  
  static public double apply(String itemId,int itemQuantity, double itemPrice, double total) {
    double totalAmount = 0.0;
    // any pricing rules or discount
    switch (itemId) {
      case "ult_small" : 
      totalAmount = applyPricingRules(itemId,itemQuantity, itemPrice);
      break;
      case "ult_large" :
      totalAmount = applyBulkDiscountRules(itemId,itemQuantity, itemPrice, total);
      break;
    }
    return totalAmount;
  }
  
  private static double applyPricingRules(String itemId, int itemQuantity, double itemPrice) {
    double deductPrice = 0.0;
    if (PricingRules.getRulesMap().containsKey(itemId)) {
      Rules rule = (Rules)PricingRules.getRulesMap().get(itemId);
      
      
      // let's compute
      int processQuantity = itemQuantity;
      while (processQuantity >= rule.getDiscountQtytoQualify()) {
        processQuantity -= rule.getDiscountQtytoQualify();
        deductPrice += (itemPrice * (rule.getDiscountQtytoQualify() - rule.getDiscountQtyToBill()) );
      }
      
    }
    return deductPrice;
  }
  
  private static double applyBulkDiscountRules(String itemId, int itemQuantity, double itemPrice, double total) {
    double returnValue = 0.0;
    if (PricingRules.getRulesMap().containsKey(itemId)) {
      Rules rule = (Rules)PricingRules.getRulesMap().get(itemId);
      
      if (itemQuantity >= rule.getDiscountQtytoQualify()) {
        returnValue = itemQuantity * rule.getDiscountPrice();
        returnValue = total - returnValue;
          
      }
      
    }
    return returnValue;
  }
  
  
}



