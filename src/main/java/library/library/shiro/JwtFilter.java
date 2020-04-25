package library.library.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import library.library.entity.User;
import library.library.service.BookService;
import library.library.service.UserService;
import library.library.util.JwtUtil;

import library.library.util.SpringContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;



import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Autowired
    UserService userService;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try{
            executeLogin(request,response);
            HttpServletRequest servletRequest=(HttpServletRequest)request;

            String token=servletRequest.getHeader("token");
            String usernmae=JwtUtil.getUsername(token);
            UserService userService=(UserService)SpringContextUtils.getBean("userServiceImpl");
            User user=userService.finduserBynam(usernmae);
            String url=servletRequest.getRequestURL().toString();
            System.out.println(user);
            System.out.println(url);
            return true;
        }catch (Exception e){
            throw new AuthenticationException("sdfs");
        }

    }

    @Override
    protected boolean isLoginAttempt(String authzHeader) {
        return super.isLoginAttempt(authzHeader);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        String token=httpServletRequest.getHeader("token");
        System.out.println(token);
        JwtToken jwtToken=new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        String username = JwtUtil.getUsername(token);
        BookService bookService=(BookService) SpringContextUtils.getBean("bookServiceImpl");
        System.out.println(bookService.list());
        System.out.println(username);
////        if (username == null) {
////            throw new AuthenticationException("token非法无效!");
////        }
////        //根据用户名从数据库中查找用户，判断用户的状态,然后将对象用户返回
//
//        User user=userService.finduserBynam(username);
//        System.out.println(user);
////        if(!JwtUtil.verify(token,username,user.getPassword())){
////            throw new AuthenticationException(" 用户名或密码错误");
////        }
        getSubject(request,response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse httpServletResponse=(HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
