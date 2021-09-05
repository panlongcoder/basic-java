package cn.basic.validation.hibernate.constraints.methodconstraint.inheritance;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * @author dragon
 * @date 2021/9/2
 */
public interface Vehicle {

    @Min(value = 2, message = "driver最小值为{value}")
    int drive(@Max(75) int speedInMph);
}
