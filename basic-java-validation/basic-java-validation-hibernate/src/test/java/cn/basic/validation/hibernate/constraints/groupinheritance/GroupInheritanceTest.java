package cn.basic.validation.hibernate.constraints.groupinheritance;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author dragon
 * @date 2021/9/2
 */
public class GroupInheritanceTest extends TestAbstractCase {

    @Test
    public void test() {
        // create a supercar and check that it's valid as a generic Car
        SuperCar superCar = new SuperCar("Morris", "DD-AB-123", 1);
        assertEquals("最小不能小于2", validator.validate(superCar).iterator().next().getMessage());

        // check that this supercar is valid as generic car and also as race car
        Set<ConstraintViolation<SuperCar>> constraintViolations = validator.validate(superCar, RaceCarChecks.class);

        print(constraintViolations);
    }


}
