package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class StudentConstraintValidator implements ConstraintValidator<ValidStudent, Container.Student> {
    public void initialize(ValidStudent constraint) {
    }

    public boolean isValid(Container.Student student, ConstraintValidatorContext context) {
        return "一班".equals(student.getClassName());
    }
}
