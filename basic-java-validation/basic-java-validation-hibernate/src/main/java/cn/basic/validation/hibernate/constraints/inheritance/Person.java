package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class Person {

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 3, message = "姓名长度最小为{min},最大为{max}")
    private String name;


}
