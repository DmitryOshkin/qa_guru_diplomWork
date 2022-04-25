package yandex.oshkin.models.Basket.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Storage {

    private String compare;
    private String wishlist;
    private Cart cart;
    private ArrayList authorized;
}

