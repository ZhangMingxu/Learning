package com.xufree.learning.spring.beans;

import com.xufree.learning.spring.annotation.ParamConfig;
import org.springframework.stereotype.Component;

/**
 * @author zhangmingxu ON 10:25 2019-05-05
 **/
@Component
public class ParamConfigBean {

    public void method(@ParamConfig(name = "actKey", required = true) String actKey, @ParamConfig(name = "param") String param) {
        System.out.println(actKey);
    }
}
