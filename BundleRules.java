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
public class BundleRules {
  private static Map<String,Rules> rulesMap = new ConcurrentHashMap<String,Rules>();
  
  public BundleRules() {
    if (rulesMap == null) {
      rulesMap = new ConcurrentHashMap<String,Rules>();
    }
  }
  
  static public Map<String,Rules> getRulesMap() {
    return rulesMap;
  }
  
  static public void add(String itemId, Rules rule) {
    rulesMap.put(itemId, rule);
    
  }
  
  static public Rules getRule(String itemId) {
    if (rulesMap.containsKey(itemId)) {
      return (Rules)rulesMap.get(itemId);
    }
    return null;
  }
}

