package cn.basic.validation.hibernate.constraints.field;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class FiledTest extends TestAbstractCase {

    @Test
    public void manufacturerIsNull() {
        Car car = new Car();
        car.setRegistered(true);

        Set<ConstraintViolation<Car>> constraintViolationSet = validator.validate(car);
        print(constraintViolationSet);

        Assert.assertEquals(1, constraintViolationSet.size());

        Assert.assertEquals("制造商不能为空", constraintViolationSet.iterator().next().getMessage());
    }

    @Test
    public void carIsNotRegisteredAndManufacturerIsNull() {
        Car car = new Car();

        Set<ConstraintViolation<Car>> constraintViolationSet = validator.validate(car);

        print(constraintViolationSet);

        Assert.assertEquals(2, constraintViolationSet.size());

    }


}
