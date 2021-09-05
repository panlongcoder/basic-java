package cn.basic.validation.hibernate.constraints.cascaded;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Data
public class ClassRoom {

    private int seatCount;

    private Set<@Valid Coder> coderList = new HashSet<>();

    public void addCoder(Coder coder) {
        coderList.add(coder);
    }

    @Data
    public static class Coder {

        @NotEmpty(message = "程序员姓名不能为空")
        private String name;

        @Min(value = 2, message = "至少要{value}级证书")
        private int level;

    }
}
