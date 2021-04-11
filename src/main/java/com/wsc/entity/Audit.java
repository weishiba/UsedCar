package com.wsc.entity;

import lombok.Data;

/**
 * Created by wsc on 2021/4/3
 * @author 18560
 */
@Data
public class Audit {
    private Long id;
    private Long createrId;//创建人编号
    private String createrName;//创建人姓名
    private String createTime;//创建时间
    private Long auditerId;//审核人编号
    private String auditerName;//审核人姓名
    private String auditerTime;//审核时间
    private Integer auditStatus;//审核状态（0：驳回，1:审核中，2：通过）
    private String auditNote;//审核备注
}
