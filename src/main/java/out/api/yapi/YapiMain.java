package out.api.yapi;

import out.api.OutApi;
import out.api.util.JsonUtil;

import java.util.*;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/16 4:05 下午
 */
public class YapiMain {


    public static Long catid=null;
    public static Long project_id=null;
    public static Long id_start=null;
    public static Long uid=null;
    public static void out(String bao) {
        for (OutApi.ControlMethon controlMethon : OutApi.outapi(bao)) {
            JsonRootBean jsonRootBean = new JsonRootBean();
            jsonRootBean.setPath(controlMethon.path);
            jsonRootBean.setProject_id("NumberInt("+project_id+")");
            jsonRootBean.set_id("NumberInt("+id_start+++")");
            jsonRootBean.setTitle(controlMethon.name);
            jsonRootBean.setMethod(controlMethon.type);
            jsonRootBean.setReq_body_type(controlMethon.requestBody==null?"form":"json");
            if(controlMethon.type.equals("GET")){
                List<Req_params> list=new LinkedList<>();
                for (HashMap hashMap : controlMethon.requestParam) {
                    Optional first = hashMap.entrySet().stream().findFirst();
                    if(first.isPresent()){
                        Map.Entry o = (Map.Entry) first.get();
                        list.add(new Req_params(){{
                            String[] split = o.getKey().toString().split("~~");
                            setName(split[0]);
                            setDesc(split[1]);
                        }});
                    }
                }
                jsonRootBean.setReq_params(list);
            }
            else {
                List<Req_body_form> list=new LinkedList<>();
                for (HashMap hashMap : controlMethon.requestParam) {
                    Optional first = hashMap.entrySet().stream().findFirst();
                    if(first.isPresent()){
                        Map.Entry o = (Map.Entry) first.get();
                        list.add(new Req_body_form(){{
                            String[] split = o.getKey().toString().split("~~");
                            setName(split[0]);
                            setDesc(split[1]);
                            setExample(o.getValue().toString());
                        }});
                    }
                }
                jsonRootBean.setReq_body_form(list);
            }
            List<Req_params> list=new LinkedList<>();
            for (HashMap hashMap : controlMethon.pathVariable) {
                Optional first = hashMap.entrySet().stream().findFirst();
                if(first.isPresent()){
                    Map.Entry o = (Map.Entry) first.get();
                    list.add(new Req_params(){{
                        String[] split = o.getKey().toString().split("~~");
                        setName(split[0]);
                        setDesc(split[1]);
                    }});
                }
            }
            jsonRootBean.getReq_params().addAll(list);
            List<Req_headers> req_headers=new LinkedList<>();
            req_headers.add(new Req_headers(){{
                if(jsonRootBean.getReq_params().size()>0){
                    setValue("application/x-www-form-urlencoded");
                }
                else if(jsonRootBean.getReq_body_form().size()>0){
                    setValue("multipart/form-data");
                }else{
                    setValue("application/json");
                };
                setName("Content-Type");
            }});
            for (String consume : controlMethon.consumes) {
                req_headers.add(new Req_headers(){{
                    setValue(consume);
                    setName("Content-Type");
                }});
            }
            jsonRootBean.setReq_headers(req_headers);
            Query_path query_path = new Query_path();
            query_path.setPath(controlMethon.path);
            jsonRootBean.setQuery_path(query_path);
            query_path.setParams(new ArrayList<>());
            if(controlMethon.returnJson!=null){
                jsonRootBean.setRes_body(JsonUtil.toJson(controlMethon.returnJson));
            }
            if(controlMethon.requestBody!=null){
                jsonRootBean.setRes_body(JsonUtil.toJson(controlMethon.returnJson));
            }
            String s = JsonUtil.toJson(jsonRootBean);
            s=s.replace("\"ObjectId(\\\"","ObjectId(\"");
            s=s.replace("\\\")\"","\")");
            s=s.replace("\"NumberInt(","NumberInt(");
            s=s.replace(")\"",")");
            System.out.println(s);
        }


    }


}
