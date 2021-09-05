package cn.basic.validation.hibernate.constraints.groupcheck;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * 分组检查测试
 *
 * @author dragon
 * @date 2021/9/2
 */
public class GroupCheckTest extends TestAbstractCase {

    @Test
    public void groupCheckTest() {
        // create a car and check that everything is ok with it.
        Car car = new Car("Morris", "DD-AB-123", 2);
        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        assertEquals(0, constraintViolations.size());

        // but has it passed the vehicle inspection?
        constraintViolations = validator.validate(car, CarChecks.class);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "The car has to pass the vehicle inspection first",
                constraintViolations.iterator().next().getMessage()
        );

        // let's go to the vehicle inspection
        car.setPassedVehicleInspection(true);
        assertEquals(0, validator.validate(car, CarChecks.class).size());

        // now let's add a driver. He is 18, but has not passed the driving test yet
        Driver john = new Driver("John Doe");
        john.setAge(18);
        car.setDriver(john);
        constraintViolations = validator.validate(car, DriverChecks.class);
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "You first have to pass the driving test",
                constraintViolations.iterator().next().getMessage()
        );

        // ok, John passes the test
        john.passedDrivingTest(true);
        assertEquals(0, validator.validate(car, DriverChecks.class).size());

        // just checking that everything is in order now
        assertEquals(
                0, validator.validate(
                        car,
                        Default.class,
                        CarChecks.class,
                        DriverChecks.class
                ).size()
        );

    }
}
