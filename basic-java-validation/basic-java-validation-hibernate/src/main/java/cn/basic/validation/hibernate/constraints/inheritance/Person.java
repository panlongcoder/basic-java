package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Data
public class Person {

    @NotBlank(message = "姓名不能为空")
    private String name;


}
