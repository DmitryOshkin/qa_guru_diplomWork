package yandex.oshkin.models.Basket.clearBasket;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Basket {

    private Storage storage;
    private ArrayList drivebackItems;
    private Integer ret;
}
