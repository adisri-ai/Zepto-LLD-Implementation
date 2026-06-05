import java.util.*;
public class Item{
    String itemNo;
    String itemName;
    public Item(String itemNo , String itemName){
        this.itemNo = itemNo;
        this.itemName = itemName;
    }
}

// Factory-design pattern for defining new items
public class ItemFactory{
    public Item createItem(String itemNo , String itemName){
        return new Item(itemNo , itemName);
    }
}
// Strategy-design pattern : Definining different strategies for replenishment of darkstores.
public interface ReplenishStrategy{
    public boolean isReplenishNeeded(DarkStore ds);
    public void perfromReplenish(DarkStore ds);
}
public class WeeklyReplenish implements ReplenishStrategy{
    public boolean isReplenishNeeded(DarkStore ds){
        //logic code 
        return true;
    }
    public boolean perfromReplenish(DarkStore ds){
        //logic code 
        return;
    }
}
public class ThresholdReplenish implements ReplenishStrategy{
    public boolean isReplenishNeeded(DarkStore ds){
        //logic code 
        return true;
    }
    public boolean perfromReplenish(DarkStore ds){
        //logic code 
        return;
    }
}
public abstract class InventoryStore{
    HashMap<Item , Integer> storeItems;
    ReplenishStrategy ReplenishStrategy;
    public void addItem(Item item);
    public void removeItem(Item iterm);
}
public class InventoryManager{
    InventoryStore inventoryStore;
    public InventoryManager(InventoryStore inventoryStore){
        this.inventoryStore = inventoryStore;
    }
    public void addStock(HashMap<Item , Integer> stock){
        return;
    }
    public void removeStock(HashMap<Item , Integer> stock){
        return;
    }
}
public class Location{
    int x;
    int y;
    Location(int x , int y){
        this.x  = x;
        this.y = y;
    }
}
public class DarkStore{
    Location location;
    InventoryStore inventoryStore;
    InventoryManager inventoryManager;
    public InventoryStore(Location location , InventoryStore inventoryStore){
        this.location = location,
        this.inventoryStore = inventoryStore,
        this.inventoryManager = new InventoryManager(inventoryStore);
    }
}
// Factory desing pattern : For creating darkstores
public class DarkStoreFactory{
    public createDarkStore(Location locaton , InventoryStore inventoryStore){
        return new DarkStore(locatio , inventoryStore);
    }
}
//Singleton-design pattern : we need only 1 location service
public class LocationService{
    public static LocationService instance = null;
    private static LocationService(){
        return;
    }
    public static LocationService getInstance(){
        if(instance==null) instance = new LocationService();
        return instance;
    }
    public findNearestDarkStores(Location location){
        return;
    }
}
// Singleton-design pattern : We need only 1 Dark Store Manager
// Facade Design pattern : Single encapsulated class for all darkstore functionalities
public class DarkStoreManager{
    public static DesignManager instance = null;
    LocationService locationService;
    List<DarkStore> darkStores;
    private static DarkStoreManager(){
        darkStores = new ArrayList<>();
        return;
    }
    public static DarkStoreManager getInstance(){
        if(instance==null) instnace = new DarkStoreManager();
        return instance;
    }
    public void addDarkStore(DarkStore ds){
        darkstores.add(darkStores);
        return;
    }
    public void removeDarkStore(DarkStore ds){
        return;
    }
    public void allocateDarkStores(Location loc){
        return;
    }
}
public class Cart{
    List<Item> selectedItems;
    public Cart(){
        selectedItems = new ArrayList<>();
    }
    public void additem(Item item);
    public void removeItem(Item item);
}
public class CartFactory{
    public Cart createCart(){
        return new Cart();
    }
}
public class DeliveryPartner{
    int empId;
    int name;
}
// Singleton - class: Only on Delivery Manager is needed to allot delivery partner
public class DeliveryManager{
    private static DeliveryManager dev = null;
    HashMap<DeliveryPartner , String> deliveryPartners;
    LocationService locationService;
    private static DeliveryManager(){
        deliveryPartners = new HashMap<>();
        locationService  = LocationService.getInstance();
    }
    public static getInstance(){
        if(dev == null) dev = new DeliveryManager();
        return dev;
    }
    public void allocateDeliveryPartners(Location loc){
        return;
    }
}
public class PaymentManager{
    PaymentService paymentService;
    PaymentController paymentController = PaymentController.getInstance();
    public PaymentManager(PaymentService paymentService){
        this.paymentService = paymentService;
    }
    public performPayment(PaymentRequest req){
        paymentController.performPayment(req , paymentService);
    }
}
public class Order{
    int orderId;
    Cart cart;
    Location destinationLocation;
    DeliveryPartner deliveryPartner;
    PaymentService paymentService;
    PaymentRequest req;
}
// Facade design pattern - The encapsulation for all order functionalities
public class OrderManager{
    private static OrderManager instance = null;
    HashMap<Order , String> orderStatus;
    DarkStoreManager darkStoreManager;
    DeliveryManager deliveryManager;
    PaymentManager paymentManager;
    private static OrderManager(){
        return;
    }
    public static OrderManager getInstance(){
        if(instance==null){
            instance = new OrderManager();
        }
        return instance;
    }
    public void placeOrder(Order){
        darkStoreManager.allocateDarkStores(order.Cart);
        deliveryManager.allocateDeliveryPartners(order.destinationLocation);
        paymentManager.performPayment(order.req);
    }
}
// Facade - design pattern - the encapsulationm of all functionalities 
public class ZeptoApp{
    private static ZeptoApp instance = null;
    OrderManager orderManager;
    ItemFactory ItemFactory;
    private static ZeptoApp(){
        orderManager = OrderManager.getInstance();
        return;
    }
    public static ZeptoApp getInstance(){
        if(instance==null){
            instance = new ZeptoApp();
        }
        return instance;
    }
    public void openApp(){
        return;
    }
    public void closeApp(){
        return;
    }
    public void createCart(){
        return;
    }
    public void placeOrder(Order order){
        orderManager.placeOrder(order)
    }
    public void addItem(){
        return;
    };
    public void removeItem(){
        return;
    };
}
