package library.library.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import library.library.entity.User;
import library.library.service.UserService;
import library.library.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;



public class Realm extends AuthorizingRealm {

    @Autowired
    public UserService userService;
    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println(token);
        String tok = (String) token.getCredentials();
        System.out.println(tok);
        if (tok == null) {
            throw new AuthenticationException("token为空");
        }
        //校验token有效性
      User user=this.checkUserTokenIsEffect(tok);
        if(!JwtUtil.verify(tok,user.getUserName(),user.getPassword())){
            throw new AuthenticationException("用户名或密码不正确");
        }

        return new SimpleAuthenticationInfo(user,tok,getName());
    }

    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        //System.out.println(token);
        String username = JwtUtil.getUsername(token);
       // System.out.println(username);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }
        //根据用户名从数据库中查找用户，判断用户的状态,然后将对象用户返回
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        User user=userService.getOne(queryWrapper);
        System.out.println(user);
        return user;
    }
    public static void main(String[] args){
        String username=JwtUtil.getUsername("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODY4MjgxMTMsInVzZXJuYW1lIjoi5piT5Z-O6JOd5Zu-In0.J43wBQ-2q68YI5obKymrxKx_QvOxKPnnPudcT9-a11I");
        System.out.println(username);
        SimpleAuthenticationInfo info= new SimpleAuthenticationInfo("易城蓝图","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODY4NTQ2MzAsInVzZXJuYW1lIjoi5piT5Z-O6JOd5Zu-In0.e8lO7J0r2Z3V9JWc11vRCuoI9kylaJt9wgF1WYsGYRE","");
        System.out.println(info);

    }

}
