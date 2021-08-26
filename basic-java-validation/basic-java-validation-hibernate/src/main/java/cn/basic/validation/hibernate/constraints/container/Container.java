package cn.basic.validation.hibernate.constraints.container;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.*;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Data
public class Container {

    private List<@Size(min = 5, message = "姓名不能小于{min}个字") String> nameList = new ArrayList<>();

    private Set<@ValidAge Integer> ageList = new HashSet<>();

    private Map<String, @Valid @ValidStudent Student> studentMap = new HashMap<>(3);

    private Optional<@MinTowingCapacity(1000) Integer> towingCapacity = Optional.empty();

    public void setTowingCapacity(Integer alias) {
        towingCapacity = Optional.of(alias);
    }


    public void addName(String name) {
        nameList.add(name);
    }

    public void addAge(int age) {
        ageList.add(age);
    }

    public void addStudent(String className, Student student) {
        studentMap.put(className, student);
    }

    @Data
    @Builder
    public static class Student {

        @NotBlank(message = "姓名不能为空")
        private String name;

        @Min(value = 5, message = "学生年纪最小不能小于{value}")
        @Max(value = 12, message = "学生年纪最大不能大于{value}")
        private Integer age;

        private String className;
    }
}
