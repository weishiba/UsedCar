package com.wsc.util;

import lombok.Data;

/**
 * @author wsc
 * @date 2021/4/29
 */
@Data
public class JsonResult {
    public Integer code;
    public String message;
    public Integer count;
    public Object data;
    public Integer size;

    public JsonResult() {

    }
    public JsonResult(Object data,Integer count) {
        this.code = 0;
        this.message = "成功！";
        this.data = data;
        this.count = count;
    }

    //组装成功返回信息
    public static JsonResult success(Object data,Integer count){

        return new JsonResult(data,count);
    }
}
