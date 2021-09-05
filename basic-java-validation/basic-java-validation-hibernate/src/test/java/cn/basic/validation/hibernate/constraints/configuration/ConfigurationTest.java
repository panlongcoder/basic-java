package cn.basic.validation.hibernate.constraints.configuration;

import cn.basic.validation.hibernate.constraints.TestAbstractCase;
import cn.basic.validation.hibernate.constraints.methodconstraint.inheritance.Car;
import cn.basic.validation.hibernate.constraints.methodconstraint.inheritance.Vehicle;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/9/3
 */
public class ConfigurationTest extends TestAbstractCase {

    @Test
    public void configTest() throws NoSuchMethodException {
        HibernateValidatorConfiguration configuration = Validation.byProvider(HibernateValidator.class)
                .configure();

        // 级联校验
        configuration.allowMultipleCascadedValidationOnReturnValues(true)
                // 子类允许覆盖父类方法约参数约束
                .allowOverridingMethodAlterParameterConstraint(true)
                // 允许并行方法定义参数约束
                .allowParallelMethodsDefineParameterConstraints(true);

        ValidatorFactory validatorFactory = configuration.failFast(true)
                .buildValidatorFactory();

        Validator validator = validatorFactory.getValidator();

        ExecutableValidator executableValidator = validator.forExecutables();

        Vehicle car = new Car();
        Method drive = car.getClass().getMethod("drive", int.class);

        Set<ConstraintViolation<Vehicle>> constraintViolations = executableValidator.validateParameters(car, drive, new Object[]{71});

        Assert.assertEquals(1, constraintViolations.size());

        print(constraintViolations);
    }
}
