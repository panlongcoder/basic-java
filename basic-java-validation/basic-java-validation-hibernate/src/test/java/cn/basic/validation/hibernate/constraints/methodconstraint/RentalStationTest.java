package cn.basic.validation.hibernate.constraints.methodconstraint;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/27
 */
public class RentalStationTest extends TestAbstractCase {


    @Test
    public void constructorParameterTest() throws NoSuchMethodException {
        Constructor<RentalStation> constructor = RentalStation.class.getConstructor(String.class);

        Set<ConstraintViolation<RentalStation>> constraintViolationSet =
                executableValidator.validateConstructorParameters(constructor, new Object[]{"  "});

        Assert.assertEquals(1, constraintViolationSet.size());

        print(constraintViolationSet);

    }

    @Test
    public void constructorReturnValueTest() throws NoSuchMethodException {
        RentalStation rentalStation = new RentalStation("厦门火车站");
        Constructor<RentalStation> constructor = RentalStation.class.getConstructor(String.class);

        Set<ConstraintViolation<RentalStation>> constraintViolationSet = executableValidator.
                validateConstructorReturnValue(constructor, rentalStation);

        Assert.assertEquals(1, constraintViolationSet.size());

        print(constraintViolationSet);
    }

    @Test
    public void methodParameterTest() throws NoSuchMethodException {
        RentalStation rentalStation = new RentalStation("dragon");

        Method method = rentalStation.getClass().getMethod("rentCar", RentalStation.Customer.class, Date.class,
                int.class);

        RentalStation.Customer customer = new RentalStation.Customer();
        customer.setLevel(1);
        customer.setName("dragon");
        RentalStation wuhanStation = new RentalStation("武汉火车站");

        Set<ConstraintViolation<RentalStation>> constraintViolationSet = executableValidator.validateParameters(
                rentalStation, method, new Object[]{customer, new Date(), 0});

        print(constraintViolationSet);
    }

    @Test
    public void methodReturnValueTest() throws NoSuchMethodException {
        RentalStation rentalStation = new RentalStation("厦门火车站");
        Method method = rentalStation.getClass().getMethod("getName", String.class);

        Set<ConstraintViolation<RentalStation>> constraintViolationSet = executableValidator.validateReturnValue(
                rentalStation, method, "dragon");

        Assert.assertEquals(1, constraintViolationSet.size());

        print(constraintViolationSet);

    }

}
