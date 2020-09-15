package com.billing.spring.com.billing.dto;

import com.billing.spring.com.billing.entities.Products;
import com.billing.spring.com.billing.entities.Purchase_details;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateBillDTO {

    private int cus_id;
    private List<BillProducts> products;


    public CreateBillDTO() {
    }

    public CreateBillDTO(int cus_id, List<BillProducts> products) {
        this.cus_id = cus_id;
        this.products = products;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public List<BillProducts> getProducts() {
        return products;
    }

    public void setProducts(List<BillProducts> products) {
        this.products = products;
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
