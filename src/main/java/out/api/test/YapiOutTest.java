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
    public static void main(String[] args) throws Exception {
        //配置有道翻译缓存
        YouDao.jedis = new Jedis("devkmos-inner.kaikeba.com",20095);
        YouDao.jedis.auth("kkb@123.");
        //项目下子目录的id
        YapiMain.catid= Long.valueOf(1008);
        //yapi项目id
        YapiMain.project_id= Long.valueOf(350);
        //主键id起始值
        YapiMain.id_start= Long.valueOf(18520004);
        //操作用户id
        YapiMain.uid= Long.valueOf(468);
        YapiMain.out("out.api");


        //通过数据库链接工具链接yapi的数据库   将程序执行输出的json直接导入Yapi数据库即可
    }
}
