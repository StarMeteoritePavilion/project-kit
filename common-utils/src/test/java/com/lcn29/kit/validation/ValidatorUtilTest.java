package com.lcn29.kit.validation;

import com.lcn29.kit.validation.domain.Demo;
import com.lcn29.kit.validation.group.CommonCreate;
import org.junit.jupiter.api.Test;

import javax.validation.groups.Default;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2020-02-18 14:45
 */
public class ValidatorUtilTest {

    @Test
    public void test() {
        Demo demo = new Demo();
        demo.setAge(101);
        demo.setName("Demo");
        demo.setSex(true);

        ValidatorUtil.ValidResult result = ValidatorUtil.validate(demo, Default.class, CommonCreate.class);

        if (result.getValueResult()) {
            System.out.println("valid success");
        } else {
            System.out.println("valid fail");
            result.getErrorMsgList().forEach(System.out::println);
        }

    }
}
