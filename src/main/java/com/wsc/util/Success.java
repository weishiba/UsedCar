package com.wsc.util;

/**
 * @author wsc
 * @date 2021/5/2
 */
public class Success extends JsonResult{


    public Success(Object data, Integer count){
        this.code = 0;
        this.message = "";
        this.data = data;
        this.count = count;
    }
}
