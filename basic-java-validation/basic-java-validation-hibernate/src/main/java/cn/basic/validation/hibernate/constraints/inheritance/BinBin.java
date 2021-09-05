package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author dragon
 * @date 2021/8/26
 */
public class BinBin extends Person {

    @AssertTrue(message = "斌斌必须是幽默的")
    private boolean isHumorous;

    @AssertTrue(message = "斌斌必须要是帅的")
    private boolean isHandsome;

    @Size(min = 4, max = 5, message = "binbin姓名长度小4,最大5")
    private String name;

    public boolean isHumorous() {
        return isHumorous;
    }

    public void setHumorous(boolean humorous) {
        isHumorous = humorous;
    }

    public boolean isHandsome() {
        return isHandsome;
    }

    public void setHandsome(boolean handsome) {
        isHandsome = handsome;
    }

    public void setName(String name) {
        this.name = name;
    }
}
