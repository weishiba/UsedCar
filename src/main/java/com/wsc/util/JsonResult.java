package com.wsc.util;

import lombok.Data;

/**
 * @author wsc
 * @date 2021/4/29
 */
@Data
public class JsonResult {
    private Integer code;
    private String message;
    private Integer count;
    private Object data;

    public JsonResult() {

    }
    public JsonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
