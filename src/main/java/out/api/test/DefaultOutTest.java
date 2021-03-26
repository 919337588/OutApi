package out.api.test;

import out.api.OutApi;
import out.api.test.resource.TestResource;
import out.api.util.JsonUtil;
import out.api.util.YouDao;
import redis.clients.jedis.Jedis;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/25 4:00 下午
 */
public class DefaultOutTest {

    public static void main(String[] args) throws Exception {
        //配置有道翻译缓存
        YouDao.jedis = new Jedis("devkmos-inner.kaikeba.com",20095);
        YouDao.jedis.auth("kkb@123.");
        System.out.println(JsonUtil.toJson(OutApi.outapi("out.api")));
        System.out.println(JsonUtil.toJson(OutApi.parseMap));;

        System.out.println("======================");;
        //单个方法
        System.out.println(JsonUtil.toJson(OutApi.getRequestParameter(TestResource.class.getMethod("test",String.class,String.class,String.class))));

    }
}
