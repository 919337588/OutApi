package out.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/12 4:19 δΈε
 */
public class YouDao {
    private static HashMap<String, String> map=new HashMap<>();
    public static Jedis jedis ;

    public static String parse(String str) {
        String rs = "";
        if(str.indexOf("_")>-1||str.indexOf("-")>-1||str.indexOf("β")>-1){
            str=str.replace("_"," ");
            str=str.replace("-"," ");
            str=str.replace("β"," ");
            rs=str;
        }else{
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    rs += " " + Character.toLowerCase(c);
                } else {
                    rs += c;
                }
            }
        }
        return rs.replace("  "," ").replace(" ","+").trim();
    }

    public static String execCurl(String val) throws IOException, InterruptedException {
        val=parse(val);
        String s="";
        if(jedis!=null){
             s = jedis.get("youdao_" + val);
            if(s!=null&&!"".equals(s.trim())){
                return s;
            }
        }else{
            s = map.get(val);
            if(s!=null&&!"".equals(s.trim())){
                return s;
            }
        }

        String[] cmds = {"curl", "http://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i="+val
        };

        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        p = process.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        s=builder.toString();
        Map<Object, Object> objectObjectMap =JsonUtil.toMap(s);
        Object translateResult1 = objectObjectMap.get("translateResult");
        if(translateResult1!=null){
            List<Object> translateResult = (List<Object>)translateResult1;
            if(translateResult.size()>0){
                try {
                    s =((Map)translateResult.get(0)).get("tgt")+"";
                }catch (Exception e){
                    s =((Map)((List)translateResult.get(0)).get(0)).get("tgt")+"";
                }
            }
        }
       if(jedis!=null){
           jedis.set("youdao_" + val,s);
       }
        map.put(val,s);
        return s;
    }

}
