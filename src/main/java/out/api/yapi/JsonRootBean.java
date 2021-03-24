/**
  * Copyright 2021 json.cn 
  */
package out.api.yapi;
import java.util.LinkedList;
import java.util.List;

/**
 * Auto-generated: 2021-03-16 15:52:43
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private String _id;
    private String method;
    private String catid=  "NumberInt("+YapiMain.catid+")";
    private String title;
    private String path;
    private String project_id;
    private String res_body_type="json";
    private String uid= "NumberInt("+YapiMain.uid+")";
    private String add_time="NumberInt("+System.currentTimeMillis()/1000+")";
    private String up_time="NumberInt("+System.currentTimeMillis()/1000+")";
    private String index= "NumberInt(0)";
    private boolean api_opened =false;
    private boolean res_body_is_json_schema =false;
    private List<Req_body_form> req_body_form=new LinkedList<Req_body_form>();
    private boolean req_body_is_json_schema=false;
    private List<Req_params> req_params=new LinkedList<Req_params>();
    private List<Req_headers> req_headers=new LinkedList<Req_headers>();
    private List<String> req_query;
    private Query_path query_path;
    private String type="var";
    private String status="undone";
    private String edit_uid="NumberInt(0)";
    private String __v="NumberInt(0)";
    private String desc="";
    private String markdown="";
    private String req_body_type;
    private String res_body;
    private String req_body;

    public boolean isApi_opened() {
        return api_opened;
    }

    public boolean isRes_body_is_json_schema() {
        return res_body_is_json_schema;
    }

    public boolean isReq_body_is_json_schema() {
        return req_body_is_json_schema;
    }

    public String getReq_body() {
        return req_body;
    }

    public void setReq_body(String req_body) {
        this.req_body = req_body;
    }

    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setMethod(String method) {
         this.method = method;
     }
     public String getMethod() {
         return method;
     }

    public void setCatid(String catid) {
         this.catid = catid;
     }
     public String getCatid() {
         return catid;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setPath(String path) {
         this.path = path;
     }
     public String getPath() {
         return path;
     }

    public void setProject_id(String project_id) {
         this.project_id = project_id;
     }
     public String getProject_id() {
         return project_id;
     }

    public void setRes_body_type(String res_body_type) {
         this.res_body_type = res_body_type;
     }
     public String getRes_body_type() {
         return res_body_type;
     }

    public void setUid(String uid) {
         this.uid = uid;
     }
     public String getUid() {
         return uid;
     }

    public void setAdd_time(String add_time) {
         this.add_time = add_time;
     }
     public String getAdd_time() {
         return add_time;
     }

    public void setUp_time(String up_time) {
         this.up_time = up_time;
     }
     public String getUp_time() {
         return up_time;
     }

    public void setIndex(String index) {
         this.index = index;
     }
     public String getIndex() {
         return index;
     }

    public void setApi_opened(boolean api_opened) {
         this.api_opened = api_opened;
     }
     public boolean getApi_opened() {
         return api_opened;
     }

    public void setRes_body_is_json_schema(boolean res_body_is_json_schema) {
         this.res_body_is_json_schema = res_body_is_json_schema;
     }
     public boolean getRes_body_is_json_schema() {
         return res_body_is_json_schema;
     }

    public void setReq_body_form(List<Req_body_form> req_body_form) {
         this.req_body_form = req_body_form;
     }
     public List<Req_body_form> getReq_body_form() {
         return req_body_form;
     }

    public void setReq_body_is_json_schema(boolean req_body_is_json_schema) {
         this.req_body_is_json_schema = req_body_is_json_schema;
     }
     public boolean getReq_body_is_json_schema() {
         return req_body_is_json_schema;
     }

    public void setReq_params(List<Req_params> req_params) {
         this.req_params = req_params;
     }
     public List<Req_params> getReq_params() {
         return req_params;
     }

    public void setReq_headers(List<Req_headers> req_headers) {
         this.req_headers = req_headers;
     }
     public List<Req_headers> getReq_headers() {
         return req_headers;
     }

    public void setReq_query(List<String> req_query) {
         this.req_query = req_query;
     }
     public List<String> getReq_query() {
         return req_query;
     }

    public void setQuery_path(Query_path query_path) {
         this.query_path = query_path;
     }
     public Query_path getQuery_path() {
         return query_path;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setEdit_uid(String edit_uid) {
         this.edit_uid = edit_uid;
     }
     public String getEdit_uid() {
         return edit_uid;
     }

    public void set__v(String __v) {
         this.__v = __v;
     }
     public String get__v() {
         return __v;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setMarkdown(String markdown) {
         this.markdown = markdown;
     }
     public String getMarkdown() {
         return markdown;
     }

    public void setReq_body_type(String req_body_type) {
         this.req_body_type = req_body_type;
     }
     public String getReq_body_type() {
         return req_body_type;
     }

    public void setRes_body(String res_body) {
         this.res_body = res_body;
     }
     public String getRes_body() {
         return res_body;
     }

}