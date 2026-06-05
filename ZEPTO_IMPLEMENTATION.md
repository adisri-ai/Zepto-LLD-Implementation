# Functional Requirements  
1. We should be able to manage inventory  
2. We should have replenish strategies (monthly/weekly) and should be scalable  
3. We can have multiple inventory store and can be extended.  
4. User should be able to see all items in dark store near to him/her(5km)  
5. If one Darkstore cannot fulfill order, we split the order to be fulfilled by multiple darkstores by multiple delivery agents.   
# UML Diagram  
```text
+----------------------+
|        Item          |
+----------------------+
| itemNo               |
| itemName             |
+----------------------+

+----------------------+
|     ItemFactory      |
+----------------------+
| + createItem()       |
+----------------------+

+---------------------------+
|    ReplenishStrategy      |
+---------------------------+
| + isReplenishNeeded()     |
| + performReplenish()      |
+---------------------------+
          ^                           ^
          |                           |
+----------------------+   +----------------------+
| WeeklyReplenish      |   | ThresholdReplenish  |
+----------------------+   +----------------------+

+----------------------+
|    InventoryStore    |
+----------------------+
| storeItems           |
| replenishStrategy    |
+----------------------+
| + addItem()          |
| + removeItem()       |
+----------------------+

+----------------------+
|   InventoryManager   |
+----------------------+
| inventoryStore       |
+----------------------+
| + addStock()         |
| + removeStock()      |
+----------------------+

+----------------------+
|      Location        |
+----------------------+
| x                    |
| y                    |
+----------------------+

+----------------------+
|      DarkStore       |
+----------------------+
| location             |
| inventoryStore       |
| inventoryManager     |
+----------------------+

+----------------------+
|   DarkStoreFactory   |
+----------------------+
| + createDarkStore()  |
+----------------------+

+----------------------+
|   LocationService    |
+----------------------+
| instance             |
+----------------------+
| + getInstance()      |
| + findNearest()      |
+----------------------+

+----------------------+
|   DarkStoreManager   |
+----------------------+
| darkStores           |
| locationService      |
+----------------------+
| + addDarkStore()     |
| + removeDarkStore()  |
| + allocateDarkStore()|
+----------------------+

+----------------------+
|        Cart          |
+----------------------+
| selectedItems        |
+----------------------+
| + addItem()          |
| + removeItem()       |
+----------------------+

+----------------------+
|     CartFactory      |
+----------------------+
| + createCart()       |
+----------------------+

+----------------------+
|   DeliveryPartner    |
+----------------------+
| empId                |
| name                 |
+----------------------+

+----------------------+
|   DeliveryManager    |
+----------------------+
| deliveryPartners     |
| locationService      |
+----------------------+
| + allocatePartner()  |
+----------------------+

+----------------------+
|   PaymentManager     |
+----------------------+
| paymentService       |
| paymentController    |
+----------------------+
| + performPayment()   |
+----------------------+

+----------------------+
|        Order         |
+----------------------+
| orderId              |
| cart                 |
| destinationLocation  |
| deliveryPartner      |
| paymentService       |
| request              |
+----------------------+

+----------------------+
|    OrderManager      |
+----------------------+
| orderStatus          |
| darkStoreManager     |
| deliveryManager      |
| paymentManager       |
+----------------------+
| + placeOrder()       |
+----------------------+

+----------------------+
|      ZeptoApp        |
+----------------------+
| orderManager         |
| itemFactory          |
+----------------------+
| + openApp()          |
| + closeApp()         |
| + createCart()       |
| + placeOrder()       |
+----------------------+
```
