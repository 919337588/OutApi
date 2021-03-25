package out.api.test.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/25 4:03 下午
 */
public class TestBean {


    String myName;
    @ApiModelProperty("参数—年龄")
    String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }
}
