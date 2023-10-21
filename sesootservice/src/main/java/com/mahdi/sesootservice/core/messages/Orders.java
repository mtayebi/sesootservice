package com.mahdi.sesootservice.core.messages;

public class Orders {
    public static final String wrongOrderOfferPrice;
    public static final String priceOrAddresIsNull;
    static {
        wrongOrderOfferPrice = "The offer order price should be true string digit value, and more than base value";
        priceOrAddresIsNull = "the offer price and address value cannot be null";
    }
}
