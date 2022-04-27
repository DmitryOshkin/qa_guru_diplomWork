package yandex.oshkin.models.Basket.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Basket {

    private Storage storage;
    private List<?> drivebackItems;
    private Integer ret;
}
