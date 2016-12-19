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
public class TestShoppingCart
{
  public static final String ULT_SMALL = "ult_small"; 
  public static final String ULT_SMALL_DESC = "Unlimited 1 GB";
  public static final double ULT_SMALL_PRICE = 24.90;
  
  public static final String ULT_MEDIUM = "ult_medium";
  public static final String ULT_MEDIUM_DESC = "Unlimited 2 GB";
  public static final double ULT_MEDIUM_PRICE = 29.90;
  
  public static final String ULT_LARGE = "ult_large";
  public static final String ULT_LARGE_DESC = "Unlimited 5 GB";
  public static final double ULT_LARGE_PRICE = 44.90;
  
  public static final String DATA_PACK = "1gb";
  public static final String DATA_PACK_DESC = "1 GB Data-pack";
  public static final double DATA_PACK_PRICE = 9.90;
  
  public static void cartOrder1() {
    System.out.println("Scenario 1");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item3 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    
    Item item5GB1 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    
    // scenario 1, apply 3 for 2 promo
    cart.add(item1);
    cart.add(item2);
    cart.add(item3);
    cart.add(item5GB1);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
  }
  
  public static void cartOrder2() {
    System.out.println("Scenario 2");
    ShoppingCart cart = new ShoppingCart();
    
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    
    Item item5GB1 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB2 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB3 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB4 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    
    // scenario 2, apply bulk discount rate
    cart.add(item1);
    cart.add(item2);
    cart.add(item5GB1);
    cart.add(item5GB2);
    cart.add(item5GB3);
    cart.add(item5GB4);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
  }
  
  public static void cartOrder3() {
    System.out.println("Scenario 3");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_MEDIUM, ULT_MEDIUM_DESC,ULT_MEDIUM_PRICE,1);
    Item item3 = new Item(ULT_MEDIUM, ULT_MEDIUM_DESC,ULT_MEDIUM_PRICE,1);
    
    // scenario 3, apply promo code
    cart.add(item1);
    cart.add(item2);
    cart.add(item3);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
    
  }
  
  public static void cartOrder4() {
    System.out.println("Scenario 4");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(DATA_PACK, DATA_PACK_DESC,DATA_PACK_PRICE,1);
    
    
    // scenario 4, apply promo code
    cart.add(item1);
    // apply PROMO CODE
    cart.add(item2,"I<3AMAYSIM");
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
    
  }
  
  
  
  public static void main(String[] args)
  {
    // Define the rules
    // 3 for 2 deal on unlimited 1 GB
    Rules rule1 = new Rules(3,2,24.90);
    PricingRules.add(ULT_SMALL, rule1);
    
    // bulk discount for 5 gig
    Rules rule2 = new Rules(4,4,39.90);
    PricingRules.add(ULT_LARGE, rule2);
    
    // add bundle rules for medium
    Rules rule3 = new Rules(1,DATA_PACK_DESC);
    BundleRules.add(ULT_MEDIUM, rule3);
    
    // scenario 1, apply 3 for 2 promo
    cartOrder1();
    
    // scenario 2, buy more than 3 get discount price
    cartOrder2();
    
    // scenario 3, get additional pack
    cartOrder3();
    
    // scenario 4, apply promo code
    cartOrder4();
    
  }
}


