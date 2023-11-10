package com.mahdi.sesootservice.core.validators.orders;

import com.mahdi.sesootservice.core.exception.orders.OrderException;
import com.mahdi.sesootservice.core.messages.Orders;

public class Validateorders {
    public static void validateOfferPrice(String offerPrice) throws OrderException {
        if (! offerPrice.matches("^[0-9_]*$")){
            throw new OrderException(Orders.wrongOrderOfferPrice);
        }

    }
}
