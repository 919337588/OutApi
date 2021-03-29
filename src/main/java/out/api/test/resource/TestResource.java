package out.api.test.resource;

import org.springframework.web.bind.annotation.*;
import out.api.test.dto.TestBean;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/25 4:02 下午
 */
@RestController
@RequestMapping("api/v1.0/")
public class TestResource {
    @RequestMapping(method = RequestMethod.GET,value = "out/{execution_time}/test")
    public TestBean test(@RequestParam(value = "parameterOne")String one, @RequestParam String two, @PathVariable("execution_time") String id){
    return  new TestBean();
    }

}
