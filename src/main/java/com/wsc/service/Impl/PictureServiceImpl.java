package com.wsc.service.Impl;

import com.wsc.entity.Picture;
import com.wsc.mapper.PictureMapper;
import com.wsc.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/5/22
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> getAllPicture() {
        return null;
    }

    @Override
    public Picture getById(String id) {
        return pictureMapper.selectById(id);
    }

    @Override
    public Picture getByCarId(String carId) {
        return pictureMapper.selectByCarId(carId);
    }

    @Override
    public int addPicture(Picture picture) {
        return pictureMapper.insertPicture(picture);
    }

    @Override
    public int removePictureById(String id) {
        return pictureMapper.deleteById(id);
    }

    @Override
    public int changePicture(Picture picture) {
        return pictureMapper.updateById(picture);
    }
}
