package cn.basic.validation.hibernate.constraints.groupcheck;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author dragon
 * @date 2021/9/2
 */
@Data
public class Person {

    @NotNull
    private String name;

    public Person(String name) {
        this.name = name;
    }

}
