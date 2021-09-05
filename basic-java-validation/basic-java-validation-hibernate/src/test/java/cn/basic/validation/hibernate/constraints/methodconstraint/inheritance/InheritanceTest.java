package cn.basic.validation.hibernate.constraints.methodconstraint.inheritance;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolation;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/9/2
 */
public class InheritanceTest extends TestAbstractCase {

    /**
     * 一个子类 覆盖了 父类的 参数约束 这是不被允许的
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void inheritanceTest() throws NoSuchMethodException {
        Vehicle car = new Car();
        Method drive = car.getClass().getMethod("drive", int.class);

//        Set<ConstraintViolation<Vehicle>> constraintViolationSet = executableValidator.validateParameters(car, drive,
//                new Object[]{56});
        int result = car.drive(55);
        Set<ConstraintViolation<Vehicle>> constraintViolationSet = executableValidator.validateReturnValue(car, drive, result);

        print(constraintViolationSet);

    }


    /**
     * 一个方法 覆盖多个父类中声明的方法,而 父类中 有 参数约束校验,则会抛出 ConstraintDeclarationException
     *
     * @throws NoSuchMethodException
     */
    @Test(expected = ConstraintDeclarationException.class)
    public void inheritanceTest2() throws NoSuchMethodException {
        BinDog binDog = new BinDog();

        Method eat = binDog.getClass().getMethod("eat", int.class);

        Set<ConstraintViolation<BinDog>> constraintViolations = executableValidator.validateParameters(binDog, eat, new Object[]{10});



    }

}
