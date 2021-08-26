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

    @Test
    public void testBinBin() {
        BinBin binBin = new BinBin();
        binBin.setHandsome(false);
        binBin.setHumorous(true);

        Set<ConstraintViolation<BinBin>> constraintViolationSet = validator.validate(binBin);

        Assert.assertEquals(2, constraintViolationSet.size());

        print(constraintViolationSet);

    }

    @Test
    public void testOverrideMethod() {
        CarToy carToy = new CarToy();

        carToy.setManufacturer("阿里巴巴钉钉客服: 057128284523");

        Set<ConstraintViolation<CarToy>> constraintViolationSet = validator.validate(carToy);

        print(constraintViolationSet);
    }
}
