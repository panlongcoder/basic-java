package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class MinTowingCapacityConstraintValidator implements ConstraintValidator<MinTowingCapacity, Integer> {
    public void initialize(MinTowingCapacity constraint) {
    }

    public boolean isValid(Integer capacity, ConstraintValidatorContext context) {
        return capacity >= 1000;
    }
}
