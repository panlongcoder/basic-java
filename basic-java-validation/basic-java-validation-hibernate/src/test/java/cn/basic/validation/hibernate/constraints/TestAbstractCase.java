package cn.basic.validation.hibernate.constraints;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Objects;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public abstract class TestAbstractCase {

    protected Validator validator;

    protected ExecutableValidator executableValidator;

    @Before
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        executableValidator = validator.forExecutables();
    }

    protected <T> void print(Set<ConstraintViolation<T>> constraintViolationSet) {
        constraintViolationSet.stream()
                .filter(Objects::nonNull)
                .forEach(constraintViolation -> {
                    System.out.println("getMessage() " + constraintViolation.getMessage());
                    System.out.println("getMessageTemplate() " + constraintViolation.getMessageTemplate());
                    System.out.println("getRootBean() " + constraintViolation.getRootBean());
                    System.out.println("getRootBeanClass() " + constraintViolation.getRootBeanClass());
                    System.out.println("getLeafBean() " + constraintViolation.getLeafBean());
                    System.out.println("getPropertyPath() " + constraintViolation.getPropertyPath());
                    System.out.println("getInvalidValue() " + constraintViolation.getInvalidValue());
                });
    }

}
