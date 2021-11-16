package com.wsc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.VO.CarVO;
import com.wsc.entity.Car;
import com.wsc.entity.Picture;
import com.wsc.entity.User;
import com.wsc.entity.UserCarInfo;
import com.wsc.mapper.CarMapper;
import com.wsc.mapper.PictureMapper;
import com.wsc.mapper.UserCarInfoMapper;
import com.wsc.mapper.UserMapper;
import com.wsc.service.CarService;
import com.wsc.util.JsonResult;
import com.wsc.util.Result;
import com.wsc.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wsc
 * @date 2021/4/22
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCarInfoMapper userCarInfoMapper;

    Map<String, Object> param = new HashMap<>();

    @Override
    public List<Car> getAllCar() {
        return carMapper.selectAllCar();
    }

    @Override
    public List<Car> getByCondition(CarConditionVO conditionVO) {
        return carMapper.selectByCondition(conditionVO);
    }

    @Override
    public JsonResult getCar(String id) {
        return null;
    }

    @Override
    public JsonResult getAbleCar(CarVO vo, Integer page, Integer limit) {
        System.out.println("打印搜索条件：" + vo);
        //组装搜索条件(已上架)
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("sale", 1);
        if (StringUtil.isNotEmpty(vo.getType())) {
            wrapper.eq("type", vo.getType());
        }
        if (StringUtil.isNotEmpty(vo.getBrand())) {
            wrapper.eq("brand", vo.getBrand());
        }
        if (vo.getPriceStr() != null && vo.getPriceEnd() != null) {
            wrapper.between("price", vo.getPriceStr(), vo.getPriceEnd());
        }
        if (vo.getSaleType() != null) {
            wrapper.eq("sale_type", vo.getSaleType());
        }
        return getJson(wrapper, page, limit);
    }

    @Override
    public JsonResult getDisableCar(CarVO vo, Integer page, Integer limit) {
        System.out.println("打印搜索条件：" + vo);
        //组装搜索条件(已上架)
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("sale", 0);
        if (StringUtil.isNotEmpty(vo.getType())) {
            wrapper.eq("type", vo.getType());
        }
        //if (vo.getBrand() != null){
        if (StringUtil.isNotEmpty(vo.getBrand())) {
            wrapper.eq("brand", vo.getBrand());
        }
        if (vo.getPriceStr() != null && vo.getPriceEnd() != null) {
            wrapper.between("price", vo.getPriceStr(), vo.getPriceEnd());
        }
        if (vo.getSaleType() != null) {
            wrapper.eq("sale_type", vo.getSaleType());
        }
        return getJson(wrapper, page, limit);
    }

    //查询车辆列表抽象方法
    private JsonResult getJson(QueryWrapper<Car> wrapper, Integer page, Integer limit) {
        IPage<Car> carIPage = new Page<>(page, limit);
        //查询结果集合
        IPage<Car> IPage = carMapper.selectPage(carIPage, wrapper);
        List<Car> carList = IPage.getRecords();
        System.out.println("打印车辆集合信息：" + carList);
        List<CarVO> result = new ArrayList<>();
        for (Car car : carList) {
            //组装表格数据
            CarVO carVO = new CarVO();
            //0.组装车辆信息
            BeanUtils.copyProperties(car, carVO);
            carVO.setCarId(car.getId());
            Picture picture = pictureMapper.selectById(car.getPictureId());
            if (picture != null) {
                //1.组装图片信息
                BeanUtils.copyProperties(picture, carVO);
                carVO.setPictureId(picture.getId());
            }

            UserCarInfo userCarInfo = userCarInfoMapper.selectByCarId(car.getId());
            if (userCarInfo != null) {
                User user = userMapper.selectUser(userCarInfo.getUserId());
                if (user != null) {
                    //2.组装用户信息
                    carVO.setUserId(user.getId());
                    carVO.setPhone(user.getPhone());
                }
            }
            result.add(carVO);
        }

        return JsonResult.success(result, carMapper.selectCount(wrapper));
    }

    @Override
    public PageInfo<Car> getByConditionPages(CarConditionVO conditionVO, Integer pageNow, Integer pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Car> cars = carMapper.selectByCondition(conditionVO);
        PageInfo<Car> carPageInfo = new PageInfo<>(cars);
        carPageInfo.getList();
        return carPageInfo;
    }

    @Override
    public CarVO getById(String id) {
        Car car = carMapper.selectById(id);
        System.out.println("车辆信息" + car);
        Picture picture = pictureMapper.selectByCarId(id);
        CarVO carVO = new CarVO();
        BeanUtils.copyProperties(car, carVO);
        if (picture != null) {
            BeanUtils.copyProperties(picture, carVO);
        }
        System.out.println(carVO);
        return carVO;
    }

    @Override
    public Result addCar(CarVO vo) {
        if (vo.getPrice() == null) {
            return Result.build(400, "车辆标价未输入");
        }
        System.out.println("图片信息：" + vo.getPicture());
        System.out.println("车况图信息" + vo.getPicture1Path());
        System.out.println("添加车辆属性：" + vo);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String uuid = UUID.randomUUID() + "";
        Car car = new Car();
        BeanUtils.copyProperties(vo, car);
        car.setId("wsc" + df2.format(new Date()));
        car.setSaleTime(df.format(new Date()));
        if (StringUtils.isBlank(vo.getPicture())) {
            car.setPicture("default.jpg");
        }
        if (vo.getPicture() != null || vo.getPicture1Path() != null) {
            Picture picture = new Picture();
            BeanUtils.copyProperties(vo, picture);
            picture.setId(uuid);
            picture.setCarId(car.getId());
            System.out.println(picture);
            int i2 = pictureMapper.insertPicture(picture);
            System.out.println("添加图片信息" + i2 + "条");
        }
        car.setPictureId(uuid);
        System.out.println(car);
        int i = carMapper.insertCar(car);
        System.out.println("添加车辆信息" + i + "条");
        if (vo.getUserId() != null) {
            System.out.println("用户ID：" + vo.getUserId());
            UserCarInfo userCarInfo = new UserCarInfo();
            userCarInfo.setCarId(car.getId());
            userCarInfo.setUserId(vo.getUserId());
            userCarInfo.setSaleType(vo.getSaleType());
            int i1 = userCarInfoMapper.insertUserCarInfo(userCarInfo);
            System.out.println("添加人车对应信息" + i1 + "条");
        }
        return Result.ok();
    }

    @Override
    public String removeCarById(String id) {
        Car car = carMapper.selectById(id);
        if (car == null || id == null) {
            return "0";
        }
        if (car.getPictureId() != null) {
            int i = pictureMapper.deleteById(car.getPictureId());
            System.out.println("删除车辆图片信息：" + i + "条");
        }
        UserCarInfo userCarInfo = userCarInfoMapper.selectByCarId(id);
        if (userCarInfo != null) {
            int i = userCarInfoMapper.deleteUserCarInfoById(userCarInfo.getId());
            System.out.println("删除人车对应信息：" + i + "条");
        }


        int i = carMapper.deleteCarById(id);
        System.out.println("删除车辆：" + i + "条");
        /*Map<String,Object> param = new HashMap<>();
        param.put("car_id",id);
        List<UserCarInfo> userCarInfos = userCarInfoMapper.selectByMap(param);*/
        return "1";
    }

    @Override
    public String disable(String id) {
        Car car = carMapper.selectById(id);
        if (car == null || id == null) {
            return "0";
        }
        int i = carMapper.disable(id);
        System.out.println("下架车辆：" + i + "条");
        return "1";
    }

    @Override
    public String able(String id) {
        Car car = carMapper.selectById(id);
        if (car == null || id == null) {
            return "0";
        }
        int i = carMapper.able(id);
        System.out.println("上架车辆：" + i + "条");
        return "1";
    }

    @Override
    public int changeCar(Car car) {
        return carMapper.updateCar(car);
    }

    @Override
    public String edit(CarVO vo) {
        System.out.println("前台传来的车辆信息" + vo);
        Car oldCar = carMapper.selectById(vo.getCarId());
        System.out.println("原先的车辆" + oldCar);
        Car newCar = new Car();
        BeanUtils.copyProperties(vo, newCar);
        newCar.setId(vo.getCarId());
        System.out.println("要修改的车辆信息" + newCar);
        int i = carMapper.updateCar(newCar);
        System.out.println("修改车辆信息：" + i + "条");
        if (newCar.getSaleType() != null) {
            if (!oldCar.getSaleType().equals(newCar.getSaleType())) {
                UserCarInfo userCarInfo = userCarInfoMapper.selectByCarId(vo.getCarId());
                userCarInfo.setSaleType(newCar.getSaleType());
                int i1 = userCarInfoMapper.updateUserCarInfo(userCarInfo);
                System.out.println("修改人车对应信息：" + i1 + "条");
            }
        }

        if (StringUtils.isNotEmpty(vo.getPicture1Path()) || StringUtils.isNotEmpty(vo.getPicture2Path())) {
            Picture p = pictureMapper.selectByCarId(vo.getCarId());
            System.out.println("原先图片："+p);
            BeanUtils.copyProperties(vo, p);
            int i1 = pictureMapper.updatePicture(p);
            System.out.println("修改车辆图片信息：" + i1 + "条");
        }
        return "1";
    }
}
