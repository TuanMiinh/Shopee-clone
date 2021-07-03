package model;

public class OrderByGroup {
    private String orderCode;
    private String orderID;
    private String address;

    public OrderByGroup() {

    }


    public OrderByGroup(String orderCode, String orderID) {
        this.orderCode = orderCode;
        this.orderID = orderID;

    }

    public OrderByGroup(String orderCode, String orderID, String address) {
        this.orderCode = orderCode;
        this.orderID = orderID;
        this.address = address;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "OrderByGroup{" +
                "orderCode='" + orderCode + '\'' +
                ", orderID='" + orderID + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
