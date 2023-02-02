package com.hanseatic.hanseaticneu.model;

import java.util.ArrayList;

public class Receipt {
    private String uuid;
    private String pos_key;
    private String table_pos_key;
    private int tip;
    private String printText;
    private ArrayList<OrderItem> orderItems;
    private String payment_type;

    private Receipt(){

    }

    public static class Builder{
        private String uuid;
        private String pos_key;
        private String table_pos_key;
        private int tip;
        private String printText;
        private ArrayList<OrderItem> orderItems;
        private String payment_type;

        public Builder(){
        }

        public Builder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public Builder pos_key(String pos_key){
            this.pos_key = pos_key;
            return this;
        }

        public Builder table_pos_key(String table_pos_key){
            this.table_pos_key = table_pos_key;
            return this;
        }

        public Builder orderItems(ArrayList<OrderItem> orderItems){
            this.orderItems = orderItems;
            return this;
        }

        public Builder tip(int tip){
            this.tip = tip;
            return this;
        }

        public Builder printText(String text){
            this.printText = text;
            return this;
        }

        public Builder paymentType(String payment_type){
            this.payment_type = payment_type;
            return this;
        }

        public Receipt build(){
            Receipt receipt = new Receipt();
            if(this.uuid != null) {
                receipt.uuid = this.uuid;
            }
            if(this.pos_key != null) {
                receipt.pos_key = this.pos_key;
            }
            receipt.table_pos_key = this.table_pos_key;
            receipt.orderItems = this.orderItems;
            receipt.tip = this.tip;
            receipt.printText = this.printText;
            receipt.payment_type = this.payment_type;
            return receipt;
        }
    }


    public String getUuid() {
        return uuid;
    }

    public String getPos_key() {
        return pos_key;
    }

    public String getTable_pos_key() {
        return table_pos_key;
    }

    public int getTip() {
        return tip;
    }

    public String getPrintText() {
        return printText;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getPaymentType() { return payment_type; }
}
