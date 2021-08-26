package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class Toy {

    protected String manufacturer;

    @NotBlank(message = "制造商不能为空")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
