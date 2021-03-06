package com.wsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsc.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/4
 */
@Mapper
public interface PictureMapper extends BaseMapper<Picture> {

    /**
     * 查询
     * @return
     */
    List<Picture> selectAllPicture();

    Picture selectById(String id);

    Picture selectByCarId(String carId);

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
    int deleteById(String id);

    int deletePictureByCarId(String carId);
    /**
     * 修改
     * @param picture
     * @return
     */
    int updatePicture(Picture picture);


}
