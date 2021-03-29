/**
  * Copyright 2021 json.cn 
  */
package out.api.yapi;

import java.util.UUID;

/**
 * Auto-generated: 2021-03-16 15:52:43
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Req_headers {

    private String required="1";
    private String _id;
    private String value;
    private String name;
    Req_headers(){
        _id= UUID.randomUUID().toString().replace("-","").toLowerCase().substring(0,24) ;

    }
    public void setRequired(String required) {
         this.required = required;
     }
     public String getRequired() {
         return required;
     }

    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setValue(String value) {
         this.value = value;
     }
     public String getValue() {
         return value;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

}