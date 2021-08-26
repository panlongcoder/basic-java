package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Age Constraint validator
 *
 * @author dragon
 * @date 2021/8/26
 */
public class AgeConstraintValidator implements ConstraintValidator<ValidAge, Integer> {

    @Override
    public void initialize(ValidAge constraintAnnotation) {
        System.out.println("validAge annotation :" + constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        System.out.println("constraintContext :" + context);
        if (value < 1) {
            return false;
        }

        if (value > 120) {
            return false;
        }

        return true;
    }
}
