package cn.basic.validation.hibernate.constraints.classes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Data
@ValidPassengerCount
public class Bus {

    private int seatCount;

    private List<Person> passengers;

    @Data
    @AllArgsConstructor
    public static class Person {

        private String name;

        private boolean isChildren;
    }
}
