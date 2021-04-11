package com.wsc.service;

import com.wsc.entity.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface PictureService {

    /**
     * 查询
     * @return
     */
    List<Picture> getAllPicture();

    Picture getById(Long id);

    /**
     * 添加
     * @param picture
     * @return
     */
    int addPicture(Picture picture);

    /**
     * 删除
     * @param id
     * @return
     */
    int removePictureById(Long id);

    /**
     * 修改
     * @param picture
     * @return
     */
    int changePicture(Picture picture);
}
