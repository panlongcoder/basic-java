package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.Size;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class CarToy extends Toy {

    @Size(min = 2, max = 5, message = "制造商名称长度在{min}到{max}之间")
    public String getManufacturer() {
        return manufacturer;
    }
}
