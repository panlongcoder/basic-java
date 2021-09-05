package cn.basic.validation.hibernate.constraints.methodconstraint.inheritance;

import jakarta.validation.constraints.Min;

/**
 * @author dragon
 * @date 2021/9/2
 */
public interface Animal {


    void eat(@Min(20) int rice);
}
