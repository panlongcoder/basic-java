package cn.basic.validation.hibernate.constraints.failfast;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

/**
 * @author dragon
 * @date 2021/9/3
 */
public class FailFastCar {

    @NotNull
    private String manufacturer;

    @AssertTrue
    private boolean isRegistered;

    public FailFastCar(String manufacturer, boolean isRegistered) {
        this.manufacturer = manufacturer;
        this.isRegistered = isRegistered;
    }


}
