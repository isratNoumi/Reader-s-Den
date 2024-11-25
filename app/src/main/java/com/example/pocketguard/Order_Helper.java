package com.example.pocketguard;

public class Order_Helper {
    String OrderId;
    String TotalBill;
    String deliveryaddress;
    String orderdate;

    public Order_Helper() {
    }

    public Order_Helper(String orderId, String totalBill, String deliveryaddress, String orderdate) {
        OrderId = orderId;
        TotalBill = totalBill;
        this.deliveryaddress = deliveryaddress;
        this.orderdate = orderdate;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getTotalBill() {
        return TotalBill;
    }

    public void setTotalBill(String totalBill) {
        TotalBill = totalBill;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }
}
