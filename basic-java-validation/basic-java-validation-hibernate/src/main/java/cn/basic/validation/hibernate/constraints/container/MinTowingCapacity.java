package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinTowingCapacityConstraintValidator.class)
public @interface MinTowingCapacity {

    String message() default "Not enough towing capacity.";

    int value() default 100;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
