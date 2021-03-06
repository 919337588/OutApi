/**
  * Copyright 2021 json.cn 
  */
package out.api.yapi;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * Auto-generated: 2021-03-16 15:52:43
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean  {


    private Long _id;
    private String method;
    private Long catid=  YapiMain.catid;
    private String title;
    private String path;
    private Long project_id;
    private String res_body_type="json";
    private Long uid= YapiMain.uid;
    private Long add_time=System.currentTimeMillis()/1000;
    private Long up_time=System.currentTimeMillis()/1000;
    private Long index= 0L;
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
    private Long edit_uid=0L;
    private Long __v=0L;
    private String desc="";
    private String markdown="";
    private String req_body_type;
    private String res_body;
    private String req_body;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Long getCatid() {
        return catid;
    }

    public void setCatid(Long catid) {
        this.catid = catid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getRes_body_type() {
        return res_body_type;
    }

    public void setRes_body_type(String res_body_type) {
        this.res_body_type = res_body_type;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public Long getUp_time() {
        return up_time;
    }

    public void setUp_time(Long up_time) {
        this.up_time = up_time;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public boolean isApi_opened() {
        return api_opened;
    }

    public void setApi_opened(boolean api_opened) {
        this.api_opened = api_opened;
    }

    public boolean isRes_body_is_json_schema() {
        return res_body_is_json_schema;
    }

    public void setRes_body_is_json_schema(boolean res_body_is_json_schema) {
        this.res_body_is_json_schema = res_body_is_json_schema;
    }

    public List<Req_body_form> getReq_body_form() {
        return req_body_form;
    }

    public void setReq_body_form(List<Req_body_form> req_body_form) {
        this.req_body_form = req_body_form;
    }

    public boolean isReq_body_is_json_schema() {
        return req_body_is_json_schema;
    }

    public void setReq_body_is_json_schema(boolean req_body_is_json_schema) {
        this.req_body_is_json_schema = req_body_is_json_schema;
    }

    public List<Req_params> getReq_params() {
        return req_params;
    }

    public void setReq_params(List<Req_params> req_params) {
        this.req_params = req_params;
    }

    public List<Req_headers> getReq_headers() {
        return req_headers;
    }

    public void setReq_headers(List<Req_headers> req_headers) {
        this.req_headers = req_headers;
    }

    public List<String> getReq_query() {
        return req_query;
    }

    public void setReq_query(List<String> req_query) {
        this.req_query = req_query;
    }

    public Query_path getQuery_path() {
        return query_path;
    }

    public void setQuery_path(Query_path query_path) {
        this.query_path = query_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getEdit_uid() {
        return edit_uid;
    }

    public void setEdit_uid(Long edit_uid) {
        this.edit_uid = edit_uid;
    }

    public Long get__v() {
        return __v;
    }

    public void set__v(Long __v) {
        this.__v = __v;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getReq_body_type() {
        return req_body_type;
    }

    public void setReq_body_type(String req_body_type) {
        this.req_body_type = req_body_type;
    }

    public String getRes_body() {
        return res_body;
    }

    public void setRes_body(String res_body) {
        this.res_body = res_body;
    }

    public String getReq_body() {
        return req_body;
    }

    public void setReq_body(String req_body) {
        this.req_body = req_body;
    }
}