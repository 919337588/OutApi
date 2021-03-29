package out.api.yapi; /**
 * Copyright 2021 json.cn
 */

import java.util.UUID;

/**
 * Auto-generated: 2021-03-16 16:26:1
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Params {

    private String _id;
    private String desc;
    private String example;
    private String name;
    Params(){
        _id=UUID.randomUUID().toString().replace("-","").toLowerCase().substring(0,24) ;
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

    public void setExample(String example) {
        this.example = example;
    }
    public String getExample() {
        return example;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}