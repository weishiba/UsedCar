package com.wsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsc.entity.Audit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/4
 */
@Mapper
public interface AuditMapper extends BaseMapper<Audit> {
    /**
     * 查询
     * @return
     */
    List<Audit> selectAllAudit();

    Audit selectById(Long id);

    /**
     * 添加
     * @param audit
     * @return
     */
    int insertAudit(Audit audit);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteAuditById(Long id);

    /**
     * 修改
     * @param audit
     * @return
     */
    int updateAudit(Audit audit);
}
