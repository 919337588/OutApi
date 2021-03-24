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
public class Req_params {

    private String _id;
    private String desc="";
    private String name;
    Req_params(){
        _id="ObjectId(\""+ UUID.randomUUID().toString().replace("-","").toLowerCase().substring(0,24)+"\")" ;

    }
    public void set_id(String _id) {
         this._id = _id;
     }
     public String get_id() {
         return _id;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

}