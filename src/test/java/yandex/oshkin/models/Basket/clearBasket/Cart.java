package yandex.oshkin.models.Basket.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    private ArrayList list;
    private Integer basketCount;
    private Integer basketCountProduct;
    private Integer basketCountServices;
    private ArrayList removedList;
    private Integer orderAmountWithoutDiscount;
    private Integer orderAmount;
    private Integer accruedBonuses;
    private Object amountDiscount;
    private Object bonus;
    private String coupon;
    private Object promoCode;
    private Integer selfDeliveryDate;
    private String shipmentTime;
    private String shipmentDate;
    private Integer deliveryType;
    private Object deliveryCost;
    private Boolean hasMarkedProducts;
}
