package cn.basic.validation.hibernate.constraints.field;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dragon
 * @date 2021/8/25
 */
@Data
@NoArgsConstructor
public class Car {

    @NotNull(message = "制造商不能为空")
    private String manufacturer;

    @AssertTrue(message = "车辆必须为已注册")
    private boolean isRegistered;

    public Car(String manufacturer, boolean isRegistered) {
        this.manufacturer = manufacturer;
        this.isRegistered = isRegistered;
    }

}
