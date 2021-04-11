package com.wsc.service;

import com.wsc.entity.Audit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface AuditService {
    /**
     * 查询
     * @return
     */
    List<Audit> getAllAudit();

    Audit getById(Long id);

    /**
     * 添加
     * @param audit
     * @return
     */
    int addAudit(Audit audit);

    /**
     * 删除
     * @param id
     * @return
     */
    int removeAuditById(Long id);

    /**
     * 修改
     * @param audit
     * @return
     */
    int changeAudit(Audit audit);
}
