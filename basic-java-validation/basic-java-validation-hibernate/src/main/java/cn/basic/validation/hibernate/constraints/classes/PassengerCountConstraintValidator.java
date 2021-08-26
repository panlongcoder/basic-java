package cn.basic.validation.hibernate.constraints.classes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class PassengerCountConstraintValidator implements ConstraintValidator<ValidPassengerCount, Bus> {
    public void initialize(ValidPassengerCount constraint) {
    }

    public boolean isValid(Bus bus, ConstraintValidatorContext context) {
        if (bus == null) {
            return false;
        }

        return bus.getPassengers() != null && bus.getPassengers().size() == bus.getSeatCount();
    }
}
