package cn.basic.validation.hibernate.constraints.methodconstraint;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

/**
 * @author dragon
 * @date 2021/8/27
 */
public class RentalStation {

    @Size(min = 20, message = "名称长度不能小于{min}")
    private String name;

    @Valid
    public RentalStation(@NotBlank(message = "姓名不能为空") String name) {
        this.name = name;
    }

    public void rentCar(
            @Valid @NotNull Customer customer,
            @NotNull @Future Date startDate,
            @Min(1) int durationInDays) {

    }

    @Size(min = 20, message = "长度至少为{min}")
    public String getName(String name) {
        return name;
    }


    @Data
    public static class Customer {

        @NotBlank(message = "name must not be blank")
        private String name;

        @Min(10)
        private int level;
    }


}
