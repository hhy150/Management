package com.example.management.app.interceptor;

import com.example.management.entity.AuthURL;
import com.example.management.exception.AuthException;
import com.example.management.util.ConstantUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger log= LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object o) throws AuthException, IOException {
        //怎么判断404路径？？就是不存在的路径
        Object role1 = request.getSession().getAttribute(ConstantUtils.USER_ROLE);//记得修改一下，
        int role=0;
        if(role1!= null)
            role=(int)role1;
        String url =  request.getServletPath();
        System.out.println(url);
        if("/error".equals(url)){
            //这个response怎么相应
            response.sendError(404,"not found");
            throw new AuthException("404 : not found");
        }
        switch (role){
            case 1:
                if(hasAuth(AuthURL.STU_AUTH,url))
                    return true;
                throw new AuthException("没有权限");
            case 2:
                if(hasAuth(AuthURL.DEPT_AUTH,url)){
                    return true;
                }
                throw new AuthException("没有权限");
            case 3:
                if(hasAuth(AuthURL.CLUB_AUTH,url))
                    return true;
                throw new AuthException("没有权限");
            case 4:
                if(hasAuth(AuthURL.SUPER_ADMIN_AUTH,url))
                    return true;
                throw new AuthException("没有权限");
            case 0:
                //表示没有登录
                response.sendRedirect("/login");//登录页面.
                return false;
         }
         log.info("发生错误，角色不存在");
         return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }

    /**
     * 认证规则
     */
    public boolean hasAuth(String[] roleAuth,String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        for(String auth:roleAuth) {
            if (matcher.match(auth, url)) {
                log.info("认证通过");
                return true;
            }
        }
        log.info("认证失败");
        return false;
    }

}
