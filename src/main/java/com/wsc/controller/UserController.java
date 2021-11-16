package com.wsc.controller;

import com.wsc.VO.RegisterVO;
import com.wsc.VO.TokenVO;
import com.wsc.VO.backVO.UserVO;
import com.wsc.entity.User;
import com.wsc.service.PermissionService;
import com.wsc.service.UserService;
import com.wsc.util.JsonResult;
import com.wsc.util.Result;
import com.wsc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 18560
 */
@Controller
@RequestMapping("/user")
/**
 * 使用token记录用户信息
 */
@SessionAttributes("loginUser")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private HttpSession session;

    User user = new User();



    @GetMapping("/index")
    public String login() {
        return "user/index";
    }

    //登录入口
    @PostMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, Model model, String username, String password) {
        User loginUser = userService.getUserByName(username);
        if (loginUser != null) {
            if (loginUser.getDisabled() == 0){
                User loginUser2 = userService.getUser(username, password);
                if (loginUser2 != null) {
                    session.setAttribute("loginUser", loginUser2);
                    model.addAttribute("loginUser", loginUser2);
                    System.out.println("登陆成功");
                    System.out.println("loginUser:" + request.getSession().getAttribute("loginUser"));
                    //生成token，并保存到数据库
                    String token = userService.createToken(loginUser2);
                    TokenVO tokenVO = new TokenVO();
                    tokenVO.setToken(token);
                    System.out.println(token);
                    System.out.println("登陆成功");
                    return Result.ok(tokenVO);
                }
                return Result.build(401, "密码不正确！");
            }
            return Result.build(400, "该用户已被禁用!");
        }
        return Result.build(401, "该用户不存在！");
    }

    //后台登录入口
    @PostMapping("/loginBack")
    @ResponseBody
    public Result loginBack(HttpServletRequest request, String username, String password) {
        User loginUser = userService.getUserByName(username);
        //判断用户名存在
        if (loginUser != null) {
            //判断用户是否被禁用
            if (loginUser.getDisabled() == 0){
                User loginUser2 = userService.getUser(username, password);
                //判断用户密码是否正确
                if (loginUser2 != null) {
                    Integer roleId = permissionService.getByUserId(loginUser.getId());
                    if (roleId != null && (roleId == 3 || roleId == 4)) {
                        //生成token，并保存到数据库
                        String token = userService.createToken(loginUser2);
                        TokenVO tokenVO = new TokenVO();
                        tokenVO.setToken(token);
                        System.out.println(token);
                        System.out.println("登陆成功");
                        return Result.ok(tokenVO);
                    }
                    //用户不是管理员
                    return Result.build(400, "用户不是管理员!");
                }
                //密码不正确
                return Result.build(400, "密码不正确!");
            }
            //该用户已被禁用
            return Result.build(400, "该用户已被禁用!");
        }
        //该用户不存在
        return Result.build(400, "该用户不存在!");


    }

    /**
     * 登出
     *
     * @param
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        //从request中取出token
        String token = TokenUtil.getRequestToken(request);
        userService.logout(token);
        return Result.ok();
    }


    @PostMapping("/register")
    @ResponseBody
    public String register(RegisterVO vo) {
        return userService.addUser(vo);

    }

    @PostMapping("/getByToken")
    @ResponseBody
    public User getByToken(String token) {
        return userService.findByToken(token);

    }

    @RequestMapping("/leave")
    public String leave(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "user/index";
    }

    private Cookie getCookie(Cookie[] cookies, String name) {
        Cookie cookie = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }

    @GetMapping("/getAbleUsers")
    @ResponseBody
    public JsonResult getAbleUsers(HttpServletRequest request, Model model, UserVO vo, Integer page, Integer limit) {

        return userService.getAbleUsers(vo, page, limit);
    }

    @GetMapping("/getDisableUsers")
    @ResponseBody
    public JsonResult getDisableUsers(HttpServletRequest request, Model model, UserVO vo, Integer page, Integer limit) {

        return userService.getDisableUsers(vo, page, limit);
    }


    /*@RequestMapping("/detail")
    @ResponseBody
    public JsonResult detail(long id){
        System.out.println("查看详情");
        return userService.getUser(id);
    }*/

    @RequestMapping("/disable")
    @ResponseBody
    public String disable(long id) {
        System.out.println("禁用用户");
        return userService.disable(id);
    }

    @RequestMapping("/able")
    @ResponseBody
    public String able(long id) {
        System.out.println("恢复用户");
        return userService.able(id);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(RegisterVO vo) {
        return userService.edit(vo);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id) {
        return userService.delete(id);
    }


}
