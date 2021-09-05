package cn.basic.validation.hibernate.constraints.failfast;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author dragon
 * @date 2021/9/3
 */
public class FailFastTest {


    @Test
    public void failFastTest() {
        Validator validator = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();

        FailFastCar failFastCar = new FailFastCar(null, false);
        Set<ConstraintViolation<FailFastCar>> constraintViolationSet = validator.validate(failFastCar);

        Assert.assertEquals(1, constraintViolationSet.size());

    }
}
