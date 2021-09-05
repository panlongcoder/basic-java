package cn.basic.validation.hibernate.constraints.container;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class ContainerTest extends TestAbstractCase {


    @Test
    public void listContainerTest() {
        Container container = new Container();
        container.addName("dragon");
        container.addName("hero");

        Set<ConstraintViolation<Container>> constraintViolationSet = validator.validate(container);

        print(constraintViolationSet);

        Assert.assertEquals(1, constraintViolationSet.size());
        Assert.assertEquals("姓名不能小于5个字", constraintViolationSet.iterator().next().getMessage());

    }

    @Test
    public void setContainerTest() {
        Container container = new Container();
        container.addAge(10);
        container.addAge(-10);
        container.addAge(150);

        Set<ConstraintViolation<Container>> constraintViolations = validator.validate(container);

        print(constraintViolations);

        Assert.assertEquals(2, constraintViolations.size());
    }

    @Test
    public void mapContainer() {
        Container container = new Container();

        container.addStudent("一班", Container.Student.builder().name("dragon").age(10).className("一班").build());

        container.addStudent("一班", Container.Student.builder().name("hero").age(50).className("一班").build());

        container.addStudent("二班", Container.Student.builder().name("mars").age(50).className("二班").build());

        Set<ConstraintViolation<Container>> constraintViolationSet = validator.validate(container);

        print(constraintViolationSet);
    }

    @Test
    public void optionContainer() {
        Container container = new Container();

        container.setTowingCapacity(50);

        Set<ConstraintViolation<Container>> constraintViolationSet = validator.validate(container);

        print(constraintViolationSet);

        Assert.assertEquals(1, constraintViolationSet.size());
    }

}
