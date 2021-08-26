package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Documented
@Target({METHOD, ANNOTATION_TYPE, CONSTRUCTOR, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeConstraintValidator.class})
public @interface ValidAge {

    String message() default "年龄必须在{min}-{max}之间";

    int min() default 1;

    int max() default 120;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
