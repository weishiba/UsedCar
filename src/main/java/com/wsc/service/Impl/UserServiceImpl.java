package com.wsc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsc.VO.RegisterVO;
import com.wsc.VO.backVO.UserVO;
import com.wsc.entity.Permission;
import com.wsc.entity.Role;
import com.wsc.entity.User;
import com.wsc.entity.UserCarInfo;
import com.wsc.mapper.PermissionMapper;
import com.wsc.mapper.RoleMapper;
import com.wsc.mapper.UserCarInfoMapper;
import com.wsc.mapper.UserMapper;
import com.wsc.service.UserService;
import com.wsc.util.JsonResult;
import com.wsc.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author 18560
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserCarInfoMapper userCarInfoMapper;


    @Override
    public List<User> getAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public JsonResult getUser(long id) {
        JsonResult jsonResult = new JsonResult();
        User user = userMapper.selectUser(id);
        jsonResult.setCode(0);
        jsonResult.setMessage("");
        jsonResult.setData(user);
        jsonResult.setCount(1);
        return jsonResult;
    }

    @Override
    public User getUser(String name, String password) {
        return userMapper.selectUser1(name, password);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public String addUser(RegisterVO vo) {
        if (StringUtil.isEmpty(vo.getUsername()) ||
                StringUtil.isEmpty(vo.getPassword()) ||
                StringUtil.isEmpty(vo.getConfirmpassword())) {
            return "-2";
        }
        User user = userMapper.selectUserByName(vo.getUsername());
        if (user != null) {
            //??????????????????,????????????
            return "0";
        } else if (!vo.getPassword().equals(vo.getConfirmpassword())) {
            //???????????????????????????
            return "-1";
        } else {
            user = new User(vo.getUsername(), vo.getPassword(), vo.getPhone(), vo.getSex(), vo.getNote(), 0);
            int i = userMapper.insertUser(user);
            User user1 = userMapper.selectUserByName(vo.getUsername());
            //????????????
            if (vo.getRoleId() != null) {
                Permission permission = new Permission(user1.getId(), vo.getRoleId());
                int i1 = permissionMapper.insertPermission(permission);
            } else {
                //????????????????????????
                Permission permission = new Permission(user1.getId(), 2L);
                int i1 = permissionMapper.insertPermission(permission);
            }
            return "1";
        }
    }

    @Override
    public int removeById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int changeUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public JsonResult getAbleUsers(UserVO vo, Integer page, Integer limit) {
        System.out.println(vo);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("disabled", 0).orderByDesc("id");
        if (vo.getUsername() != null) {
            wrapper.like("username", vo.getUsername());
        }
        if (vo.getSex() != null) {
            wrapper.eq("sex", vo.getSex());
        }
        return getUsers(wrapper,page,limit);

    }

    @Override
    public JsonResult getDisableUsers(UserVO vo, Integer page, Integer limit) {
        System.out.println(vo);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("disabled", 1).orderByDesc("id");
        if (vo.getUsername() != null) {
            wrapper.like("username", vo.getUsername());
        }
        if (vo.getSex() != null) {
            wrapper.eq("sex", vo.getSex());
        }
        return getUsers(wrapper,page,limit);

    }

    private JsonResult getUsers(QueryWrapper<User> wrapper,Integer page, Integer limit){
        JsonResult jsonResult = new JsonResult();
        IPage<User> userIPage = new Page<>(page, limit);
        IPage<User> result = userMapper.selectPage(userIPage, wrapper);
        List<User> allUser = result.getRecords();
        System.out.println("???????????????" + allUser.size());
        List<UserVO> list = new ArrayList<>();
        for (User user : allUser) {
            Map<String, Object> param = new HashMap<>();
            param.put("user_id", user.getId());
            List<Permission> permissions = permissionMapper.selectByMap(param);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            String roleName = "";
            for (Permission p : permissions) {
                Role role = roleMapper.selectById(p.getRoleId());
                if (roleName != "") {
                    roleName += "/";
                }
                roleName += role.getName();
            }
            userVO.setRole(roleName);
            list.add(userVO);
        }
        return JsonResult.success(list,userMapper.selectCount(wrapper));
    }

    @Override
    public String disable(Long id) {
        User user = userMapper.selectUser(id);
        if (id == null || user == null){
            //????????????
            return "0";
        }
        int i = userMapper.disableUser(id);
        System.out.println("???????????????"+i+"???");
        return "1";
    }

    @Override
    public String able(Long id) {
        User user = userMapper.selectUser(id);
        if (id == null || user == null){
            //????????????
            return "0";
        }
        int i = userMapper.ableUser(id);
        System.out.println("???????????????"+i+"???");
        return "1";
    }

    //12???????????????
    private final static int EXPIRE = 12;

    @Override
    public String createToken(User user) {
        //???UUID??????token
        String token = UUID.randomUUID().toString();
        //????????????
        LocalDateTime now = LocalDateTime.now();
        //????????????
        LocalDateTime expireTime = now.plusHours(EXPIRE);
        //??????????????????
        user.setLoginTime(now);
        user.setExpireTime(expireTime);
        user.setToken(token);
        System.out.println("??????token?????????user???"+user);
        userMapper.updateUser(user);
        return token;
    }

    @Override
    public void logout(String token) {
        User user = userMapper.findByToken(token);
        //???UUID??????token
        token = UUID.randomUUID().toString();
        //???????????????token????????????token???????????????????????????token??????
        user.setToken(token);
        //userRepository.save(user);
        userMapper.updateUser(user);
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

    @Override
    public String delete(Long id) {
        User user = userMapper.selectUser(id);
        if (id == null || user == null){
            //????????????
            return "0";
        }
        int i = userMapper.deleteById(id);
        System.out.println("???????????????"+i+"???");
        //??????????????????
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        int delete = permissionMapper.delete(wrapper);
        System.out.println("?????????????????????"+delete+"???");
        List<UserCarInfo> userCarInfos = userCarInfoMapper.selectByUserId(id);
        if (userCarInfos.size()>0){
            QueryWrapper<UserCarInfo> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("user_id",id);
            int delete1 = userCarInfoMapper.delete(wrapper2);
            System.out.println("???????????????????????????"+delete1+"???");
        }
        return "1";
    }

    //????????????????????????????????????????????????
    @Override
    public String edit(RegisterVO vo) {
        User oldUser = userMapper.selectUser(vo.getUserId());
        if (StringUtil.isEmpty(vo.getUsername()) ||
                StringUtil.isEmpty(vo.getPassword())) {
            return "-2";
        }
        User user1 = userMapper.selectUserByName(vo.getUsername());
        if (user1 != null && !oldUser.getUsername().equals(vo.getUsername())) {
            return "0";
        }
        User user = new User();
        Map<String, Object> param = new HashMap<>();
        param.put("user_id", vo.getUserId());
        List<Permission> permissions =  permissionMapper.selectByMap(param);
        for (Permission p:permissions){
            if (p != null){
                p.setRoleId(vo.getRoleId());
                int i = permissionMapper.updatePermission(p);
            }
        }
        BeanUtils.copyProperties(vo, user);
        user.setId(vo.getUserId());
        System.out.println(user);
        int i = userMapper.updateUser(user);
        return "1";
    }


}
