package cn.basic.validation.hibernate.constraints.groupinheritance;

import jakarta.validation.constraints.AssertTrue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dragon
 * @date 2021/9/2
 */
@Getter
@Setter
public class SuperCar extends Car {

    @AssertTrue(
            message = "Race car must have a safety belt",
            groups = RaceCarChecks.class
    )
    // 安全带
    private boolean safetyBelt;

    public SuperCar(String manufacturer, String licencePlate, int seatCount) {
        super(manufacturer, licencePlate, seatCount);
    }

}
