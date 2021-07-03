package model;

import java.util.List;

public class OrderByGroupInfo {
    private String orderCode;
    private List<OrderInformation> orderInformation;
    private String address;


    public OrderByGroupInfo(String orderCode, List<OrderInformation> orderInformation) {
        this.orderCode = orderCode;
        this.orderInformation = orderInformation;
    }


    public OrderByGroupInfo(String orderCode, List < OrderInformation > orderInformation, String address) {
            this.orderCode = orderCode;
            this.orderInformation = orderInformation;
            this.address = address;
        }

        public String getOrderCode () {
            return orderCode;
        }

        public void setOrderCode (String orderCode){
            this.orderCode = orderCode;
        }

        public List<OrderInformation> getOrderInformation () {
            return orderInformation;
        }

        public void setOrderInformation (List < OrderInformation > orderInformation) {
            this.orderInformation = orderInformation;
        }

        public String getAddress () {
            return address;
        }

        public void setAddress (String address){
            this.address = address;
        }

        @Override
        public String toString () {
            return "OrderByGroupInfo{" +
                    "orderCode='" + orderCode + '\'' +
                    ", orderInformation=" + orderInformation +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
