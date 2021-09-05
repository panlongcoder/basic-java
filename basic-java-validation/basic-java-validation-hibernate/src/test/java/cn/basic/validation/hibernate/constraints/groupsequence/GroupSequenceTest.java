package cn.basic.validation.hibernate.constraints.groupsequence;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import cn.basic.validation.hibernate.constraints.groupcheck.Car;
import cn.basic.validation.hibernate.constraints.groupcheck.Driver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/9/2
 */
public class GroupSequenceTest extends TestAbstractCase {

    @Test
    public void test() {

        Car car = new Car("Morris", "DD-AB-123", 2);
        car.setPassedVehicleInspection(true);

        Driver john = new Driver("John Doe");
        john.setAge(18);
        john.passedDrivingTest(true);
        car.setDriver(john);

        assertEquals(0, validator.validate(car, OrderedChecks.class).size());


    }
}
