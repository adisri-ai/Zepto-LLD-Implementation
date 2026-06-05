# Functional Requirements  
1. Should support multiple gateways  
2. Should be easy to add multiple providers(such as Razorpay, Paytm , etc.)  
3. There should be a standard payment flow with required validations  
4. Have error handling and retry mechanism.  
# UML Diagram  
```text
+----------------------+
|    PaymentRequest    |
+----------------------+
| senderId             |
| receiverId           |
| amount               |
+----------------------+

+----------------------+
|   BankingService     |
+----------------------+
| + performTransaction |
+----------------------+
        ^          ^
        |          |
+----------------+  +----------------+
| RazorpayBank   |  | PaytmBank      |
+----------------+  +----------------+

+----------------------+
|   PaymentGateway     |
+----------------------+
| bank                 |
+----------------------+
| + validate()         |
| + performTransaction |
| + giveStatus()       |
| + performPayment()   |
+----------------------+
        ^                      ^
        |                      |
+-------------------+   +-------------------+
| RazorpayGateway   |   | PaytmGateway      |
+-------------------+   +-------------------+

+--------------------------+
| RazorpayGatewayProxy     |
+--------------------------+
| gateway                  |
| maxRetries               |
+--------------------------+
| + performPayment()       |
+--------------------------+

+--------------------------+
| PaytmGatewayProxy        |
+--------------------------+
| gateway                  |
| maxRetries               |
+--------------------------+
| + performPayment()       |
+--------------------------+

+--------------------------+
|  PaymentGatewayMaker     |
+--------------------------+
| + makeGateway()          |
+--------------------------+
        ^                          ^
        |                          |
+----------------------+   +----------------------+
| RazorpayGatewayMaker |   | PaytmGatewayMaker    |
+----------------------+   +----------------------+

+----------------------+
|    PaymentService    |
+----------------------+
| gatewayMaker         |
| gateway              |
+----------------------+
| + performPayment()   |
+----------------------+

+----------------------+
|   PaymentController  |
+----------------------+
| controller           |
| lock                 |
+----------------------+
| + getInstance()      |
| + performPayment()   |
+----------------------+
```
