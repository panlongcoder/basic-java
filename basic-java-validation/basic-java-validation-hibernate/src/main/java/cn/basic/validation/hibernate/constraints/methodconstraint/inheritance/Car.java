package cn.basic.validation.hibernate.constraints.methodconstraint.inheritance;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * @author dragon
 * @date 2021/9/2
 */
public class Car implements Vehicle {


    @Override
    @Min(value = 20, message = "car driver 最小值为 {value}")
    @Max(value = 80, message = "driver最大值为{value}")
    public int drive(@Max(55) int speedInMph) {

        return -1;
    }
}
