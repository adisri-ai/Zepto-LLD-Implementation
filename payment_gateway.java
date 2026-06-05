import java.util.*;
// class representing payment request  
public class PaymentRequest{
    String senderId;
    String receiverId;
    float amount;
}
// interface representing BakingService  
public interface BankingService{
    public boolean perfromTransaction(PaymentRequest req);
}
public class RazorpayBank implements BankingService{
    @Override
    public boolean perfromTransaction(PaymentRequest req){
        try{
            System.out.println("Transaction successfull by Razorpay");
            return true;
        }catch{
            System.out.println("Transaction unsucessfull by Razorpay");
            return false;
        }
    }
}
public class PaytmBank implements BankingService{
    @Override
    public boolean perfromTransaction(PaymentRequest req){
        try{
            System.out.println("Transaction successfull by Paytm");
            return true;
        }catch{
            System.out.println("Transaction unsucessfull by Paytm");
            return false;
        }
    }
}
// Template design pattern : The flow remains the same for performing a payment regardless of
// the order in which the functions are overriden
// interface for payment gateway
public abstract class PaymentGateway{
    BankingService bank;
    public boolean validate(PaymentRequest req);
    public boolean perfromTransaction(PaymentRequest req);
    public boolean giveStatus(PaymentRequest req);
    public boolean performPayment(PaymentRequest req){
        if(!this.validate(req)) return false;
        if(!this.perfromTransaction(req)) return false;
        if(!this.giveStatus(req)) return false;
        return true;
    }
}
public class RazorpayGateway extends PaymentGateway{
    RazorpayGateway(){
        this.bank = new PaytmBank();
    }
    @Override
    public boolean validate(PaymentRequest req){
        if(req.amount<=0) return false;
        return true;
    }
    @Override
    public boolean perfromTransaction(PaymentRequest req){
        try{
            print("Transaction performed using Razorpay");
            return true;
        }catch{
            return false;
        }
    }
    @Override 
    public boolean giveStatus(PaymentRequest req){
        return true;
    }
}
public class PaytmGateway extends PaymentGateway{
    PaytmGateway(){
        this.bank = new PaytmBank();
    }
    @Override
    public boolean validate(PaymentRequest req){
        if(req.amount<=0) return false;
        return true;
    }
    @Override
    public boolean perfromTransaction(PaymentRequest req){
        try{
            print("Transaction performed using Paytm");
            return true;
        }catch{
            return false;
        }
    }
    @Override 
    public boolean giveStatus(PaymentRequest req){
        return true;
    }
}
// Protected Proxy design pattern : We create proxy for payment gateway to handle retry and error handling mechanism
// payment Gateway proxy 
public class PaytmGatewayProxy implements PaymentGateway{
    PaytmGateway gateway;
    int maxRetries;
    PaytmGatewayProxy(PaytmGateway gate , int maxRetries){
        this.gateway = gate;
        this.maxRetries = maxRetries
    }
    @Override
    public boolean validate(PaymentRequest req){
        return this.gateway.validate(req);
    }
    @Override
    public boolean perfromTransaction(req){
        return this.gateway.perfromTransaction(req);
    }
    @Override
    public boolean giveStatus(req){
        return this.gatway.giveStatus(req);
    }
    @Override
    public boolean performPayment(req){
        for(int i = 0 ; i<this.maxRetries ; ++i){
            if(this.gateway.performPayment(req)) return true;
        }
        return false;
    }
}
public class RazorpayGatewayProxy implements PaymentGateway{
    Razorpay gateway;
    int maxRetries;
    PaytmGatewayProxy(Razorpay gate , int maxRetries){
        this.gateway = gate;
        this.maxRetries = maxRetries
    }
    @Override
    public boolean validate(PaymentRequest req){
        return this.gateway.validate(req);
    }
    @Override
    public boolean perfromTransaction(req){
        return this.gateway.perfromTransaction(req);
    }
    @Override
    public boolean giveStatus(req){
        return this.gatway.giveStatus(req);
    }
    @Override
    public boolean performPayment(req){
        for(int i = 0 ; i<this.maxRetries ; ++i){
            if(this.gateway.performPayment(req)) return true;
        }
        return false;
    }
}
// Factory Design Pattern 
// for creating payment gateways
public interface PaymentGatewayMaker{
    public PaymentGateway makeGateway();
}
public class RazorpayGatewayMaker implements PaymentGatewayMaker{
    @Override
    public PaymentGateway makeGateway(){
        return new RazorpayGatewayProxy(new RazorpayGateway() , 3);
    }
}
public class PaytmGatewayMaker implements PaymentGatewayMaker{
    @Override
    public PaymentGateway makeGateway(){
        return new PaytmGatewayProxy(new PaymentGateway() , 5);
    }
}
// Implementing Lazy loading for payment gateway
public class PaymentService{
    PaymentGatewayMaker gatewayMaker;
    PaymentGateway gateway;
    PaymentService(PaymentGatewayMaker gatewayMaker){
        this.gatewayMaker = gatewayMaker;
        this.gateway = null;
    }
    public boolean performPayment(PaymentRequest req){
        if(this.gateway == null) this.gateway = this.gatewayMaker.makeGateway();
        return this.gateway.performPayment(req);
    }
}
public abstract PaymentServiceFactory(){
    public static PaymentService createPaymentService();
}
public class RazorpayPaymentServiceFactory extends PaymentServiceFactory{
    @Override 
    public static PaymentService createPaymentService(){
        gatewayMaker = new RazorpayGatewayMaker();
        return new PaymentService(gatewayMaker);
    }
}
public class PaytmPaymentServiceFactory extends PaymentServiceFactory{
    @Override 
    public static PaymentService createPaymentService(){
        gatewayMaker = new PaytmGatewayMaker();
        return new PaymentService(gatewayMaker);
    }
}
// Singleton-Design Pattern : We make sure that the whole application has only one payment section 
// Facade-Design Pattern : User makes call only to PaymentController who performs the whole transaction process
public class PaymentController{
    private PaymentController controller = null;
    private final lock = new ReentrantLock();
    private PaymentController(){
        lock.lock();
        if(this.controller == null){
            this.controller = new PaymentController();
        }
        lock.unLock();
        return this.controller;
    }
    public PaymentController getInstance(){
        return new PaymentController();
    }
    public boolean performPayment(PaymentRequest req , PaymentService service){
        return service.performPayment(PaymentRequest);
    }
}
public class Main {

    public static void main(String[] args) {

        PaymentRequest request = new PaymentRequest();

        request.senderId = "USER_101";
        request.receiverId = "MERCHANT_501";
        request.amount = 2500.75f;
        PaymentService paymentService =  new PaytmPaymentServiceFactory.createPaymentService()
        PaymentController controller = PaymentController.getInstance();
        boolean status = controller.performPayment(request, paymentService);
        if(status){
            System.out.println("Payment Completed Successfully");
        }
        else{
            System.out.println("Payment Failed");
        }
    }
}
