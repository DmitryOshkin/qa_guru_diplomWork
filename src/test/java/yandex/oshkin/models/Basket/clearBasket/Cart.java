package yandex.oshkin.models.Basket.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    private List<?> list;
    private Integer basketCount;
    private Integer basketCountProduct;
    private Integer basketCountServices;
    private List<?> removedList;
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
