package cn.basic.validation.hibernate.constraints.classes;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class ClassLevelTest extends TestAbstractCase {

    @Test
    public void seatCountEqualsPassengers() {
        Bus bus = new Bus();
        bus.setSeatCount(3);
        bus.setPassengers(Arrays.asList(new Bus.Person("dragon", false), new Bus.Person("hero", false)));

        Set<ConstraintViolation<Bus>> constraintViolationSet = validator.validate(bus);

        Assert.assertEquals(1, constraintViolationSet.size());

        print(constraintViolationSet);
    }


}
