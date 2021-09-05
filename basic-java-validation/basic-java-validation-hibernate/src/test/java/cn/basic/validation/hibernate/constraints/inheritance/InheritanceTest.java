package cn.basic.validation.hibernate.constraints.inheritance;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class InheritanceTest extends TestAbstractCase {

    /**
     * 当一个类 实现 一个接口 或者 继承 另外 一个类,这 父类 或者 父接口 上 所有的 校验约束,都会以同样的方式应用于 该实现类中,
     * <p>
     * 也就是说 该类 继承了 所有的 校验约束.
     * <p>
     * 而有一点, 父类方法有 参数校验,而子类重写了父类的方法,同时 覆盖了 父类中同样的 参数校验约束,则会抛出ConstraintDeclarationException
     */
    @Test
    public void testBinBin() {
        BinBin binBin = new BinBin();
        binBin.setHumorous(true);
        binBin.setHandsome(false);
        binBin.setName("dragon1992");

        Set<ConstraintViolation<Person>> constraintViolationSet = validator.validate(binBin);

//        Assert.assertEquals(2, constraintViolationSet.size());

        print(constraintViolationSet);

    }


    @Test
    public void testOverrideMethod() {
        CarToy carToy = new CarToy();

        carToy.setManufacturer("");

        Set<ConstraintViolation<CarToy>> constraintViolationSet = validator.validate(carToy);

        print(constraintViolationSet);
    }
}
