package cn.basic.validation.hibernate.constraints.classes;

import cn.basic.validation.hibernate.constraints.container.MinTowingCapacityConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PassengerCountConstraintValidator.class)
public @interface ValidPassengerCount {

    String message() default "人数与座位数不一致";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
