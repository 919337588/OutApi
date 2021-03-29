package out.api.test;

import out.api.util.MongoDBUtil;
import out.api.util.YouDao;
import out.api.yapi.YapiMain;
import redis.clients.jedis.Jedis;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/25 3:56 下午
 */
public class YapiOutTest {
    public static void main(String[] args) throws Exception {


        //配置有道翻译的redis缓存  如果不配置缓存  可能会被有道封ip
        YouDao.jedis = new Jedis("xxxxxxxx",1111);
        YouDao.jedis.auth("xxxxxx");

        YapiMain.out("out.api","OutApi测试","outApi导出的api","xxxxxx",1111);

    }
}
