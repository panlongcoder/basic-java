package cn.basic.validation.hibernate.constraints.inheritance;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

/**
 * @author dragon
 * @date 2021/8/26
 */
@Data
public class BinBin extends Person {

    @AssertTrue(message = "斌斌必须是幽默的")
    private boolean isHumorous;

    @AssertTrue(message = "斌斌必须要是帅的")
    private boolean isHandsome;
}
