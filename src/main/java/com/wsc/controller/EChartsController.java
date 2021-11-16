package com.wsc.controller;

import com.wsc.VO.EChartsVO.CarEChartsVO;
import com.wsc.VO.EChartsVO.UserEChartsVO;
import com.wsc.mapper.CarMapper;
import com.wsc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsc
 * @date 2021/5/4
 */
@RequestMapping("ECharts")
@RestController
public class EChartsController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarMapper carMapper;

    @RequestMapping("/userSum")
    public Integer userSum() {
        return userMapper.selectCount(null);
    }

    @RequestMapping("/user")
    public List<Map<String, String>> user() {
        UserEChartsVO vo = userMapper.userSum();
        Map<String, String> map5 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map4 = new HashMap<>();
        map2.put("name", "黑名单用户");
        map2.put("value", vo.getBlack().toString());
        map4.put("name", "普通用户");
        map4.put("value", vo.getUsers().toString());
        map5.put("name", "管理员");
        map5.put("value", vo.getManager().toString());
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map2);
        list.add(map4);
        list.add(map5);
        return list;
    }

    @RequestMapping("/car")
    public List<Integer> car() {
        List<Integer> list = new ArrayList<>();
        CarEChartsVO vo = carMapper.selectSum();
        list.add(vo.getOnsale());
        list.add(vo.getNosale());
        list.add(vo.getSum());
        list.add(vo.getSell());
        list.add(vo.getBuy());
        return list;
    }
}
