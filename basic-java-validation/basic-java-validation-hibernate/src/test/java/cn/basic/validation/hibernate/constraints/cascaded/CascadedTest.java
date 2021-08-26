package cn.basic.validation.hibernate.constraints.cascaded;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import jakarta.validation.ConstraintViolation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class CascadedTest  extends TestAbstractCase {

    @Test
    public void cascadedTest() {
        ClassRoom classRoom = new ClassRoom();

        ClassRoom.Coder coder = new ClassRoom.Coder();
        coder.setName("dragon");
        coder.setLevel(1);

        ClassRoom.Coder binbin = new ClassRoom.Coder();
        binbin.setName("binbin");
        binbin.setLevel(5);

        classRoom.addCoder(coder);
        classRoom.addCoder(binbin);

        Set<ConstraintViolation<ClassRoom>> constraintViolationSet = validator.validate(classRoom);

        Assert.assertEquals(1, constraintViolationSet.size());

        print(constraintViolationSet);

    }
}
