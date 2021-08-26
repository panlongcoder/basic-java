package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Documented
@Target({TYPE_USE, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentConstraintValidator.class)
public @interface ValidStudent {


    String message() default "学生必须是一班的";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
