package out.api.test;

import out.api.util.YouDao;
import out.api.yapi.YapiMain;
import redis.clients.jedis.Jedis;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/25 3:56 下午
 */
public class YapiOutTest {
    public static void main(String[] args) {
        //配置有道翻译缓存
        YouDao.jedis = new Jedis("devkmos-inner.kaikeba.com",20095);
        YouDao.jedis.auth("kkb@123.");

        YapiMain.catid=1008;
        YapiMain.project_id=350;
        YapiMain.id_start=18520004;
        YapiMain.uid=468;
        YapiMain.out("out.api");
    }
}
