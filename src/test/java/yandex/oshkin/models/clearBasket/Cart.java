package yandex.oshkin.models.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {

    private String areaId;

    private String name;

    private String city;

    private String countryId;

    private String countryCode;

    private String country;
}
