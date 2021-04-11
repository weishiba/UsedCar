package com.wsc.mapper;

import com.wsc.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/4
 */
@Mapper
public interface PictureMapper {

    /**
     * 查询
     * @return
     */
    List<Picture> selectAllPicture();

    Picture selectById(Long id);

    /**
     * 添加
     * @param picture
     * @return
     */
    int insertPicture(Picture picture);

    /**
     * 删除
     * @param id
     * @return
     */
    int deletePictureById(Long id);

    /**
     * 修改
     * @param picture
     * @return
     */
    int updatePicture(Picture picture);
}
