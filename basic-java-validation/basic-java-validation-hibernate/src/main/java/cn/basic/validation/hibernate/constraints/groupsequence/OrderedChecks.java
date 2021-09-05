package cn.basic.validation.hibernate.constraints.groupsequence;

import cn.basic.validation.hibernate.constraints.groupcheck.CarChecks;
import cn.basic.validation.hibernate.constraints.groupcheck.DriverChecks;
import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

/**
 * @author dragon
 * @date 2021/9/2
 */
@GroupSequence({Default.class, CarChecks.class, DriverChecks.class})
public interface OrderedChecks {
}
