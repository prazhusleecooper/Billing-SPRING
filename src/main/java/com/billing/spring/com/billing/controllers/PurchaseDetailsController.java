package com.billing.spring.com.billing.controllers;

import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.billing.spring.com.billing.dto.CreateBillDTO;
import com.billing.spring.com.billing.entities.Customers;
import com.billing.spring.com.billing.entities.Purchase_details;
import com.billing.spring.com.billing.repositories.CustomersRepository;
import com.billing.spring.com.billing.repositories.Purchase_DetailsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
public class PurchaseDetailsController {

    @Autowired
    Purchase_DetailsRepository purchaseDetailsRepository;
    
    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    CreateBillDTO createBillDTO;

    Logger logger = LoggerFactory.getLogger(getClass());

    // POST Method to create a new Bill
    @CrossOrigin
    @RequestMapping(value = "/createBill", method = RequestMethod.POST)
    public ResponseEntity<?> createBill(@RequestBody CreateBillDTO bill) {
//        JSONObject jsonObject = new JSONObject(bill.getProducts());
//        String newBillProducts = jsonObject.toJSONArray(bill.getProducts());
//        System.out.println("\n\n\n\n\nThE RESULTANT JSON:::::::::::::::::" + bill.getProducts().toString());
        Purchase_details newBill = new Purchase_details();
        newBill.setCus_id(bill.getCus_id());
        newBill.setProducts(bill.getProducts().toString());
        newBill.setSno(ThreadLocalRandom.current().nextInt());
        String result = "";
        try {
            purchaseDetailsRepository.save(newBill);
            result = "{ \"status\": 200, \"statusCode\": 1}";
        } catch (Exception e) {
            result = "{ \"status\": 500, \"statusCode\": 2}";
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        JSONObject jsonArray = new JSONObject("{\n  \"cus_id\": 55,\n  \"products\": [\n      {\n        \"product_ID\": 332,\n        \"product_name\": \"product 1\",\n        \"product_price\": 15.0,\n        \"quantity\": 2,\n        \"tax\": 10.0,\n        \"discount_amount\": 10.0,\n        \"total_price\": 30.0\n      },\n      {\n        \"product_ID\": 333,\n        \"product_name\": \"product 2\",\n        \"product_price\": 15.0,\n        \"quantity\": 2,\n        \"tax\": 10.0,\n        \"discount_amount\": 10.0,\n        \"total_price\": 30.0\n      }\n    ]\n}");
        
//        GET THE PRODUCTS JSON -> bill.getProducts()
//        System.out.println("\n\n\n\n\n\n\nTHE ALL:::" + bill.getProducts());
      
//        for(Purchase_details product: bill.getProducts()) {
//        	
//        }
        
        
        // Bill products of the instance
        JSONArray billProducts = new JSONArray(newBill.getProducts().toString());
        
        // TO find the customer email        
        List<Customers> cusList = customersRepository.findAll();
        JSONArray cusListJ = new JSONArray(cusList);
        String customerEmail = "";
        for(int i=0; i < cusListJ.length(); i++) {
        	JSONObject entry = cusListJ.getJSONObject(i);
        	if(entry.getInt("cus_ID") == bill.getCus_id()) {
        		customerEmail = entry.getString("cus_email");
        	}
        }
        
        String messageTextEmail = "<center><h1>Thank you for your purchase at Gokul Toy Shop!</center> <br>" +
        	"<center><h2>Here is your Bill:</h2><center><br>" +
        	"<table style=\"table-layout: auto; width: 100%; color: black  \">" + 
        	"<tr style=\"padding: 12px 0; text-align: center; background-color: #4CAF50;\">" +
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center; \">Sno</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\" >Product Name</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Price (Rs.)</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Quantity</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">GST (Rs.)</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Discount (Rs.)</th>"+
				"<th style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Total (Rs.)</th>"+
			"</tr>";
        
        Float grandTotal = 0f;
        
        for(int i=0, sno = 1; i < billProducts.length(); i++, sno++) {
        	JSONObject entry = billProducts.getJSONObject(i);
        	grandTotal += entry.getFloat("total_price");
        	messageTextEmail += "<tr style=\"; padding: 12px 0; text-align: center;background-color: #f2f2f2;\">" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">" + sno + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">" + entry.getString("productName") + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Rs. " + entry.getFloat("product_price") + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">" + entry.getInt("quantity") + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Rs. " + entry.getFloat("tax") + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Rs. " + entry.getFloat("discount_amount") + "</td>" +
        			"<td style=\"border: 1px solid black; padding: 12px 0; text-align: center;\">Rs. " + entry.getFloat("total_price") + "</td></tr>";
        }
        
        messageTextEmail += "</table> <br>" + "<h2>Grand Total: Rs."+ grandTotal +"</h2>" ;
        
     // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = ""; // FILL YOUR EMAIL HERE
        String password = ""; // FILL YOUR PASSWORD HERE
 
        // outgoing message information
        String mailTo = customerEmail;
        String subject = "Hello my friend";
 
        // message contains HTML markups
//        String message = "<i>Greetings!</i><br>";
//        message += "<b>Wish you a nice day!</b><br>";
//        message += "<font color=red>Duke</font>" + "<h1>YEET</h1>";
        
        String message = messageTextEmail;
 
        PurchaseDetailsController mailer = new PurchaseDetailsController();
 
        try {
            mailer.sendHtmlEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
        
        
        
        
        
        /* STATUS CODES
         *  1 - Successful Insert
         *  2 - Error while inserting
         *  */
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    public void sendHtmlEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.user", userName);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
//        Session session = Session.getInstance(properties, auth);
 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        
        
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    
    
}


class BillProducts {
    private int product_ID;
    private String productName;
    private float product_price;
    private int quantity;
    private float tax;
    private float discount_amount;
    private float total_price;

    public BillProducts() {

    }

    public BillProducts(int product_ID, String productName, float product_price, int quantity, float tax, float discount_amount, float total_price) {
        this.product_ID = product_ID;
        this.productName = productName;
        this.product_price = product_price;
        this.quantity = quantity;
        this.tax = tax;
        this.discount_amount = discount_amount;
        this.total_price = total_price;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(float discount_amount) {
        this.discount_amount = discount_amount;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "{" +
                "product_ID:" + product_ID +
                ", productName:'" + productName + '\'' +
                ", product_price:" + product_price +
                ", quantity:" + quantity +
                ", tax:" + tax +
                ", discount_amount:" + discount_amount +
                ", total_price:" + total_price +
                '}';
    }
}
