package com.hanseatic.hanseaticneu.model;

import java.util.ArrayList;

public class OrderItem {
    private String uuid;
    private String comment;
    private String product_pos_key;
    private String product_title;
    private Double product_tax;
    private int discount;
    private int product_price;
    private ArrayList<OrderItemModifierItem> modifierItems;

    private  OrderItem(){ }

    public String getUuid() {
        return uuid;
    }

    public String getComment() {
        return comment;
    }

    public String getProduct_pos_key(){
        return product_pos_key;
    }

    public Double getProduct_tax() {
        return product_tax;
    }

    public String getProduct_title() {return  product_title; }

    public int getDiscount() {
        return discount;
    }

    public ArrayList<OrderItemModifierItem> getModifierItems() {
        return modifierItems;
    }

    public int getProduct_price() {
        return product_price;
    }

    public static class Builder{
        private String uuid;
        private String comment;
        private String product_pos_key;
        private Double product_tax;
        private String product_title;
        private int discount;
        private int product_price;
        private ArrayList<OrderItemModifierItem> modifierItems;

        public Builder(){
        }

        public Builder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }
        public Builder comment(String comment){
            this.comment = comment;
            return this;
        }

        public Builder product_pos_key(String product_pos_key){
            this.product_pos_key = product_pos_key;
            return this;
        }
        public Builder product_title(String product_title){
            this.product_title = product_title;
            return this;
        }

        public Builder modifier_items(ArrayList<OrderItemModifierItem> orderItemModifierItems){
            this.modifierItems = orderItemModifierItems;
            return this;
        }

        public Builder tax(Double tax){
            this.product_tax = tax;
            return this;
        }

        public Builder discount(int discount){
            this.discount = discount;
            return this;
        }

        public Builder product_price(int price){
            this.product_price = price;
            return this;
        }

        public OrderItem build(){
            OrderItem orderItem = new OrderItem();
            orderItem.uuid = this.uuid;
            orderItem.comment = this.comment;
            orderItem.product_pos_key = this.product_pos_key;
            orderItem.product_tax = this.product_tax;
            orderItem.product_title = this.product_title;
            orderItem.modifierItems = this.modifierItems;
            orderItem.discount = this.discount;
            orderItem.product_price = this.product_price;
            return orderItem;
        }
    }
}
