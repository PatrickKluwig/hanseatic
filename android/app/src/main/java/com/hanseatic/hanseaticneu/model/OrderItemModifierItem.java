package com.hanseatic.hanseaticneu.model;

public class OrderItemModifierItem {
    private String title;
    private String modifier_item_pos_key;
    private int price_modifier;

    private OrderItemModifierItem(){}

    public String getTitle() {
        return title;
    }

    public String getModifier_item_pos_key() {
        return modifier_item_pos_key;
    }

    public int getPrice_modifier() {
        return price_modifier;
    }

    public static class Builder {
        private String title;
        private String modifier_item_pos_key;
        private int price_modifier;

        public Builder() {
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder modifier_item_pos_key(String modifier_item_pos_key) {
            this.modifier_item_pos_key = modifier_item_pos_key;
            return this;
        }

        public Builder price_modifier(int price_modifier) {
            this.price_modifier = price_modifier;
            return this;
        }

        public OrderItemModifierItem build() {
            OrderItemModifierItem orderItemModifierItem = new OrderItemModifierItem();
            orderItemModifierItem.title = this.title;
            orderItemModifierItem.modifier_item_pos_key = this.modifier_item_pos_key;
            orderItemModifierItem.price_modifier = this.price_modifier;
            return orderItemModifierItem;
        }
    }
}
