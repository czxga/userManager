package com.cao.controller;

import com.cao.model.User;
import com.cao.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("/login.do")
    public ModelAndView login(User user) throws Exception {
        Boolean login = this.userService.login(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( login?"index":"login" );
        modelAndView.addObject( "msg" , "登录错误" );
        return modelAndView;

    }

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response, Long id)throws Exception{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        User user = userService.selectUser(Long.parseLong(strings.iterator().next()));
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(null == user? "该id不存在用户":user));
        response.getWriter().close();
    }

    @RequestMapping("/addUser.do")
    public ModelAndView insertUser(User user, HttpServletRequest request)throws Exception{
        if (null == user){
            return null;
        }
        user.setRegTime(new Date());
        user.setRegIp(request.getRemoteAddr());
        Long aLong = this.userService.insertUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( aLong >= 1L?"login":"register" );
        return modelAndView;
    }


}

