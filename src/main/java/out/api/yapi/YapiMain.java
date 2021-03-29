package out.api.yapi;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import out.api.OutApi;
import out.api.util.JsonUtil;
import out.api.util.MongoDBUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chixin
 * @version 1.0
 * @date 2021/3/16 4:05 下午
 */
public class YapiMain {

    private static MongoDatabase yapi;

    public static Long catid = null;
    public static Long project_id = null;
    private static Long id_start = null;
    public static Long uid = null;


    public static void out(String bao, String projectName, String catName, String mogoIp, int mogoPort) throws Exception {
        init(projectName, catName, mogoIp, mogoPort);
        List<JsonRootBean> yapiBean = getYapiBean(bao);
        insertInterface(yapiBean);

    }

    public static void insertInterface(List<JsonRootBean> yapiBean) {
        MongoCollection<Document> interfaces = yapi.getCollection("interface");
        MongoCursor<Document> iterator = interfaces.find().iterator();
        HashSet<String> pathhc = new HashSet<>();
        while (iterator.hasNext()) {
            Document next = iterator.next();
            String method = next.get("method").toString();
            String path = next.get("path").toString();
            int i = path.indexOf("?");
            if (i >= 0) {
                path = path.substring(0, i);
            }
            pathhc.add(method + "~~" + path);

        }
        List<Document> collect = yapiBean.stream()
                .filter(
                        v -> !pathhc.contains(v.getMethod() + "~~" + v.getPath())
                )
                .map(MongoDBUtil::toDocument).collect(Collectors.toList());
        if(collect.size()>0){
            interfaces.insertMany(collect);
        }

    }

    public static List<JsonRootBean> getYapiBean(String bao) throws Exception {
        List<JsonRootBean> jsonRootBeans = new LinkedList<>();
        for (OutApi.ControlMethon controlMethon : OutApi.outapi(bao)) {
            JsonRootBean jsonRootBean = new JsonRootBean();
            if (!controlMethon.path.startsWith("/")) {
                controlMethon.path = "/" + controlMethon.path;
            }
            jsonRootBean.setPath(controlMethon.path);
            jsonRootBean.setProject_id(project_id);
            jsonRootBean.set_id(id_start++);
            jsonRootBean.setTitle(controlMethon.name);
            jsonRootBean.setMethod(controlMethon.type);
            jsonRootBean.setReq_body_type(controlMethon.requestBody == null ? "form" : "json");
            if (controlMethon.type.equals("GET")) {
                List<Req_params> list = new LinkedList<>();
                for (HashMap hashMap : controlMethon.requestParam) {
                    Optional first = hashMap.entrySet().stream().findFirst();
                    if (first.isPresent()) {
                        Map.Entry o = (Map.Entry) first.get();
                        list.add(new Req_params() {{
                            String[] split = o.getKey().toString().split("~~");
                            setName(split[0]);
                            setDesc(split[1]);
                        }});
                    }
                }
                jsonRootBean.setReq_params(list);
            } else {
                List<Req_body_form> list = new LinkedList<>();
                for (HashMap hashMap : controlMethon.requestParam) {
                    Optional first = hashMap.entrySet().stream().findFirst();
                    if (first.isPresent()) {
                        Map.Entry o = (Map.Entry) first.get();
                        list.add(new Req_body_form() {{
                            String[] split = o.getKey().toString().split("~~");
                            setName(split[0]);
                            setDesc(split[1]);
                            setExample(o.getValue().toString());
                        }});
                    }
                }
                jsonRootBean.setReq_body_form(list);
            }
            List<Req_params> list = new LinkedList<>();
            for (HashMap hashMap : controlMethon.pathVariable) {
                Optional first = hashMap.entrySet().stream().findFirst();
                if (first.isPresent()) {
                    Map.Entry o = (Map.Entry) first.get();
                    list.add(new Req_params() {{
                        String[] split = o.getKey().toString().split("~~");
                        setName(split[0]);
                        setDesc(split[1]);
                    }});
                }
            }
            jsonRootBean.getReq_params().addAll(list);
            List<Req_headers> req_headers = new LinkedList<>();
            req_headers.add(new Req_headers() {{
                if (jsonRootBean.getReq_params().size() > 0) {
                    setValue("application/x-www-form-urlencoded");
                } else if (jsonRootBean.getReq_body_form().size() > 0) {
                    setValue("multipart/form-data");
                } else {
                    setValue("application/json");
                }
                ;
                setName("Content-Type");
            }});
            for (String consume : controlMethon.consumes) {
                req_headers.add(new Req_headers() {{
                    setValue(consume);
                    setName("Content-Type");
                }});
            }
            jsonRootBean.setReq_headers(req_headers);
            Query_path query_path = new Query_path();
            query_path.setPath(controlMethon.path);
            jsonRootBean.setQuery_path(query_path);
            query_path.setParams(new ArrayList<>());
            if (controlMethon.returnJson != null) {
                jsonRootBean.setRes_body(JsonUtil.toJson(controlMethon.returnJson));
            }
            if (controlMethon.requestBody != null) {
                jsonRootBean.setRes_body(JsonUtil.toJson(controlMethon.returnJson));
            }
            jsonRootBeans.add(jsonRootBean);
        }

        return jsonRootBeans;
    }

    public static void init(String projectName, String catName, String ip, int port) throws Exception {
        MongoDBUtil.initMogo(ip, port);
        yapi = MongoDBUtil.getMongoClient().getDatabase("yapi");
        Document projectDocument = initProject(projectName);
        project_id = Long.valueOf(projectDocument.get("_id").toString());
        Document catDocument = initCat(project_id, catName);
        uid = Long.valueOf(catDocument.get("uid").toString());
        catid = Long.valueOf(catDocument.get("_id").toString());
        id_start = project_id * 10000;
        Long maxId = getMaxId(project_id) + 1;
        id_start = id_start > maxId ? id_start : maxId;
    }

    public static Long getMaxId(long project_id) {
        Long maxId = Long.MIN_VALUE;
        MongoCollection<Document> interfaces = yapi.getCollection("interface");
        Bson filter = Filters.eq("project_id", project_id);
        FindIterable findIterable = interfaces.find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            Document document = (Document) cursor.next();
            Long id = Long.valueOf(document.get("_id").toString());
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId;
    }


    public static Document initCat(long project_id, String catName) {
        Document document = null;
        MongoCollection<Document> interface_cat = yapi.getCollection("interface_cat");
        Bson filter = Filters.and(Filters.eq("name", catName), Filters.eq("project_id", project_id));
        FindIterable findIterable = interface_cat.find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            document = (Document) cursor.next();
        }
        return document;
    }

    public static Document initProject(String projectName) throws Exception {
        Document document = null;

        MongoCollection<Document> project = yapi.getCollection("project");
        Bson filter = Filters.eq("name", projectName);
        FindIterable findIterable = project.find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            if (document != null) {
                throw new Exception("projectName名称存在多个服务");
            }
            document = (Document) cursor.next();
        }
        return document;
    }
}
